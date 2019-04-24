package com.albaycan.resourceplanner.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.albaycan.resourceplanner.domain.Order;
import com.albaycan.resourceplanner.domain.Product;

public class OrderManagerImp implements OrderManager {
	
	List<Order> orderList = new ArrayList<Order>();
	private InventoryManager inventoryManager = new InventoryManagerImp();
			
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
	public int addRefund(int id) {			
		
		Order order = getOrderById(id);
		order.setRefunded(true);
		
		orderList.removeIf(x->x.getId()==id);
		orderList.add(order);				
		
		inventoryManager.increaseStock(order.getProductId(), order.getQty());
		
		return 0;
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
	public List<Order> getOrderByProductId(int productId) {
		
		return orderList.stream().filter(x->x.getProductId()==productId).collect(Collectors.toList());
	}

	@Override
	public List<Order> getOrderByProductName(String productName) {
		
		List<Product> list = inventoryManager.getProductByName(productName);
		Product product = list.stream().filter(x->x.getName().toLowerCase().contains(productName.toLowerCase())).findFirst().get();
		int productId = product.getId();
		
		return getOrderByProductId(productId);
	}

	@Override
	public void editOrder(Order order) {
		
		removeOrder(order.getId());
		addOrder(order);
		
	}


}
