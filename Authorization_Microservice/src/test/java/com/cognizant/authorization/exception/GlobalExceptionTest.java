package com.cognizant.authorization.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;


class GlobalExceptionTest {
	
	@Test
	void test_BadCredentialsExceptionHandler() {
		GlobalExceptionHandler global = new GlobalExceptionHandler();
		ResponseEntity<String> response = global.badCredentialsExceptionHandler(new BadCredentialsException(null));
		assertEquals(404 , response.getStatusCodeValue());
	}
}
