package com.zpc.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.zpc.order.entity.Order;
import com.zpc.order.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping(value = "order/{orderId}")
	public Order queryOrderById(@PathVariable("orderId") String id) {
		return orderService.queryOrderById(id);
	}
	
	@GetMapping(value = "order2/{orderId}")
	public Order queryOrderById2(@PathVariable("orderId") String id) {
		return orderService.queryOrderByIdx(id);
	}
	
	@GetMapping(value = "order3/{orderId}")
	public Order queryOrderById3(@PathVariable("orderId") String id) {
		return orderService.queryOrderByIdx2(id);
	}
}
