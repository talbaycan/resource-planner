package com.albaycan.resourceplanner;

import java.util.ArrayList;
import java.util.List;

public class InventoryManagerImp implements InventoryManager {

	List<Product> productList = new ArrayList<Product>();
	
	@Override
	public int addProduct(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeProduct(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Product> getProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editProduct(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public Product getProductById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getOutOfStockProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductWhichWillSoldOutInAWeek() {
		// TODO Auto-generated method stub
		return null;
	}

}
