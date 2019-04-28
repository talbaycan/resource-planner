package com.albaycan.resourceplanner.domain;

import java.time.LocalDateTime;
import java.util.List;

public class AggregatedDailyReport extends ReportTemplate {

	private List<AggregatedDailyReportLineItem> aggregatedDailyReportLineItems;	
	
	public AggregatedDailyReport(int id, String name, ReportTypeEnum type, LocalDateTime createdDateTime, List<AggregatedDailyReportLineItem> aggregatedDailyReportLineItems) {
		super(id, name, type, createdDateTime);
		this.aggregatedDailyReportLineItems = aggregatedDailyReportLineItems;

	}

	public List<AggregatedDailyReportLineItem> getAggregatedDailyReportLineItems() {
		return aggregatedDailyReportLineItems;
	}

	public void setAggregatedDailyReportLineItems(List<AggregatedDailyReportLineItem> aggregatedDailyReportLineItems) {
		this.aggregatedDailyReportLineItems = aggregatedDailyReportLineItems;
	}

	

}
