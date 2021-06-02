package com.cognizant.authorization.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

class MyUserDetailsTest {

	
	User user = new User(1, "admin", "admin", "admin", true);
	MyUserDetails userDetails = new MyUserDetails(user);
	
	@Test
	void test_GetUserName() {
		assertEquals("admin", userDetails.getUsername());
	}
	
	@Test
	void test_GetPassword() {
		assertEquals("admin", userDetails.getPassword());
	}
	
	@Test
	void test_GetAuthority() {
		assertEquals(Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList()), userDetails.getAuthorities());
	}
	
	@Test
	void test_IsAccountNotExpired() {
		assertEquals(true, userDetails.isAccountNonExpired());
	}
	
	@Test
	void test_IsAccountNotLocked() {
		assertEquals(true, userDetails.isAccountNonLocked());
	}
	
	@Test
	void test_IsCredentialsNonExpired() {
		assertEquals(true, userDetails.isCredentialsNonExpired());
	}
	
	@Test
	void test_IsEnabled() {
		assertEquals(true, userDetails.isEnabled());
	}
	
	@Test
	void test_UserDetails() {
		userDetails = new MyUserDetails();
		assertEquals(false, userDetails.isEnabled());
	}
}
