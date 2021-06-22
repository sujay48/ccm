package com.cognizant.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.producer.model.Message;
import com.cognizant.producer.service.ProducerService;

@RestController
@RequestMapping("/producer")
public class ProducerController {
	
	private static final Integer HIGH_PRIORITY = 2;
	private static final Integer LOW_PRIORITY = 1;
	
	@Autowired
	private ProducerService producerService;
	
	/**
	 * To publish message to the queue with high priority
	 * @param message
	 * @return
	 */
	@PostMapping(value = "/first/high")
	public String publishToFirstQueueWithHighPriority(@RequestBody Message message) {
		producerService.publishToFirstQueue(message, HIGH_PRIORITY);
		return "Sent Successfully to First Queue as High Priority!!";
	}
	
	/**
	 * To publish message to the queue with low priority
	 * @param message
	 * @return
	 */
	@PostMapping(value = "/first/low")
	public String publishToFirstQueueWithLowPriority(@RequestBody Message message) {
		producerService.publishToFirstQueue(message, LOW_PRIORITY);
		return "Sent Successfully to First Queue as Low Priority!!";
	}
	
	/**
	 * To publish message to the second queue
	 * @param content
	 * @return
	 */
	@PostMapping(value = "/second")
	public String publishToSecondQueue(@RequestBody Message content) {
		producerService.publishToSecondQueue(content);
		return "Sent Successfully to Second Queue!!";
	}

}
