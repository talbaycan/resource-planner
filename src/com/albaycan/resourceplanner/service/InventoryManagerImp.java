package com.albaycan.resourceplanner.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.albaycan.resourceplanner.domain.Product;

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
		
		return productList.stream().filter(x->x.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
	}

	@Override
	public List<Product> getOutOfStockProducts() {
		
		return productList.stream().filter(x->x.getStock()==0).collect(Collectors.toList());
	}

	@Override
	public List<Product> getProductWhichWillSoldOutInAWeek() {
		
		
		
		return null;
	}

	@Override
	public void increaseStock(int id, int qty) {
		Product product = getProductById(id);
		product.setStock(product.getStock()+qty);
		editProduct(product);
		
	}

	@Override
	public void decreaseStock(int id, int qty) {
		Product product = getProductById(id);
		product.setStock(product.getStock()-qty);
		editProduct(product);
		
	}

	@Override
	public boolean exist(int id) {
		if(getProductById(id)==null) {
			return false;			
		} else return true;
		
	}

}
