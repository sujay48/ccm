package com.cognizant.ccm.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.cognizant.ccm.model.AuthResponse;

class AuthorizationFallbackFactoryTest {

	@Test
	void test_GetValidity() {
		AuthorizationFallbackFactory authorizationFallbackFactory = new AuthorizationFallbackFactory();
		AuthResponse authResponse = authorizationFallbackFactory.getValidity("token");
		assertEquals(false, authResponse.isValid());
	}
}
