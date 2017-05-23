package com.perf.blog.message;

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

	public void sendMessage(final String order) {
		jmsTemplate.send(new MessageCreator(){
			@Override
			public Message createMessage(Session session) throws JMSException{
				javax.jms.Message message  = session.createTextMessage(order);
				return message;
			}
		 });
	}

}
