package com.albaycan.resourceplanner.domain;

import java.time.LocalDateTime;
import java.util.List;

public class AggregatedProductReport extends ReportTemplate{
	
	private List<AggregatedProductReportLineItem> aggregatedProductReportLineItems;

	public AggregatedProductReport(int id, String name, ReportTypeEnum type, List<AggregatedProductReportLineItem> aggregatedProductReportLineItems, LocalDateTime createdDateTime) {
		super(id, name, type, createdDateTime);
		this.aggregatedProductReportLineItems = aggregatedProductReportLineItems;
	}

	public List<AggregatedProductReportLineItem> getAggregatedProductReportLineItems() {
		return aggregatedProductReportLineItems;
	}

	public void setAggregatedProductReportLineItems(List<AggregatedProductReportLineItem> aggregatedProductReportLineItems) {
		this.aggregatedProductReportLineItems = aggregatedProductReportLineItems;
	}

	

}
