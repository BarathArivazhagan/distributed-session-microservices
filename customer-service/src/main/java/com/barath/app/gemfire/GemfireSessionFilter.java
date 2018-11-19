package com.barath.app.gemfire;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.session.data.gemfire.GemFireOperationsSessionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class GemfireSessionFilter extends OncePerRequestFilter{
	
	
	private static final Logger logger = LoggerFactory.getLogger(GemfireSessionFilter.class);
	private final GemFireOperationsSessionRepository repository;	
	

	public GemfireSessionFilter(GemFireOperationsSessionRepository repository) {
		super();
		this.repository = repository;
	}



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		if(logger.isInfoEnabled()) {
			logger.info("gemfire session filter invoked ====>");
		}
		HttpSession session = request.getSession();
		logger.info("session id {}",session.getId());
		logger.info("new session ???  {}",session.isNew());
		if(session.isNew()) {
			throw new ServletException("Session id is not found access not allowed");
		}
	
		
		filterChain.doFilter(request, response);
	}

}
