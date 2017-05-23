package com.perf.blog.jms.service;

import java.util.Map;

import com.perf.blog.jms.model.InventoryResponse;
import com.perf.blog.jms.model.Order;

public interface OrderService {
	public void sendOrder(Order order);
	
	public void updateOrder(InventoryResponse response);
	
	public Map<String, Order> getAllOrders();
}
