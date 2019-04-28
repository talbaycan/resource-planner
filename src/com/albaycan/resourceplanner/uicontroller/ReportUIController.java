package com.albaycan.resourceplanner.uicontroller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.albaycan.resourceplanner.domain.AggregatedCategoryReport;
import com.albaycan.resourceplanner.domain.AggregatedCategoryReportLineItem;
import com.albaycan.resourceplanner.domain.AggregatedDailyReport;
import com.albaycan.resourceplanner.domain.AggregatedDailyReportLineItem;
import com.albaycan.resourceplanner.domain.AggregatedProductReport;
import com.albaycan.resourceplanner.domain.AggregatedProductReportLineItem;
import com.albaycan.resourceplanner.domain.BasicReport;
import com.albaycan.resourceplanner.domain.ReportRecord;
import com.albaycan.resourceplanner.domain.ReportWithDateFilter;
import com.albaycan.resourceplanner.service.OrderManager;
import com.albaycan.resourceplanner.service.ReportingManager;

public class ReportUIController implements UIController {

	private Scanner input = new Scanner(System.in);
	private ReportingManager reportingManager;
	
	public ReportUIController(ReportingManager reportingManager) {
		this.reportingManager = reportingManager;
	}

	@Override
	public String showMenu() throws ParseException {
		String endOfMenu;
		do {
			
		System.out.println(reportManagerMenuText());
		int menuChoice = input.nextInt();
		input.nextLine();
		
		if(menuChoice==1) {
			getSalesReport();
		} else if (menuChoice==2) {
			getSalesReportWithDateFilter();
		} else if (menuChoice==3) {
			getAggregatedDailyReport();
		} else if (menuChoice==4) {
			getAggregatedProductReport();
		} else if (menuChoice==5) {
			getAggregatedCategoryReport();
		}
		
		System.out.println("Press ‘B’ to go back to Report Manager Menu or ‘*’ to go to Main Menu");
		endOfMenu = input.nextLine();
		
		} while(endOfMenu.equals("B"));
		
		return endOfMenu;
		
	}
	
	private String reportManagerMenuText() {
		StringBuilder sb = new StringBuilder();
		sb.append("***********Report Manager************\n");
		sb.append("Please select an operation below:\n");
		sb.append("       (1)Get Sales Report\n");
		sb.append("       (2)Get Sales Report Between Dates\n");
		sb.append("       (3)Get Aggregated Daily Sales Report\n");
		sb.append("       (4)Get Aggregated Product Sales Report\n");
		sb.append("       (5)Get Aggregated Category Sales Report\n");
		
		return toString();
	}
	
	
	
	private void getSalesReport() {
		
		BasicReport basicReport = reportingManager.getSalesReport();
		List<ReportRecord> reportRecords = basicReport.getReportRecords();
		
		System.out.println("***********Sales Report************");
		System.out.printf("Report Id: %d", basicReport.getId()).println();
		System.out.printf("Report Name: %s", basicReport.getName());
		System.out.printf("Report Creation Date: %t", basicReport.getCreatedDateTime());
		System.out.printf("Number of Orders: %d", basicReport.getRecordCount());
		
		if(reportRecords==null) {
			System.out.println("No Order/s found");
		} else {		
			
		String leftAlignFormat = "%-10d | %-25t           | %-20s      | %-20f      | %-20f      | %-20f    | %-20s      %n";

		System.out.format(
				"------------|---------------------|----------------|----------------|----------------|------------|---------------|%n");
		System.out.format(
				" Order Id   | Order Date          | Product Name   | Sale Price (£) | Total Cost (£) | Profit (£) | Profit Ratio  |%n");
		System.out.format(
				"------------|---------------------|----------------|----------------|----------------|------------|---------------|%n");
		
			for(ReportRecord report : reportRecords) {
			
				System.out.format(leftAlignFormat, report.getOrderId(), report.getOrderDate(), report.getProductName(), report.getSalePrice(), report.getTotalCost(), report.getProfit(), report.getProfitRatio());
			}
		}
		
	}


