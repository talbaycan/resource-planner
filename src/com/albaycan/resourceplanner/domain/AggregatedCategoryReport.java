package com.albaycan.resourceplanner.domain;

import java.time.LocalDateTime;
import java.util.List;

public class AggregatedCategoryReport extends ReportTemplate {

	private List<AggregatedCategoryReportLineItem> aggregatedCategoryReportLineItems;
	
	public AggregatedCategoryReport(int id, String name, ReportTypeEnum type, LocalDateTime createdDateTime, List<AggregatedCategoryReportLineItem> aggregatedCategoryReportLineItems) {
		super(id, name, type, createdDateTime);
		this.aggregatedCategoryReportLineItems = aggregatedCategoryReportLineItems;
	}

	public List<AggregatedCategoryReportLineItem> getAggregatedCategoryReportLineItems() {
		return aggregatedCategoryReportLineItems;
	}

	public void setAggregatedCategoryReportLineItems(List<AggregatedCategoryReportLineItem> aggregatedCategoryReportLineItems) {
		this.aggregatedCategoryReportLineItems = aggregatedCategoryReportLineItems;
	}

	
	
}
