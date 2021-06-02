package com.cognizant.ccm.model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;


class AuthResponseTest {
	AuthResponse authResponse = new AuthResponse();

	@Test
	void test_SetUid() {
		authResponse.setUId("1");
		assertEquals("1", authResponse.getUId());
	}

	@Test
	void test_GetUid() {
		authResponse.setUId("1");
		assertEquals("1", authResponse.getUId());
	}

	@Test
	void test_SetName() {
		authResponse.setUName("Admin");
		assertEquals("Admin", authResponse.getUName());
	}

	@Test
	void test_GetName() {
		authResponse.setUName("Admin");
		assertEquals("Admin", authResponse.getUName());
	}

	@Test
	void test_SetValid() {
		authResponse.setValid(true);
		assertEquals(true, authResponse.isValid());
	}

	@Test
	void test_IsValid() {
		authResponse.setValid(true);
		assertEquals(true, authResponse.isValid());
	}

	@Test
	void test_AuthResponse_StringStringBoolean() {
		AuthResponse authrespo = new AuthResponse("1", "Admin", true);
		assertEquals("Admin", authrespo.getUName());
	}
}