	private void getSalesReportWithDateFilter() throws ParseException {
		
		System.out.println("***********Sales Report Between Dates************");
		
		System.out.println("Enter Start Date (Please enter the date in 'dd/MM/yyyy' format):");
		String startDateStr = input.nextLine();
		Date strDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDateStr);
		LocalDate startDate = strDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
		System.out.println("Enter End Date (Please enter the date in 'dd/MM/yyyy' format):");
		String endDateStr = input.nextLine();
		Date enDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDateStr);
		LocalDate endDate = enDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		ReportWithDateFilter reportWithDateFilter = reportingManager.getSalesReportWithDateFilter(startDate, endDate);
		List<ReportRecord> reportRecords = reportWithDateFilter.getReportRecords();		

		System.out.printf("Report Id: %d", reportWithDateFilter.getId()).println();
		System.out.printf("Report Name: %s", reportWithDateFilter.getName()).println();
		System.out.printf("Report Creation Date: %t", reportWithDateFilter.getCreatedDateTime()).println();
		System.out.printf("Filter Start Date: %s", startDateStr).println();
		System.out.printf("Filter End Date: %s", endDateStr).println();
		System.out.printf("Number of Orders: %d", reportWithDateFilter.getRecordCount()).println();
		
		if(reportRecords==null) {
			System.out.println("No Order/s found");
		} else {		
			
		String leftAlignFormat = "%-10d | %-25t           | %-20s      | %-20f      | %-20f      | %-20f    | %-20s      %n";

		System.out.format(
				"------------|---------------------|----------------|----------------|----------------|------------|---------------|%n");
		System.out.format(
				" Order Id   | Order Date          | Product Name   | Sale Price (£) | Total Cost (£) | Profit (£) | Profit Ratio  |%n");
		System.out.format(
				"------------|---------------------|----------------|----------------|----------------|------------|---------------|%n");
		
			for(ReportRecord report : reportRecords) {
			
				System.out.format(leftAlignFormat, report.getOrderId(), report.getOrderDate(), report.getProductName(), report.getSalePrice(), report.getTotalCost(), report.getProfit(), report.getProfitRatio());
			}
		}
				
	}

	
	private void getAggregatedDailyReport() {
		
		AggregatedDailyReport aggregatedDailyReport = reportingManager.getAggregatedDailyReport();
		List<AggregatedDailyReportLineItem> aggregatedDailyReportLineItems = aggregatedDailyReport.getAggregatedDailyReportLineItems();
		
		int orderCount=0;
		
		System.out.println("***********Aggregated Daily Sales Reports************");		
		System.out.printf("Report Id: %d", aggregatedDailyReport.getId()).println();
		System.out.printf("Report Name: %s", aggregatedDailyReport.getName());
		System.out.printf("Report Creation Date: %t", aggregatedDailyReport.getCreatedDateTime());
		System.out.printf("Number of Orders: %d", orderCount);

		if(aggregatedDailyReportLineItems==null) {
			System.out.println("No Order/s found");
		} else {		
			
		String leftAlignFormat = "%-15d   | %-20t     | %-20s      | %-20f      | %-20f      | %-20f    %n";

		System.out.format(
				"------------|-------------|---------------|----------------|------------------|--------------|%n");
		System.out.format(
				" Day        | Order Count | Quantity Sold | Total Cost (£) | Total Profit (£) | Profit Ratio |%n");
		System.out.format(
				"------------|-------------|---------------|----------------|------------------|--------------|%n");
		
			for(AggregatedDailyReportLineItem report : aggregatedDailyReportLineItems) {
			
				System.out.format(leftAlignFormat, report.getDate(), report.getOrderCount(), report.getQuantitySold(), report.getTotalCost(), report.getTotalProfit(), report.getProfitRatio());
				orderCount = orderCount + report.getOrderCount();
			}
		}
		
	}
	
	
	private void getAggregatedProductReport() {
		
		AggregatedProductReport aggregatedProductReport = reportingManager.getAggregatedProductReport();
		List<AggregatedProductReportLineItem> aggregatedProductReportLineItems = aggregatedProductReport.getAggregatedProductReportLineItems();
		
		int orderCount=0;
		
		System.out.println("***********Aggregated Product Sales Reports************");		
		System.out.printf("Report Id: %d", aggregatedProductReport.getId()).println();
		System.out.printf("Report Name: %s", aggregatedProductReport.getName());
		System.out.printf("Report Creation Date: %t", aggregatedProductReport.getCreatedDateTime());
		System.out.printf("Number of Orders: %d", orderCount);

		if(aggregatedProductReportLineItems==null) {
			System.out.println("No Order/s found");
		} else {		
			
		String leftAlignFormat = "%-20d   | %-20t     | %-20t     | %-20s      | %-20f      | %-20f      | %-20f    %n";

		System.out.format(
				"----------------|-----------|-------------|---------------|----------------|------------------|--------------|%n");
		System.out.format(
				" Product Name   | SKU       | Order Count | Quantity Sold | Total Cost (£) | Total Profit (£) | Profit Ratio |%n");
		System.out.format(
				"----------------|-----------|-------------|---------------|----------------|------------------|--------------|%n");
		
			for(AggregatedProductReportLineItem report : aggregatedProductReportLineItems) {
			
				System.out.format(leftAlignFormat, report.getProductName(), report.getSKU(), report.getOrderCount(), report.getQuantitySold(), report.getTotalCost(), report.getTotalProfit(), report.getProfitRatio());
				orderCount = orderCount + report.getOrderCount();
			}
		}
		
	}
	
	
	private void getAggregatedCategoryReport() {
		
		AggregatedCategoryReport aggregatedCategoryReport = reportingManager.getAggregatedCategoryReport();
		List<AggregatedCategoryReportLineItem> aggregatedCategoryReportLineItems = aggregatedCategoryReport.getAggregatedCategoryReportLineItems();
		
		int orderCount=0;
		
		System.out.println("***********Aggregated Category Sales Reports************");		
		System.out.printf("Report Id: %d", aggregatedCategoryReport.getId()).println();
		System.out.printf("Report Name: %s", aggregatedCategoryReport.getName());
		System.out.printf("Report Creation Date: %t", aggregatedCategoryReport.getCreatedDateTime());
		System.out.printf("Number of Orders: %d", orderCount);

		if(aggregatedCategoryReportLineItems==null) {
			System.out.println("No Order/s found");
		} else {		
			
		String leftAlignFormat = "%-20d   | %-20t     | %-20s      | %-20f      | %-20f      | %-20f    %n";

		System.out.format(
				"--------------|-------------|---------------|----------------|------------------|--------------|%n");
		System.out.format(
				" Category     | Order Count | Quantity Sold | Total Cost (£) | Total Profit (£) | Profit Ratio |%n");
		System.out.format(
				"--------------|-------------|---------------|----------------|------------------|--------------|%n");
		
			for(AggregatedCategoryReportLineItem report : aggregatedCategoryReportLineItems) {
			
				System.out.format(leftAlignFormat, report.getCategory(), report.getOrderCount(), report.getQuantitySold(), report.getTotalCost(), report.getTotalProfit(), report.getProfitRatio());
				orderCount = orderCount + report.getOrderCount();
			}
		}
		
	}

	


}
