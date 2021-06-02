package com.cognizant.authorization.service;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;
 
/**
 * 
 * @Component indicates that an annotated class is a "component".
 *  Such classes are considered as candidates for auto-detection
 *  when using annotation-based configuration and classpath scanning. 
 * 
 * 	@Slf4j (Simple Logging Facade for Java) provides a simple abstraction of all
 *        the logging frameworks
 *
 */

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	/**
	 * autowired the JwtUtil and CustomerDetailsService
	 */
	
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private CustomerDetailsService customerDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//Get Authorization header and validate it
		log.info("START :: Method :: doFilterInternal() :: ");
		final String authHeadder = request.getHeader("Authorization");
		String username = null;
		String jwt = null;

		if (authHeadder != null && authHeadder.startsWith("Bearer ")) {
			jwt = authHeadder.substring(7);
			username = jwtUtil.extractUsername(jwt);

		}
		/**Get jwt token and validate it 
		  *Get User identity and set it on the spring security context
		**/
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			var userDetails = this.customerDetailsService.loadUserByUsername(username);

			if (Boolean.TRUE.equals(jwtUtil.validateToken(jwt, userDetails))) {

				var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
		log.info("END :: Method :: doFilterInternal() :: ");
	}

}
