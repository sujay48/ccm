package com.cognizant.consumer.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.cognizant.consumer.exception.CustomException;
import com.cognizant.consumer.model.Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class QueueConsumer {

	/**
	 * Consumer Listening to First Queue
	 * @param message
	 */
	@RabbitListener(queues = "${first.queue}")
	public void consumeMessageFromFirstQueue(Message message) {
		log.info("Message from first queue : " + message.getDescription());
	}
	
	/**
	 * Consumer Listening to Second Queue
	 * @param message
	 */
	@RabbitListener(queues = "${second.queue}")
	public void consumeMessageFromSecondQueue(Message message) throws CustomException {
		log.info("Message Recieved By Consumer from Second Queue");
		if(message.getDescription().length() == 0) {
			throw new CustomException();
		}
		log.info("Message from second queue : " + message.getDescription());
	}
}
