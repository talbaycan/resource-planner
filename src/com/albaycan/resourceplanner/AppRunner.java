package com.albaycan.resourceplanner;

import java.util.Scanner;

import com.albaycan.resourceplanner.uicontroller.UIController;
import com.albaycan.resourceplanner.uicontroller.UIControllerFactory;

public class AppRunner {

	private static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {

		String endOfMenu = null;
		do {
			System.out.println(mainMenuText());
			int mainMenuChoice = input.nextInt();
			input.nextLine();
			
			try {
			UIController uiController = UIControllerFactory.getUIController(mainMenuChoice);
			endOfMenu = uiController.showMenu();					
			}
			catch(Exception ex)
			{
				System.err.printf("ERROR: %s\nPlease try again!",ex.getStackTrace().toString()).println();
				endOfMenu="*";
			}
			
		} while (endOfMenu.equals("*"));

	}

	private static String mainMenuText() {
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
