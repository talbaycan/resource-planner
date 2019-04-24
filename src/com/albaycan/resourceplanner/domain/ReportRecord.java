package com.albaycan.resourceplanner.domain;

import java.time.LocalDateTime;

public class ReportRecord implements java.io.Serializable {


	private int orderId;
	private LocalDateTime orderDate;
	private String productName;
	private double salePrice;
	private double totalCost;
	private double profit;
	private double profitRatio;

	
	public ReportRecord(int orderId, LocalDateTime orderDate, String productName, double salePrice, double totalCost,
			double profit, double profitRatio) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.productName = productName;
		this.salePrice = salePrice;
		this.totalCost = totalCost;
		this.profit = profit;
		this.profitRatio = profitRatio;
	}


	
	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public LocalDateTime getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public double getSalePrice() {
		return salePrice;
	}


	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}


	public double getTotalCost() {
		return totalCost;
	}


	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}


	public double getProfit() {
		return profit;
	}


	public void setProfit(double profit) {
		this.profit = profit;
	}


	public double getProfitRatio() {
		return profitRatio;
	}


	public void setProfitRatio(double profitRatio) {
		this.profitRatio = profitRatio;
	}
	
	
	
	
}
