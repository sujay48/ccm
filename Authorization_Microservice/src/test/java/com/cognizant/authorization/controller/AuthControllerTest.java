package com.cognizant.authorization.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognizant.authorization.model.AuthRequest;
import com.cognizant.authorization.model.AuthResponse;
import com.cognizant.authorization.model.AuthenticationResponse;
import com.cognizant.authorization.repository.UserRepository;
import com.cognizant.authorization.service.CustomerDetailsService;
import com.cognizant.authorization.service.JwtUtil;

/**
 
 * 		With the @SpringBootTest annotation, Spring Boot provides a 
 * 		convenient way to start up an application context to be used in a test
 * 		@RunWith is an annotation to use test runners
 *
 */

@SpringBootTest
class AuthControllerTest {

	/**
	 * @InjectMocks annotation can be used to inject mock fields into a test object automatically
	 */
	@InjectMocks
	AuthController authController;

	AuthenticationResponse authResponse;

	UserDetails userdetails;

	@Mock
	JwtUtil jwtUtil;

	@Mock
	CustomerDetailsService customerDetailService;

	@Mock
	UserRepository userservice;
	
	@Mock
	AuthenticationManager autheticationManager;
	/**
	 * to test the login when valid details are entered
	 */
	@Test
	void test_loginTest() throws Exception {
		
		AuthRequest user = new AuthRequest("admin", "admin");
		UserDetails loadUserByUsername = customerDetailService.loadUserByUsername("admin");
		UserDetails userDetails = new User(user.getUsername(), user.getPassword(), new ArrayList<>());
		when(customerDetailService.loadUserByUsername("admin")).thenReturn(userDetails);
		when(jwtUtil.generateToken(loadUserByUsername)).thenReturn("token");
		ResponseEntity<?> login = authController.createAuthorizationToken(user);
		assertEquals( 200 , login.getStatusCodeValue() );
	}

	/**
	 * to test the login when invalid details are entered
	 * @throws Exception 
	 */
	
	@Test
	void test_loginFailedTest() throws Exception {
		AuthRequest user = new AuthRequest("admin", "admin");
		when(autheticationManager.authenticate(Mockito.any())).thenThrow(BadCredentialsException.class);
		assertThrows(BadCredentialsException.class, () -> authController.createAuthorizationToken(user));
    }
	

	/**
	 * to test validation of invalid token
	 */

	@Test
	void test_validateTest() {
		when(jwtUtil.validateToken("token")).thenReturn(true);
		ResponseEntity<AuthResponse> validity = authController.getValidity("bearer token");
		AuthResponse authResponse = validity.getBody();
		assertEquals(true, authResponse.isValid());
	}
	
	@Test
	void test_validateTestInValidtoken() {
		when(jwtUtil.validateToken("token")).thenReturn(false);
		ResponseEntity<AuthResponse> validity = authController.getValidity("bearer token");
		AuthResponse authResponse = validity.getBody();
		assertEquals(false, authResponse.isValid());
	}

}
