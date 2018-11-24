package com.barath.app;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.csrf.WebSessionServerCsrfTokenRepository;

@Configuration
public class SecurityConfiguration {
	
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		
		http
			.authorizeExchange()				
			.anyExchange().authenticated()
			.and()
			.oauth2Login();
		
		
			
		
		return http.build();
	}

}
