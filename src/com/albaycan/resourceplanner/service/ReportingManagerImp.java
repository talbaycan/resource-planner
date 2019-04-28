package com.albaycan.resourceplanner.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.albaycan.resourceplanner.domain.AggregatedCategoryReport;
import com.albaycan.resourceplanner.domain.AggregatedCategoryReportLineItem;
import com.albaycan.resourceplanner.domain.AggregatedDailyReport;
import com.albaycan.resourceplanner.domain.AggregatedDailyReportLineItem;
import com.albaycan.resourceplanner.domain.AggregatedProductReport;
import com.albaycan.resourceplanner.domain.AggregatedProductReportLineItem;
import com.albaycan.resourceplanner.domain.BasicReport;
import com.albaycan.resourceplanner.domain.Category;
import com.albaycan.resourceplanner.domain.Order;
import com.albaycan.resourceplanner.domain.Product;
import com.albaycan.resourceplanner.domain.ReportRecord;
import com.albaycan.resourceplanner.domain.ReportTypeEnum;
import com.albaycan.resourceplanner.domain.ReportWithDateFilter;

public class ReportingManagerImp implements ReportingManager {

	private InventoryManager inventoryManager=null;
	private OrderManager orderManager=null;
	
	
	public ReportingManagerImp(InventoryManager inventoryManager, OrderManager orderManager) {
		this.inventoryManager = inventoryManager;
		this.orderManager = orderManager;
	}

	List<Order> ordersList = orderManager.getOrders();

	@Override
	public BasicReport getSalesReport() {		
		
		int id = new Random().nextInt(Integer.MAX_VALUE);
		String name = "Full Sales Report";
		ReportTypeEnum type = ReportTypeEnum.valueOf("BasicReport");
		LocalDateTime createdDateTime = LocalDateTime.now();
		List<ReportRecord> reportRecords = new ArrayList<ReportRecord>();	
		int recordCount = 0;
				
		for (Order order : ordersList) {
			
			Product product = inventoryManager.getProductById(order.getProductId());
			
			int orderId = order.getId();
			LocalDateTime orderDate = order.getCreateDateTime();
			String productName= product.getName();
			double salePrice = order.getSalePrice();
			double totalCost = (product.getBuyPrice()+order.getShippingCost()+order.getPackagingCost());
			double profit = salePrice - totalCost;
			double profitRatio = 100*(profit/totalCost);
			
			ReportRecord reportRecord = new ReportRecord(orderId, orderDate, productName, salePrice, totalCost, profit, profitRatio);
			reportRecords.add(reportRecord);
			recordCount++;

		}
		
		BasicReport basicReport = new BasicReport(id, name, type, createdDateTime, reportRecords, recordCount);
		
		
		return basicReport;
	}

