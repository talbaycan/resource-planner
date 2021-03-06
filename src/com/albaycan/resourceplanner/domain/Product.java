package com.albaycan.resourceplanner.domain;

import java.time.LocalDateTime;
import java.util.Random;

public class Product implements java.io.Serializable {

	private int id;
	private String SKU;
	private int supplierId;
	private Category category;
	private String name;
	private double buyPrice;
	private double sellPrice;
	private int weight;
	private int stock;
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
	
	public Product (String SKU, int supplierId, Category category, String name, double buyPrice, double sellPrice, int weight, int stock) {
		this.id = new Random().nextInt((Integer.MAX_VALUE));
		this.SKU = SKU;
		this.supplierId = supplierId;
		this.category = category;
		this.name = name;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
		this.weight = weight;
		this.stock = stock;
		this.createDateTime = LocalDateTime.now();
		this.updateDateTime = LocalDateTime.now();
		
	}

	public int getId() {
		return id;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public String getSKU() {
		return SKU;
	}

	public void setSKU(String SKU) {
		this.SKU = SKU;
		this.updateDateTime = LocalDateTime.now();
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
		this.updateDateTime = LocalDateTime.now();
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
		this.updateDateTime = LocalDateTime.now();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.updateDateTime = LocalDateTime.now();
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
		this.updateDateTime = LocalDateTime.now();
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
		this.updateDateTime = LocalDateTime.now();
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
		this.updateDateTime = LocalDateTime.now();
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
		this.updateDateTime = LocalDateTime.now();
	}
	
	
	
}
