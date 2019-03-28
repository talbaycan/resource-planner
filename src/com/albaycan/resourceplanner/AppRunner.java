package com.albaycan.resourceplanner;

import java.util.Scanner;

public class AppRunner {

	public static void main(String[] args) {		

		Scanner input = new Scanner(System.in);
		
		// Print First Menu
		System.out.println(firstMenuText());
		int firstMenuChoice = input.nextInt();
	}
	
	private static String firstMenuText() {
		StringBuilder sb = new StringBuilder();
		sb.append("=======================================================\n");
		sb.append("***********Welcome to Resource Planning App************\n");
		sb.append("=======================================================\n");
		sb.append("Please select one of the following options to start:\n");
		sb.append("       (1)Supplier Manager\n");
		sb.append("       (2)Inventory Manager\n");
		sb.append("       (3)Order Manager\n");
		sb.append("       (4)Reporting Manager\n");
		sb.append("Press Ctrl + C to exit\n");
		
		return sb.toString();
	}

}
