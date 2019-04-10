package com.albaycan.resourceplanner;

import java.util.List;

public interface InventoryManager {	

	int addProduct(Product product);
	void removeProduct(int id);
	List<Product> getProducts();
	void editProduct(Product product);
	Product getProductById(int id);
	List<Product> getProductByName(String name);
	List<Product> getOutOfStockProducts();
	List<Product> getProductWhichWillSoldOutInAWeek();
	
}
