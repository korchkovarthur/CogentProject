package com.furniturecloud.security;

//import javax.sql.DataSource;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//public class SecuritySetttings {
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
//
//}
