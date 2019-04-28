package com.albaycan.resourceplanner.domain;

public abstract class ReportLineItemTemplate {

	private int orderCount;
	private int quantitySold;
	private double totalCost;
	private double totalProfit;
	private double profitRatio;
	
	public ReportLineItemTemplate(int orderCount, int quantitySold, double totalCost, double totalProfit,
			double profitRatio) {
		super();
		this.orderCount = orderCount;
		this.quantitySold = quantitySold;
		this.totalCost = totalCost;
		this.totalProfit = totalProfit;
		this.profitRatio = profitRatio;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public int getQuantitySold() {
		return quantitySold;
	}

	public void setQuantitySold(int quantitySold) {
		this.quantitySold = quantitySold;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(double totalProfit) {
		this.totalProfit = totalProfit;
	}

	public String getProfitRatio() {
		return Double.toString(profitRatio)+"%";
	}

	public void setProfitRatio(double profitRatio) {
		this.profitRatio = profitRatio;
	}
	
	

}
