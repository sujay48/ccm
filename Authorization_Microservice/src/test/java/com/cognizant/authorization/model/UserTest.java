package com.cognizant.authorization.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserTest {
	User user = new User();

	@Test
	void test_Id() {
		user.setId(1);
		assertEquals(1, user.getId());
	}

	@Test
	void test_UserName() {
		user.setUserName("admin");
		assertEquals("admin", user.getUserName());
	}

	@Test
	void test_Password() {
		user.setPassword("admin");
		assertEquals("admin", user.getPassword());
	}

	@Test
	void test_Active() {
		user.setActive(true);
		assertEquals(true, user.isActive());
	}
	
	@Test
	void test_Roles() {
		user.setRoles("admin");
		assertEquals("admin", user.getRoles());
	}
}
