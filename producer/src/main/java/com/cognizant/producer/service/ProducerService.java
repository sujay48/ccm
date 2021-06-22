package com.cognizant.producer.service;

import com.cognizant.producer.model.Message;

public interface ProducerService {
	void publishToFirstQueue(Message message, Integer priority);

	void publishToSecondQueue(Message message);
}
