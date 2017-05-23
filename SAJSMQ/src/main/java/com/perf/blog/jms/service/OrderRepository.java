package com.perf.blog.jms.service;

import java.util.Map;

import com.perf.blog.jms.model.Order;

public interface OrderRepository {

	public void putOrder(Order order);
	
	public Order getOrder(String orderId);
	
	public Map<String, Order> getAllOrders();
}
