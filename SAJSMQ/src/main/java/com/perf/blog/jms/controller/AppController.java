package com.perf.blog.jms.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.perf.blog.jms.model.Order;
import com.perf.blog.jms.service.OrderService;

@Controller
public class AppController {

	@Autowired
	OrderService orderService;
	
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String prepareProduct(ModelMap model) {
		return "index";
	}

	@RequestMapping(value = { "/newOrder" }, method = RequestMethod.GET)
	public String prepareOrder(ModelMap model) {
		Order order = new Order();
		model.addAttribute("order", order);
		return "order";
	}

	@RequestMapping(value = { "/newOrder" }, method = RequestMethod.POST)
	public String sendOrder(@Valid Order order, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			return "order";
		}
		orderService.sendOrder(order);
		model.addAttribute("success", "Order for " + order.getProductName() + " registered.");
		return "ordersuccess";
	}
	
	@RequestMapping(value = { "/checkStatus" }, method = RequestMethod.GET)
	public String checkOrderStatus(ModelMap model) {
		Map<String,Order> map = orderService.getAllOrders();
		System.out.println(map);
		model.addAttribute("orders", orderService.getAllOrders());
		return "orderStatus";
	}
}
