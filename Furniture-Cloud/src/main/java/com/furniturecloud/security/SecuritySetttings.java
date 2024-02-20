package com.furniturecloud.security;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.web.SecurityFilterChain;
@Configuration
//@EnableWebSecurity
public class SecuritySetttings {
//	@Bean
//	public UserDetailsService userDetails(DataSource ds) {
//		
//		
//		UserDetails user1 = User.withUsername("Admin").password("{noop}1234").roles("ADMIN").build();
//		UserDetails user2 = User.withUsername("User").password("{noop}1234").roles("USER").build();
//		JdbcUserDetailsManager jdbcMan = new JdbcUserDetailsManager(ds);
//		jdbcMan.createUser(user1);
//		jdbcMan.createUser(user2);
//		return null;
//		
//	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests((authorize) -> authorize
//		        .anyRequest().authe
//		    );
		http.csrf((csrf) -> csrf.disable());
		http.sessionManagement(s->s.disable());
		http.httpBasic(d->d.disable());
//		http.httpBasic(Customizer.withDefaults());
		return http.build();
	}

}
