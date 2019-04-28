package com.albaycan.resourceplanner.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.albaycan.resourceplanner.domain.AggregatedCategoryReport;
import com.albaycan.resourceplanner.domain.AggregatedDailyReport;
import com.albaycan.resourceplanner.domain.AggregatedProductReport;
import com.albaycan.resourceplanner.domain.BasicReport;
import com.albaycan.resourceplanner.domain.ReportWithDateFilter;

public interface ReportingManager {

	BasicReport getSalesReport();
	ReportWithDateFilter getSalesReportWithDateFilter(LocalDate startDate, LocalDate endDate);
	AggregatedDailyReport getAggregatedDailyReport();
	AggregatedProductReport getAggregatedProductReport();
	AggregatedCategoryReport getAggregatedCategoryReport();
	
}
