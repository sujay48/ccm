package com.cognizant.producer.service;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cognizant.producer.model.Message;

@Service
public class ProducerServiceImpl implements ProducerService {

	@Value("${binding.key.first}")
	private String firstBindingKey;

	@Value("${binding.key.second}")
	private String secondBindingKey;

	@Autowired
	private DirectExchange exchange;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	/**
	 * Publishes message to First Queue
	 */
	@Override
	public void publishToFirstQueue(Message content, Integer priority) {
		rabbitTemplate.convertAndSend(exchange.getName(), firstBindingKey, content, message -> {
			message.getMessageProperties().setPriority(priority);
			return message;
		});
	}

	/**
	 * Publishes message to Second Queue
	 */
	@Override
	public void publishToSecondQueue(Message message) {
		rabbitTemplate.convertAndSend(exchange.getName(), secondBindingKey, message);
	}

}
