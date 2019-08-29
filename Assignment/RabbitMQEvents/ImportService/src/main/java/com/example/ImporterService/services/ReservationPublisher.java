package com.example.ImporterService.services;

import org.springframework.amqp.core.AmqpTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.ImporterService.models.Reservation;



@Service
public class ReservationPublisher {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("${reservations.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${reservations.rabbitmq.routingkey}")
	private String routingkey;	
	
	public void sendMessage(Reservation reservation) {
		rabbitTemplate.convertAndSend(exchange, routingkey, reservation);
		System.out.println("Send msg = " + reservation);
	}
}