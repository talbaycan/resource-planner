package com.albaycan.resourceplanner.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.albaycan.resourceplanner.domain.Order;
import com.albaycan.resourceplanner.domain.Product;

public class OrderManagerImp implements OrderManager {
	
	List<Order> orderList = new ArrayList<Order>();
	private InventoryManager inventoryManager;
			
	public OrderManagerImp(InventoryManager inventoryManager) {
		this.inventoryManager = inventoryManager;
	}

	@Override
	public int addOrder(Order order) {
		
		orderList.add(order);
		inventoryManager.decreaseStock(order.getProductId(), order.getQty());
		
		return order.getId();
	}

	@Override
	public void removeOrder(int id) {		
		
		for (Order order: orderList) {	
			if(order.getId()==id) {
				orderList.remove(order);
				inventoryManager.increaseStock(order.getProductId(), order.getQty());
			}		
		}
	}

	@Override
	public void addRefund(int id) {			
		
		Order order = getOrderById(id);
		order.setRefunded(true);
		
		editOrder(order);				
		
		inventoryManager.increaseStock(order.getProductId(), order.getQty());		
		
	}

	@Override
	public List<Order> getOrders() {
		
		return orderList;
	}

	@Override
	public Order getOrderById(int id) {
		
		return orderList.stream().filter(x->x.getId()==id).findFirst().get();
	}

	@Override
	public List<Order> getOrdersByProductId(int productId) {
		
		return orderList.stream().filter(x->x.getProductId()==productId).collect(Collectors.toList());
	}

	@Override
	public List<Order> getOrdersByProductName(String productName) {
		
		List<Product> filteredProducts= inventoryManager.getProductByName(productName);
		
		List<Order> filteredOrders=new ArrayList<Order>();
		
		for (Product product : filteredProducts) {
			List<Order> orders=	getOrdersByProductId(product.getId());
			filteredOrders.addAll(orders);
		}
		
		return filteredOrders;
	}

	@Override
	public void editOrder(Order order) {
		
		removeOrder(order.getId());
		addOrder(order);
		
	}


}
