package com.cognizant.authorization.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AuthenticationResponseTest {
	
	AuthenticationResponse authResponse = new AuthenticationResponse();

	@Test
	void test_SetJwt() {
		authResponse.setJwt("token");
		assertEquals("token", authResponse.getJwt());
	}

	@Test
	void test_GetJwt() {
		authResponse.setJwt("token");
		assertEquals("token", authResponse.getJwt());
	}
}
