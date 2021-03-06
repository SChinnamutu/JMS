package com.perf.blog.jms.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

	@Autowired
	JmsTemplate jmsTemplate;

	//public void sendMessage(final Order order) {
	public void sendMessage(final String order) {
		jmsTemplate.send(new MessageCreator(){
				@Override
				public Message createMessage(Session session) throws JMSException{
					//ObjectMessage objectMessage = session.createObjectMessage(order);
					javax.jms.Message message  = session.createTextMessage(order);
					return message;
				}
			});
	}

}
