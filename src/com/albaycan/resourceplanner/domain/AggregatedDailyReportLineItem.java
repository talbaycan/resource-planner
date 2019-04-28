package com.albaycan.resourceplanner.domain;

import java.time.LocalDate;

public class AggregatedDailyReportLineItem extends ReportLineItemTemplate {

	private LocalDate date;
	
	public AggregatedDailyReportLineItem(LocalDate date, int orderCount, int quantitySold, double totalCost, double totalProfit,
			double profitRatio) {
		super(orderCount, quantitySold, totalCost, totalProfit, profitRatio);
		this.date = date;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	
	
}
