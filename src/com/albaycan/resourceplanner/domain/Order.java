package com.albaycan.resourceplanner.domain;

import java.time.LocalDateTime;
import java.util.Random;

public class Order  implements java.io.Serializable {
	
	private int id;
	private int productId;
	private double salePrice;
	private double shippingCost;
	private double packagingCost;
	private int qty;
	private String customerName;
	private String customerAddress;
	private String customerPostCode;
	private Country customerCountry;
	private String customerPhone;
	private String customerEmail;
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
	private boolean refunded;
	
	
	public Order(int productId, double salePrice, double shippingCost, double packagingCost, int qty,
			String customerName, String customerAddress, String customerPostCode, Country customerCountry,
			String customerPhone, String customerEmail, boolean refunded) {
		super();
		this.id = new Random().nextInt((Integer.MAX_VALUE));
		this.productId = productId;
		this.salePrice = salePrice;
		this.shippingCost = shippingCost;
		this.packagingCost = packagingCost;
		this.qty = qty;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerPostCode = customerPostCode;
		this.customerCountry = customerCountry;
		this.customerPhone = customerPhone;
		this.customerEmail = customerEmail;
		this.createDateTime = LocalDateTime.now();
		this.updateDateTime = LocalDateTime.now();
		this.refunded = refunded;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
		this.updateDateTime = LocalDateTime.now();
	}


	public double getSalePrice() {
		return salePrice;
	}


	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
		this.updateDateTime = LocalDateTime.now();
	}


	public double getShippingCost() {
		return shippingCost;
	}


	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
		this.updateDateTime = LocalDateTime.now();
	}


	public double getPackagingCost() {
		return packagingCost;
	}


	public void setPackagingCost(double packagingCost) {
		this.packagingCost = packagingCost;
		this.updateDateTime = LocalDateTime.now();
	}


	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
		this.updateDateTime = LocalDateTime.now();
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
		this.updateDateTime = LocalDateTime.now();
	}


	public String getCustomerAddress() {
		return customerAddress;
	}


	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
		this.updateDateTime = LocalDateTime.now();
	}


	public String getCustomerPostCode() {
		return customerPostCode;
	}


	public void setCustomerPostCode(String customerPostCode) {
		this.customerPostCode = customerPostCode;
		this.updateDateTime = LocalDateTime.now();
	}


	public Country getCustomerCountry() {
		return customerCountry;
	}


	public void setCustomerCountry(Country customerCountry) {
		this.customerCountry = customerCountry;
		this.updateDateTime = LocalDateTime.now();
	}


	public String getCustomerPhone() {
		return customerPhone;
	}


	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
		this.updateDateTime = LocalDateTime.now();
	}


	public String getCustomerEmail() {
		return customerEmail;
	}


	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
		this.updateDateTime = LocalDateTime.now();
	}


	public boolean isRefunded() {
		return refunded;
	}


	public void setRefunded(boolean refunded) {
		this.refunded = refunded;
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
	
		

}
