package com.barath.app.gemfire;

import java.lang.invoke.MethodHandles;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.gemfire.GemfireOperations;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/sessions")
public class CacheController {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private static final String SELECT_QUERY_SESSIONS="SELECT * FROM /gemfire-http-sessions.keySet";
	private final GemfireOperations gemfireOperations;
	private final SessionRepository<Session> sessionRepository;
	private static final ObjectMapper mapper = new ObjectMapper();
	
	
	public CacheController(GemfireOperations gemfireOperations,SessionRepository<Session> sessionRepository) {
		super();
		this.gemfireOperations = gemfireOperations;
		this.sessionRepository = sessionRepository;
	}

	@GetMapping
	public List<String> getSessionsFromGemfire(){
		
		return gemfireOperations.query(SELECT_QUERY_SESSIONS)
			.stream()
			.map(Objects::toString)
			.collect(Collectors.toList());
	}
	
	@GetMapping("/{sessionId}")
	public Map<String,Object> getSessionsFromGemfireByKey(@PathVariable String sessionId) throws Exception{
		
		Session session = gemfireOperations.get(sessionId);
		if( session == null) { throw new SessionNotFoundException(" session not found "); }
		logger.info(" attritube names {}",mapper.writeValueAsString(session.getAttributeNames()));
		return session.getAttributeNames()
		 .stream()
		 .collect(Collectors.toMap(atr -> atr , atr -> session.getAttribute(atr)));
	
			
	}
	
	@PostMapping("/{sessionId}")
	public Map<String,Object> saveSessionAttritubes(HttpSession session ,@PathVariable String sessionId, @RequestBody Map<String,Object> sessionAttritubes){
		
		
		if( session == null) { throw new SessionNotFoundException(" session not found "); }
		sessionAttritubes
			.entrySet()
				.stream()
					.forEach( entry -> {
						
						session.setAttribute(entry.getKey(), entry.getValue());
					});
		
		return enumerationAsStream(session.getAttributeNames())				
				.collect(Collectors.toMap(Function.identity(), atr -> session.getAttribute(atr)));
	
			
	}
	
	@DeleteMapping("/remove")
	public void removeSessions() {
		
		this.gemfireOperations
			.query(SELECT_QUERY_SESSIONS)
			.stream()
			.map(Objects::toString)
			.collect(Collectors.toList())
			.stream()
			.forEach( key ->{
				
				System.out.println("key "+key);
				this.gemfireOperations.remove(key);
			});
			
	}
	
	
	protected static class SessionNotFoundException extends RuntimeException {

	
		private static final long serialVersionUID = -2442514644155426307L;

		public SessionNotFoundException() {
			super();
			
		}

		public SessionNotFoundException(String message) {
			super(message);
		
		}
		
		
		
	}
	
	public static <T> Stream<T> enumerationAsStream(Enumeration<T> e) {
	     return StreamSupport.stream(
	         Spliterators.spliteratorUnknownSize(
	             new Iterator<T>() {
	                 public T next() {
	                     return e.nextElement();
	                 }
	                 public boolean hasNext() {
	                     return e.hasMoreElements();
	                 }
	             },
	             Spliterator.ORDERED), false);
	 }
}
