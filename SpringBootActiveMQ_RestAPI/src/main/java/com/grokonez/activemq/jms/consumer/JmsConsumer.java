package com.grokonez.activemq.jms.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.grokonez.activemq.model.Customer;
import com.grokonez.activemq.model.MessageStorage;

@Component
public class JmsConsumer {
	@Autowired
	private MessageStorage customerStorage;
	
	@JmsListener(destination = "${gkz.activemq.queue}", containerFactory="jsaFactory")
	public void receive(Customer customer){
		System.out.println("Recieved Message: " + customer);
		customerStorage.add(customer);
	}
}