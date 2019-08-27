package com.barath.app;

import java.security.Principal;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class SecurityHeaderGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {

    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange, chain) -> {
        	
        	ServerHttpRequest.Builder mutate = exchange.getRequest().mutate();
        	 System.out.println("CALLED ======>");
        	return exchange.getPrincipal()
        			
        		.map(principal -> {
    	        if (principal instanceof Authentication) {
    	            Authentication authentication = (Authentication) principal;
    	            Object principal1 = authentication.getPrincipal();
    	            if (principal1 instanceof UserDetails) {
    	                UserDetails user = (UserDetails) principal1;
    	                mutate.header(config.getName(), user.getUsername());
    	            }
    	        }
    	        return mutate;
    	    }).flatMap(builder -> chain.filter(exchange.mutate().request(builder.build()).build()));

          
        };
    }


}