package com.albaycan.resourceplanner.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReportWithDateFilter implements java.io.Serializable {
	
	private int id;
	private String name;
	private String type;
	private ReportRecord reportRecords;
	private LocalDateTime createdDateTime;
	private LocalDate filterStartDate;
	private LocalDate filterEndDate;
	
	
	public ReportWithDateFilter(String name, String type, ReportRecord reportRecords, LocalDate filterStartDate,
			LocalDate filterEndDate) {
		super();
		this.name = name;
		this.type = type;
		this.reportRecords = reportRecords;
		this.filterStartDate = filterStartDate;
		this.filterEndDate = filterEndDate;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public ReportRecord getReportRecords() {
		return reportRecords;
	}


	public void setReportRecords(ReportRecord reportRecords) {
		this.reportRecords = reportRecords;
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


	public int getId() {
		return id;
	}


	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}
	
	
	
	
}			