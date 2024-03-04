package com.furniturecloud.security;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import com.furniturecloud.security.utils.RSAKeyProperties;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
@Configuration
//@EnableWebSecurity
public class SecuritySetttings {
	
	private final RSAKeyProperties keys;
	
	public SecuritySetttings(RSAKeyProperties keys) {
		// TODO Auto-generated constructor stub
		this.keys=keys;
	}
	@Bean
	public AuthenticationManager getAuthMan(UserDetailsService uds) {
		DaoAuthenticationProvider daop = new DaoAuthenticationProvider();
		daop.setUserDetailsService(uds);
		return new ProviderManager(daop);
	}
    @Bean
    public PasswordEncoder passEnc() {
		return new BCryptPasswordEncoder();
	}
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		 return http
				 .csrf(csrf->csrf.disable())
				 .authorizeHttpRequests(auth->{
					auth.requestMatchers("/auth/**" ).permitAll();
					auth.requestMatchers("/user/**").hasAnyRole("ADMIN", "USER");
					auth.requestMatchers("/admin/**").hasRole("ADMIN");
					auth.anyRequest().authenticated();
					})
				 .oauth2ResourceServer(o->o.jwt(jwt->jwt.jwtAuthenticationConverter(jwtAuthConverter())))
				 .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				 .build();
	}
    
    @Bean
    public JwtDecoder jwtDecoder() {
    	return NimbusJwtDecoder.withPublicKey(keys.getPublicKey()).build();
    }
    
    @Bean
    public JwtEncoder jwtEncoder() {
    	JWK jwk = new RSAKey.Builder(keys.getPublicKey()).privateKey(keys.getPrivateKey()).build();
    	JWKSource<SecurityContext> jwks = new ImmutableJWKSet<SecurityContext>(new JWKSet(jwk));
    	return new NimbusJwtEncoder(jwks);
    }
    
    @Bean
    public JwtAuthenticationConverter jwtAuthConverter() {
    	JwtGrantedAuthoritiesConverter jGAC=new  JwtGrantedAuthoritiesConverter();
    	jGAC.setAuthoritiesClaimName("roles");
    	jGAC.setAuthorityPrefix("ROLE_");
    	JwtAuthenticationConverter jwtAuthConverter= new JwtAuthenticationConverter();
    	jwtAuthConverter.setJwtGrantedAuthoritiesConverter(jGAC);
    	return jwtAuthConverter;
    }

}
