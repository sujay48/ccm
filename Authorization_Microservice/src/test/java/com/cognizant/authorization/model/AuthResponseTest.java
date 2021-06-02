package com.cognizant.authorization.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
/**
 * 		the class tests all the setters and getters and constructors
 * 		for the Authorization response 
 *
 */


class AuthResponseTest {

	AuthResponse authResponse = new AuthResponse();

	@Test
	void test_SetUId() {
		authResponse.setUId("1");
		assertEquals("1", authResponse.getUId());
	}

	@Test
	void test_GetUId() {
		authResponse.setUId("1");
		assertEquals("1", authResponse.getUId());
	}
	
	@Test
	void test_SetUname() {
		authResponse.setUName("admin");
		assertEquals("admin", authResponse.getUName());
	}

	@Test
	void test_GetUname() {
		authResponse.setUName("admin");
		assertEquals("admin", authResponse.getUName());
	}
	
	@Test
	void test_IsValid() {
		authResponse.setValid(true);
		assertEquals(true, authResponse.isValid());
	}

	@Test
	void test_SetValid() {
		authResponse.setValid(true);
		assertEquals(true, authResponse.isValid());
	}

}
