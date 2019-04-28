package com.albaycan.resourceplanner.domain;

public class AggregatedCategoryReportLineItem extends ReportLineItemTemplate {

	private Category category;
	
	public AggregatedCategoryReportLineItem(Category category, int orderCount, int quantitySold, double totalCost, double totalProfit,
			double profitRatio) {
		super(orderCount, quantitySold, totalCost, totalProfit, profitRatio);
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
}
