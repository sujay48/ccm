package com.cognizant.producer.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
	
	private static final Integer PRIORITY = 2;

	@Value("${first.queue}")
	private String firstQueueName;
	
	@Value("${second.queue}")
	private String secondQueueName;
	
	@Value("${dead.letter.queue}")
	private String deadLetterQueueName;

	@Value("${direct.exchange}")
	private String exchangeName;
	
	@Value("${dead.letter.exchange}")
	private String deadLetterExchange;
	
	@Value("${binding.key.first}")
	private String firstBindingKey;
	
	@Value("${binding.key.second}")
	private String secondBindingKey;
	
	@Value("${binding.key.dead.letter}")
	private String deadLetterBindingKey;

	/**
	 * Bean for Priority Queue having maximum priority of 2
	 * @return
	 */
	@Bean
	public Queue firstQueue() {
		Map<String, Object> args= new HashMap<>();
	    args.put("x-max-priority", PRIORITY);
	    return new Queue(firstQueueName, false, false, false, args);
	}

	/**
	 * Bean for Default Queue
	 * @return
	 */
	@Bean
	public Queue secondQueue() {
		Map<String, Object> args= new HashMap<>();
		args.put("x-dead-letter-exchange", deadLetterExchange);
		args.put("x-dead-letter-routing-key", deadLetterBindingKey);
		return new Queue(secondQueueName, false, false, false, args);
	}
	
	/**
	 * Bean for Dead Letter Queue
	 * @return
	 */
	@Bean 
	public Queue deadLetterQueue() {
		return new Queue(deadLetterQueueName);
	}
	
	/**
	 * Bean for Direct Exchange
	 * @return
	 */
	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(exchangeName);
	}

	/**
	 * Bean for Dead Letter Exchange
	 * @return
	 */
	@Bean
	public DirectExchange deadLetterExchange() {
		return new DirectExchange(deadLetterExchange);
	}
	
	/**
	 * Bean to Bind First Queue to the Exchange
	 * @param firstQueue
	 * @param exchange
	 * @return
	 */
	@Bean
	public Binding firstBinding(Queue firstQueue, DirectExchange exchange) {
		return BindingBuilder.bind(firstQueue).to(exchange).with(firstBindingKey);
	}
	
	/**
	 * Bean to Bind Second Queue to the Exchange
	 * @param secondQueue
	 * @param exchange
	 * @return
	 */
	@Bean
	public Binding secondBinding(Queue secondQueue, DirectExchange exchange) {
		return BindingBuilder.bind(secondQueue).to(exchange).with(secondBindingKey);
	}
	
	/**
	 * Bean to Bind Dead Letter Queue to Dead Letter Exchange
	 * @param deadLetterQueue
	 * @param deadLetterExchange
	 * @return
	 */
	@Bean
	public Binding deadLetterQueueBinding(Queue deadLetterQueue, DirectExchange deadLetterExchange) {
		return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange).with(deadLetterBindingKey);
	}
	/**
	 * Message Converter
	 * @return
	 */
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
    
    /**
     * Bean to set Message Converter
     * @param connectionFactory
     * @return
     */
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
