package com.cognizant.producer.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MessageTest {

	Message message = new Message();
	
	@Test
	void test_Description() {
		message.setDescription("Hello");
		assertEquals("Hello", message.getDescription());
	}
}
