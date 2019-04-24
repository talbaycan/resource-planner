package com.albaycan.resourceplanner.uicontroller;

import java.util.Scanner;

public class ReportUIController implements UIController {

	private Scanner input = new Scanner(System.in);
	
	@Override
	public String showMenu() {
		String endOfMenu;
		do {
			
		System.out.println(reportManagerMenuText());
		int menuChoice = input.nextInt();
		input.nextLine();
		
		if(menuChoice==1) {
			//getSalesReport();
		} else if (menuChoice==2) {
			//getSalesReportByDateFilter();
		} else if (menuChoice==3) {
			//getAggregatedReportByDayMonth();
		} else if (menuChoice==4) {
			//getAggregatedSalesReportByProducts();
		} else if (menuChoice==5) {
			//getAggregatedSalesReportByProductCategories();
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
		sb.append("       (2)Get Sales Report by date filter\n");
		sb.append("       (3)Get Aggregated Report by Day, Month\n");
		sb.append("       (4)Get Aggregated Sales Report By Products\n");
		sb.append("       (5)Get Aggregated Sales Report By Product Categories\n");
		
		return toString();
	}

}
