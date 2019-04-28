package com.albaycan.resourceplanner.domain;

public class AggregatedProductReportLineItem extends ReportLineItemTemplate {

	private String productName;
	private String SKU;	
	
	public AggregatedProductReportLineItem(String productName, String SKU, int orderCount, int quantitySold, double totalCost, double totalProfit,
			double profitRatio) {
		super(orderCount, quantitySold, totalCost, totalProfit, profitRatio);
		this.productName = productName;
		this.SKU = SKU;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSKU() {
		return SKU;
	}

	public void setSKU(String sKU) {
		SKU = sKU;
	}

		
}
