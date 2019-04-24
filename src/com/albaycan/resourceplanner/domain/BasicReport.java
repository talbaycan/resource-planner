package com.albaycan.resourceplanner.domain;

import java.time.LocalDateTime;
import java.util.Random;

public class BasicReport implements java.io.Serializable {

	private int id;
	private String name;
	private String type;
	private ReportRecord reportRecords;
	private int recordCount;
	private LocalDateTime createdDateTime;
	
	public BasicReport(int id, String name, String type, ReportRecord reportRecords, int recordCount, LocalDateTime createdDateTime) {
		super();
		this.id = new Random().nextInt(Integer.MAX_VALUE);
		this.name = name;
		this.type = type;
		this.reportRecords = reportRecords;
		this.recordCount = recordCount;
		this.createdDateTime = createdDateTime;
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

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getId() {
		return id;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}
	
	
	
}
