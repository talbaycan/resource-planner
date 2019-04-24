package com.albaycan.resourceplanner.service;

import java.util.List;

import com.albaycan.resourceplanner.domain.Product;

public interface InventoryManager {	

	int addProduct(Product product);
	void removeProduct(int id);
	List<Product> getProducts();
	void editProduct(Product product);
	Product getProductById(int id);
	List<Product> getProductByName(String name);
	List<Product> getOutOfStockProducts();
	List<Product> getProductWhichWillSoldOutInAWeek();
	void increaseStock(int id, int qty);
	void decreaseStock(int id, int qty);
	boolean exist(int id);
	
}
