package com.albaycan.resourceplanner;

import java.time.LocalDateTime;
import java.util.Random;

enum Category {
	Gift, Toy, Computer
}

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
	
	public Product (String SKU, int supplierId, Category category, String name, double buyPrice, double sellPrice, int weight, int stock, LocalDateTime createDateTime, LocalDateTime updateDateTime) {
		this.id = new Random().nextInt((Integer.MAX_VALUE));
		this.SKU = SKU;
		this.supplierId = supplierId;
		this.category = category;
		this.name = name;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
		this.weight = weight;
		this.stock = stock;
		this.createDateTime = createDateTime;
		this.updateDateTime = updateDateTime;
		
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
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	
	
	
}
