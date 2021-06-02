package com.cognizant.authorization.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cognizant.authorization.model.User;
import com.cognizant.authorization.repository.UserRepository;

/**
 * 
 * 
 * 
 * With the @SpringBootTest annotation, Spring Boot provides a convenient way to
 * start up an application context to be used in a test
 * 
 * @RunWith is an annotation to use test runners
 *
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CustomerDetailsServiceTest {

	/**
	 * @InjectMocks annotation can be used to inject mock fields into a test object
	 *              automatically
	 */
	@InjectMocks
	CustomerDetailsService customerDetailService;

	@Mock
	UserRepository userRepository;

	/**
	 * to test the test_loadUserByUsername()
	 */

	@Test
	void test_loadUserByUsernameTest1() throws UsernameNotFoundException {

		User user1 = new User(1, "admin", "admin", "admin", true);
		Optional<User> user = Optional.of(user1);
		when(userRepository.findByUserName("admin")).thenReturn(user);
		UserDetails loadUserByUsername = customerDetailService.loadUserByUsername("admin");
		assertEquals(user1.getUserName(), loadUserByUsername.getUsername());
	}

	@Test
	void test_loadUserByUsernameTest2() throws UsernameNotFoundException {

		Optional<User> user = Optional.empty();
		when(userRepository.findByUserName(Mockito.anyString())).thenReturn(user);
		assertThrows(UsernameNotFoundException.class, () -> customerDetailService.loadUserByUsername("admin"));
	}

}