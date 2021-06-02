package com.cognizant.authorization;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
/**
 * 
 * 
 * 		 With the @SpringBootTest annotation, Spring Boot provides a 
 * 		 convenient way to start up an application context to be used in a test
 *
 */
@SpringBootTest

public class AuthorizationMicroserviceApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void applicationStarts() {
		AuthorizationMicroserviceApplication.main(new String[] {});
	}
}