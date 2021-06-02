package com.cognizant.ccm.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

class GlobalExceptionHandlerTest {
	
	HttpHeaders headers;
	
	HttpStatus status;
	
	WebRequest request;
	
	@Test
	void test_GatewaySettingsNotFoundExceptionHandler() {
		GlobalExceptionHandler global = new GlobalExceptionHandler();
		ResponseEntity<String> response = global.gatewaySettingsNotFoundExceptionHandler(new GatewaySettingsParametersNotFoundException(null));
		assertEquals(404 , response.getStatusCodeValue());
	}
	
	@Test
	void test_TokenNotFoundException() {
		GlobalExceptionHandler global = new GlobalExceptionHandler();
		ResponseEntity<String> response = global.invalidTokenExceptionHandler(new InvalidTokenException());
		assertEquals("Security Token is Invalid or it may be Expired", response.getBody());
	}
	
}
