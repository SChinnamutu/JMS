package com.perf.blog.jms.messaging;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.perf.blog.jms.model.InventoryResponse;
import com.perf.blog.jms.model.Order;
import com.perf.blog.jms.service.OrderService;
import com.perf.blog.jms.util.BasicUtil;

@Component
public class MessageReceiver {
	static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);

	private static final String ORDER_RESPONSE_QUEUE = "sample.queue";
	
	@Autowired
	OrderService orderService;
	
	
	@JmsListener(destination = ORDER_RESPONSE_QUEUE)
	public void receiveMessage(javax.jms.Message message) throws JMSException {
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Order order = null;
		String text = null;
		InventoryResponse inventoryResponse = new InventoryResponse();
		try {
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				text = textMessage.getText();
				LOG.info("Received Object : " + text);
				order = BasicUtil.getObjectFromJson(text);
			} else if(message instanceof Object){
				ObjectMessage objMsg = (ObjectMessage) message;
			     if(objMsg.getObject() instanceof Order) {
			    	order = (Order) objMsg.getObject();
			     }
			}
			inventoryResponse.setOrderId(order.getOrderId());
			inventoryResponse.setReturnCode(200);
			LOG.info("Application : response received : {}",message);
		} catch (Exception e) {
			inventoryResponse.setOrderId(BasicUtil.getUniqueId());
			inventoryResponse.setReturnCode(300);
		}	
		orderService.updateOrder(inventoryResponse);
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
}
