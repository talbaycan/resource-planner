package com.albaycan.resourceplanner.service;

import java.util.List;

import com.albaycan.resourceplanner.domain.Order;

public interface OrderManager {
	
	int addOrder(Order order);
	void removeOrder(int id);
	void addRefund(int orderId);
	List<Order> getOrders();
	Order getOrderById(int id);
	List<Order> getOrdersByProductId(int productId);
	List<Order> getOrdersByProductName(String productName);	
	void editOrder(Order order);
}
