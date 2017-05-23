package com.perf.blog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perf.blog.jms.util.BasicUtil;
import com.perf.blog.message.MessageSender;
import com.perf.blog.model.UserDetails;

@Service("userDetailService")
public class UserDetailServiceImpl {

	static final Logger LOG = LoggerFactory.getLogger(UserDetailServiceImpl.class);
	
	@Autowired
	MessageSender messageSender;
	
	
	public void sendOrder(UserDetails details) {
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		LOG.info("Application : sending order request {}", details);
		try {
			messageSender.sendMessage(BasicUtil.convertToJson(details));
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}

}
