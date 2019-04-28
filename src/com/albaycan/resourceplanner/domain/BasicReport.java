package com.albaycan.resourceplanner.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class BasicReport extends ReportTemplate implements java.io.Serializable {

	private List<ReportRecord> reportRecords;
	private int recordCount;
		
	public BasicReport(int id, String name, ReportTypeEnum type, LocalDateTime createdDateTime, List<ReportRecord> reportRecords, int recordCount) {
		super(id, name, type, createdDateTime);
		this.reportRecords = reportRecords;
		this.recordCount = recordCount;
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
	
	
	
	
}
