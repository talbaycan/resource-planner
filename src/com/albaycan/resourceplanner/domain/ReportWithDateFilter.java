package com.albaycan.resourceplanner.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReportWithDateFilter extends ReportTemplate implements java.io.Serializable {	
	
	private List<ReportRecord> reportRecords; 
	private int recordCount;
	private LocalDate filterStartDate;
	private LocalDate filterEndDate;		
		
	
	public ReportWithDateFilter(int id, String name, ReportTypeEnum type, LocalDateTime createdDateTime, List<ReportRecord> reportRecords, int recordCount, LocalDate filterStartDate, LocalDate filterEndDate) {
		super(id, name, type, createdDateTime);
		this.reportRecords = reportRecords;
		this.recordCount = recordCount;
		this.filterStartDate = filterStartDate;
		this.filterEndDate = filterEndDate;
	}


	public List<ReportRecord> getReportRecords() {
		return reportRecords;
	}


	public void setReportRecords(List<ReportRecord> reportRecords) {
		this.reportRecords = reportRecords;
	}


	public int getRecordCount() {
		return recordCount;
	}


	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}


	public LocalDate getFilterStartDate() {
		return filterStartDate;
	}


	public void setFilterStartDate(LocalDate filterStartDate) {
		this.filterStartDate = filterStartDate;
	}


	public LocalDate getFilterEndDate() {
		return filterEndDate;
	}


	public void setFilterEndDate(LocalDate filterEndDate) {
		this.filterEndDate = filterEndDate;
	}


	
	
}			