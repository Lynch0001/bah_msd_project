package com.bah.project.dataservice.utility;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthFilter implements Filter {

	private static Logger log = LoggerFactory.getLogger(AuthFilter.class);
	
	@Autowired
	private JwtUtil jwtUtil;
	
	private String api_scope = "com.api.customer.r";
	private String AUTH_SCOPE = "API_TOKEN";
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		log.debug("AUTH FILTER - request: {}",request);
		
		HttpServletResponse res = (HttpServletResponse) response;
		log.debug("AUTH FILTER - response: {}", response);
		
		String uri = req.getRequestURI();
		log.debug("AUTH FILTER - uri: {}", uri);
		
		
		// URI starts with API
		
		
		
		try {
			
			if(uri.startsWith("/api/customers") || uri.startsWith("/api/events") || uri.startsWith("/api/registrations") ) {
				
				// Get Header and Verify the Token
				
				String authheader = req.getHeader("authorization");

				log.debug("AUTH FILTER - Auth header: {}", authheader);
				log.debug("AUTH FILTER - Auth header length: {}", authheader.length());
				log.debug("AUTH FILTER - Auth header: {}", authheader.substring(0));
				if (authheader != null && authheader.length() > 7 && authheader.startsWith("Bearer")) {
					
					String jwt_token = authheader.substring(7, authheader.length());
					
					log.debug("AUTH FILTER - Auth token: {}", jwt_token);
					
					if (jwtUtil.verifyToken(jwt_token)) {
							
						log.debug("AUTH FILTER -token verified");
						
						String request_scopes = jwtUtil.getScopes(jwt_token);
				
						log.debug("AUTH FILTER -checking scopes");
					
							if (request_scopes.contains(api_scope) || request_scopes.contains(AUTH_SCOPE)) {

								log.debug("AUTH FILTER -validated scopes");
								
								// continue to API and execute request
								
								chain.doFilter(request, response);
								return;

				
							} else {	// reject request and return error instead of data
								((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid Scope");} // scope
							
					}else {	// reject request and return error instead of data
						((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid Token");} 	// token	
					
				} else {	// reject request and return error instead of data
					((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid Header");
					throw new Exception("Invalid Header");} // header
				
			} else {
				// reject request and return error instead of data
				((HttpServletResponse) response).sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad Request - Invalid API"); // if
			} // else
		} catch (Exception e) {
			log.debug("Exception message: {}", e.getMessage());
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad Request - Invalid API");
		}
		
		
			
	} // method
				
} // class
	
