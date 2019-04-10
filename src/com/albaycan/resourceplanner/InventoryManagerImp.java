package com.albaycan.resourceplanner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryManagerImp implements InventoryManager {

	List<Product> productList = new ArrayList<Product>();
	
	@Override
	public int addProduct(Product product) {
		
		productList.add(product);
		
		return product.getId();
	}

	@Override
	public void removeProduct(int id) {
		
		productList.removeIf(x->x.getId()==id);
		
	}

	@Override
	public List<Product> getProducts() {
		
		return productList;
	}

	@Override
	public void editProduct(Product product) {
	
		removeProduct(product.getId());
		addProduct(product);

	}

	@Override
	public Product getProductById(int id) {

		return productList.stream().filter(x->x.getId()==id).findFirst().get();
	}

	@Override
	public List<Product> getProductByName(String name) {
		
		return productList.stream().filter(x->x.getName().contains(name)).distinct().collect(Collectors.toList());
	}

	@Override
	public List<Product> getOutOfStockProducts() {
		
		return productList.stream().filter(x->x.getStock()==0).distinct().collect(Collectors.toList());
	}

	@Override
	public List<Product> getProductWhichWillSoldOutInAWeek() {
		// TODO Auto-generated method stub
		return null;
	}

}
