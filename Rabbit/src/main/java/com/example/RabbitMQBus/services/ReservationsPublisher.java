package com.example.RabbitMQBus.services;

import org.springframework.amqp.core.AmqpTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class ReservationsPublisher {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("")
	private String exchange;
	
	@Value("")
	private String routingkey;	
	
	public void sendMessage(String reservation) {
		rabbitTemplate.convertAndSend(exchange, routingkey, reservation);
		System.out.println("Send msg = " + reservation);
	}
}