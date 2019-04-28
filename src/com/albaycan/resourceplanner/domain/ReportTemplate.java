package com.albaycan.resourceplanner.domain;

import java.time.LocalDateTime;
import java.util.Random;

public abstract class ReportTemplate {

	private int id;
	private String name;
	private ReportTypeEnum type;
	private LocalDateTime createdDateTime;
	
	public ReportTemplate(int id, String name, ReportTypeEnum type, LocalDateTime createdDateTime) {
		super();
		this.id = new Random().nextInt(Integer.MAX_VALUE);
		this.name = name;
		this.type = type;	
		this.createdDateTime = LocalDateTime.now();
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ReportTypeEnum getType() {
		return type;
	}

	public void setType(ReportTypeEnum type) {
		this.type = type;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

		
}
