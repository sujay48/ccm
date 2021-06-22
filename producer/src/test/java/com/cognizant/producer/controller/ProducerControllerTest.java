package com.cognizant.producer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.producer.model.Message;
import com.cognizant.producer.service.ProducerService;

@ExtendWith(MockitoExtension.class)
class ProducerControllerTest {
	
	@Mock
	ProducerService producerService;
	
	@InjectMocks
	ProducerController producerController;
	
	@Test
	void testPublishToFirstQueueWithHighPriority() {
		Message message = new Message();
		message.setDescription("Description");
		assertEquals("Sent Successfully to First Queue as High Priority!!",producerController.publishToFirstQueueWithHighPriority(message));
		Mockito.verify(producerService, Mockito.times(1)).publishToFirstQueue(message, 2);
	}
	
	@Test
	void testPublishToFirstQueueWithLowPriority() {
		Message message = new Message();
		message.setDescription("Description");
		assertEquals("Sent Successfully to First Queue as Low Priority!!",producerController.publishToFirstQueueWithLowPriority(message));
		Mockito.verify(producerService, Mockito.times(1)).publishToFirstQueue(message, 1);
	}
	
	@Test
	void testPublishToSecondQueue() {
		Message message = new Message();
		message.setDescription("Description");
		assertEquals("Sent Successfully to Second Queue!!",producerController.publishToSecondQueue(message));
		Mockito.verify(producerService, Mockito.times(1)).publishToSecondQueue(message);
	}
}
