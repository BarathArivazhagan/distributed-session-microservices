package com.barath.app.gemfire;


import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.session.events.SessionDeletedEvent;
import org.springframework.session.events.SessionDestroyedEvent;
import org.springframework.session.events.SessionExpiredEvent;
import org.springframework.stereotype.Component;


@Component
public class GemfireSessionEvents {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@EventListener
	public void handleSessionCreationEvent(SessionCreatedEvent event){
		logger.info("SESSION CREATED EVENT {}",event.getSession().getId());
		
	}
	
	@EventListener
	public void handleSessionDeletionEvent(SessionDeletedEvent event){
		logger.info("SESSION Deleted EVENT {}",event.getSession().getId());
		
	}
	
	@EventListener
	public void handleSessionDestroyedEvent(SessionDestroyedEvent event){
		logger.info("SESSION Destroyed EVENT {}",event.getSession().getId());
		
	}

	
	@EventListener
	public void handleSessionExpiredEvent(SessionExpiredEvent event){
		logger.info("SESSION Expired EVENT {}",event.getSession().getId());
		
	}

}