	@Override
	public ReportWithDateFilter getSalesReportWithDateFilter(LocalDate startDate, LocalDate endDate) {
		
		LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.of(00,00,00,00000));
		LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.of(23,59,59,99999));
		
		List<Order> ordersListWithDateFilter = ordersList.stream().filter(order->order.getCreateDateTime().isAfter(startDateTime)).filter(order->order.getCreateDateTime().isBefore(endDateTime)).collect(Collectors.toList());
		
		int id = new Random().nextInt(Integer.MAX_VALUE);
		String name = "Full Sales Report";
		ReportTypeEnum type = ReportTypeEnum.valueOf("ReportWithDateFilter");
		LocalDateTime createdDateTime = LocalDateTime.now();
		List<ReportRecord> reportRecords = new ArrayList<ReportRecord>();	
		int recordCount = 0;
				
		for (Order order : ordersListWithDateFilter) {
			
			Product product = inventoryManager.getProductById(order.getProductId());
			
			int orderId = order.getId();
			LocalDateTime orderDate = order.getCreateDateTime();
			String productName= product.getName();
			double salePrice = order.getSalePrice();
			double totalCost = (product.getBuyPrice()+order.getShippingCost()+order.getPackagingCost());
			double profit = salePrice - totalCost;
			double profitRatio = 100*(profit/totalCost);
			
			ReportRecord reportRecord = new ReportRecord(orderId, orderDate, productName, salePrice, totalCost, profit, profitRatio);
			reportRecords.add(reportRecord);
			recordCount++;

		}
		
		ReportWithDateFilter reportWithDateFilter = new ReportWithDateFilter(id, name, type, createdDateTime, reportRecords, recordCount, startDate, endDate);
		
		
		return reportWithDateFilter;
	}

	@Override
	public AggregatedDailyReport getAggregatedDailyReport() {
		
		int id = new Random().nextInt(Integer.MAX_VALUE);
		String name = "Aggregated Daily Sales Report";
		ReportTypeEnum type = ReportTypeEnum.valueOf("AggregatedDailyReport"); 
		LocalDateTime createdDateTime = LocalDateTime.now();
		List<AggregatedDailyReportLineItem> aggregatedDailyReportLineItems = new ArrayList<AggregatedDailyReportLineItem>();
		
		List<LocalDate> orderDateList = new ArrayList<LocalDate>();		
		
		for(Order order : ordersList) {
			
			LocalDate date = order.getCreateDateTime().toLocalDate();
			
			if(!orderDateList.contains(date))
				orderDateList.add(date);				
		}
		
		for(LocalDate localDate: orderDateList) {
			
			LocalDate date = localDate;			
			
			for (Order orderr : ordersList) {
				
				Product product = inventoryManager.getProductById(orderr.getProductId());

				int orderCount=0;
				int quantitySold= 0;
				double totalCost = 0.0;
				double totalProfit = 0.0;
				double profitRatio = 0.0;
				
				if(orderr.getCreateDateTime().toLocalDate().compareTo(localDate)==0) {
		
					orderCount++;
					
					quantitySold = quantitySold + orderr.getQty();
					
					totalCost = totalCost + product.getBuyPrice() + orderr.getShippingCost() + orderr.getPackagingCost();
					
					totalProfit = totalProfit + (orderr.getSalePrice()-(product.getBuyPrice() + orderr.getShippingCost() + orderr.getPackagingCost()));					
															
				}
				
				profitRatio = 100 * (totalProfit/totalCost);				
				
				AggregatedDailyReportLineItem aggregatedDailyReportLineItem = new AggregatedDailyReportLineItem(date, orderCount, quantitySold, totalCost, totalProfit, profitRatio);
				aggregatedDailyReportLineItems.add(aggregatedDailyReportLineItem);
			}
			
			
		}
				
		AggregatedDailyReport aggregatedDailyReport = new AggregatedDailyReport(id, name, type, createdDateTime, aggregatedDailyReportLineItems);		
		
		return aggregatedDailyReport;
	}

	@Override
	public AggregatedProductReport getAggregatedProductReport() {
		
		int id = new Random().nextInt(Integer.MAX_VALUE);
		String name = "Aggregated Product Sales Report";
		ReportTypeEnum type = ReportTypeEnum.valueOf("AggregatedProductReport"); 
		LocalDateTime createdDateTime = LocalDateTime.now();
		List<AggregatedProductReportLineItem> aggregatedProductReportLineItems = new ArrayList<AggregatedProductReportLineItem>();
		
		List<Integer> productIdList = new ArrayList<Integer>();
		
		for(Order order : ordersList) {
			if(!productIdList.contains(order.getProductId()))
				productIdList.add(order.getProductId());
		}
		
		for(Integer productId:productIdList) {
			
			for (Order orderr : ordersList) {
				
				Product product = inventoryManager.getProductById(orderr.getProductId());
				
				String productName = product.getName();
				String SKU = product.getSKU();
				int orderCount=0;
				int quantitySold= 0;
				double totalCost = 0.0;
				double totalProfit = 0.0;
				double profitRatio = 0.0;
				
				if(orderr.getProductId()==productId) {
		
					orderCount++;
					
					quantitySold = quantitySold + orderr.getQty();
					
					totalCost = totalCost + product.getBuyPrice() + orderr.getShippingCost() + orderr.getPackagingCost();
					
					totalProfit = totalProfit + (orderr.getSalePrice()-(product.getBuyPrice() + orderr.getShippingCost() + orderr.getPackagingCost()));					
															
				}
				
				profitRatio = 100 * (totalProfit/totalCost);				
				
				AggregatedProductReportLineItem aggregatedProductReportLineItem = new AggregatedProductReportLineItem(productName, SKU, orderCount, quantitySold, totalCost, totalProfit, profitRatio);
				aggregatedProductReportLineItems.add(aggregatedProductReportLineItem);
			}

		}		
		
		AggregatedProductReport aggregatedProductReport = new AggregatedProductReport(id, name, type, aggregatedProductReportLineItems, createdDateTime);
		
		return aggregatedProductReport;
	}

	@Override
	public AggregatedCategoryReport getAggregatedCategoryReport() {
		
		int id = new Random().nextInt(Integer.MAX_VALUE);
		String name = "Aggregated Category Sales Report";
		ReportTypeEnum type = ReportTypeEnum.valueOf("AggregatedCategoryReport"); 
		LocalDateTime createdDateTime = LocalDateTime.now();
		List<AggregatedCategoryReportLineItem> aggregatedCategoryReportLineItems = new ArrayList<AggregatedCategoryReportLineItem>();
		
		List<Category> categoryList = new ArrayList<Category>();
		
		for(Order order : ordersList) {
			
			Product product = inventoryManager.getProductById(order.getProductId());
			
			if(!categoryList.contains(product.getCategory()))
				categoryList.add(product.getCategory());
		}
		
		for(Category ctg : categoryList) {
			
			for (Order orderr : ordersList) {
				
				Product product = inventoryManager.getProductById(orderr.getProductId());
				
				Category category = null;
				int orderCount=0;
				int quantitySold= 0;
				double totalCost = 0.0;
				double totalProfit = 0.0;
				double profitRatio = 0.0;
				
				if(product.getCategory().equals(ctg)) {
					
					category = product.getCategory();
		
					orderCount++;
					
					quantitySold = quantitySold + orderr.getQty();
					
					totalCost = totalCost + product.getBuyPrice() + orderr.getShippingCost() + orderr.getPackagingCost();
					
					totalProfit = totalProfit + (orderr.getSalePrice()-(product.getBuyPrice() + orderr.getShippingCost() + orderr.getPackagingCost()));					
															
				}
				
				profitRatio = 100 * (totalProfit/totalCost);				
				
				AggregatedCategoryReportLineItem aggregatedCategoryReportLineItem = new AggregatedCategoryReportLineItem(category, orderCount, quantitySold, totalCost, totalProfit, profitRatio);
				aggregatedCategoryReportLineItems.add(aggregatedCategoryReportLineItem);
			}

		}		
		
		AggregatedCategoryReport aggregatedCategoryReport = new AggregatedCategoryReport(id, name, type, createdDateTime, aggregatedCategoryReportLineItems);
		
		return aggregatedCategoryReport;
	}
	


}
