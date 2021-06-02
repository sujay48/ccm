package com.cognizant.authorization.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.authorization.model.AuthRequest;
import com.cognizant.authorization.model.AuthResponse;
import com.cognizant.authorization.model.AuthenticationResponse;
import com.cognizant.authorization.service.CustomerDetailsService;
import com.cognizant.authorization.service.JwtUtil;
 /**
  *
 * @RestController annotation marks this class as a RESTful web service
 * @RequestMapping annotation maps HTTP requests to handler methods
 * @Slf4j annotation provides a simple abstraction of all the logging frameworks
 * 
 */
 
@RestController
public class AuthController {

	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomerDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;

	/**
	 * Method-POST, http://localhost:8083/login
	 * @return token
	 * to allow login of a user only after verifying
	 * that the user trying to use this service is authenticated or not
	 */
	
	@PostMapping(value = "/login")
	public ResponseEntity<?> createAuthorizationToken(@Valid @RequestBody AuthRequest authRequest) throws Exception
	{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authRequest.getUsername(), authRequest.getPassword()));
			
		}
		catch(BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect username or password");
		}
		final var userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	/**
	 * Method-GET, http://localhost:8083/validate
	 * @return valid status 
	 * to set the validity of a user if the token provided
	 * to the application is correct.
	 */
	@GetMapping(value = "/validate")
	public ResponseEntity<AuthResponse> getValidity(@RequestHeader("Authorization") String token) {
		
		token = token.substring(7);
		var res = new AuthResponse();
		if (Boolean.TRUE.equals(jwtUtil.validateToken(token))) {
			res.setValid(true);
		} else {
			res.setValid(false);
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}