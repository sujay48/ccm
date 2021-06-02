package com.cognizant.authorization.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
/**
 * 
 * 
 * 		the class tests all the setters and getters and constructors
 * 		for the admin details 
 *
 */


class AuthRequestTest {

	AuthRequest authRequest = new AuthRequest();
	
	@Test
	void test_Username() {
		authRequest.setUsername("admin");
		assertEquals("admin", authRequest.getUsername());
	}
	
	@Test
	void test_Password() {
		authRequest.setPassword("admin");
		assertEquals("admin", authRequest.getPassword());
	}

}
