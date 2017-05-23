package com.perf.blog.message;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.perf.blog.jms.util.BasicUtil;
import com.perf.blog.model.UserDetails;

@Component
public class MessageReceiver {
	
	static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);

	private static final String ORDER_RESPONSE_QUEUE = "sample.queue";
	
	
	@JmsListener(destination = ORDER_RESPONSE_QUEUE)
	public void receiveMessage(javax.jms.Message message) throws JMSException {
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		UserDetails userDetails = null;
		String text = null;
		try {
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				text = textMessage.getText();
				userDetails = BasicUtil.getObjectFromJson(text);
			} else if(message instanceof Object){
				ObjectMessage objMsg = (ObjectMessage) message;
			     if(objMsg.getObject() instanceof UserDetails) {
			    	userDetails = (UserDetails) objMsg.getObject();
			     }
			}
			LOG.info("Received Object : " + userDetails);
		} catch (Exception e) {
		}	
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
}
