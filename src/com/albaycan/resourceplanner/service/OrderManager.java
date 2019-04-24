package com.albaycan.resourceplanner.service;

import java.util.List;

import com.albaycan.resourceplanner.domain.Order;

public interface OrderManager {
	
	int addOrder(Order order);
	void removeOrder(int id);
	int addRefund(int id);
	List<Order> getOrders();
	Order getOrderById(int id);
	List<Order> getOrderByProductId(int productId);
	List<Order> getOrderByProductName(String productName);	
	void editOrder(Order order);
	
}
