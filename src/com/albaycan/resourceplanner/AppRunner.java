package com.albaycan.resourceplanner;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

public class AppRunner {

	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {		

		String endOfMenu = null; 
		
		do {
		// Print First Menu
		System.out.println(firstMenuText());
		int firstMenuChoice = input.nextInt();
		
		if(firstMenuChoice==1) {
			
			// Print Supplier Manager Menu
			do {
			
				System.out.println(supplierManagerMenuText());
				int supplierManagerMenuChoice = input.nextInt();
				
				// Call methods according to menu selection
				if (supplierManagerMenuChoice==1) {
					addingNewSupplier();					
				} else if (supplierManagerMenuChoice==2) {
					removingSupplier();
				} else if (supplierManagerMenuChoice==3) {
					showingAllSuppliers();
				} else if (supplierManagerMenuChoice==4) {
					editingSupplier();
				}
				
				System.out.println("Press ‘B’ to go back to Supplier Manager Menu or ‘*’ to go to Main Menu");
				endOfMenu = input.next();
				
				} while (endOfMenu.equals("B"));
		}		
		
		
		}
		while (endOfMenu.equals("*"));
										
		
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
	
	private static String supplierManagerMenuText() {
		StringBuilder sb = new StringBuilder();
		sb.append("***********Supplier Manager************\n");
		sb.append("Please select an operation below:\n");
		sb.append("       (1)Add New Supplier\n");
		sb.append("       (2)Remove Supplier\n");
		sb.append("       (3)Show All Suppliers\n");
		sb.append("       (4)Edit Supplier\n");
		
		return sb.toString();
	}
	
	private static void addingNewSupplier() {
				
		System.out.println("***********Add New Supplier************\n");
		
		System.out.println("Supplier Name:");
		String name = input.next();
		
		System.out.println("Supplier Address:");
		String address = input.next();
		
		System.out.println("Post code:");
		String postCode = input.next();
		
		System.out.println("Country:");
		String country = input.next();
		
		System.out.println("Phone:");
		String phone = input.next();
		
		System.out.println("Email Address:");
		String email = input.next();
		
		LocalDateTime createDateTime = LocalDateTime.now();
		LocalDateTime updateDateTime = LocalDateTime.now();
		
		Supplier supplier = new Supplier(name, address, postCode, country, phone, email, createDateTime, updateDateTime);		
		
		SupplierManager supplierManager = new SupplierManagerImp();
		supplierManager.addSupplier(supplier);
		
	}
	
	private static void removingSupplier() {
		System.out.println("***********Remove Supplier************\n");
		
		System.out.println("Supplier Id:\n");
		int id = input.nextInt();		
		
		SupplierManager supplierManager = new SupplierManagerImp();
		supplierManager.removeSupplier(id);
		
	}
	
	private static void showingAllSuppliers() {
		System.out.println("***********List of All Suppliers************\n");
		
		SupplierManager supplierManager = new SupplierManagerImp();
		System.out.println(supplierManager.getSuppliers());
		
	}
	
	private static void editingSupplier() {
		System.out.println("***********Edit Supplier************\n");
		
		
		System.out.println("Enter the Id of the supplier you wish to edit:\n");
		int id = input.nextInt();
		
		SupplierManager supplierManager = new SupplierManagerImp();
		
		System.out.println("Tip: The values in brackets are existing values. Press Enter to skip or enter a new value to change...\n");
		
		System.out.printf("Supplier Name (“%s”):", supplierManager.getSupplierById(id).name).println();
		String name = input.next();
		
		System.out.printf("Supplier Address (“%s”):", supplierManager.getSupplierById(id).address).println();
		String address = input.next();
		
		System.out.printf("Post code(“%s”):", supplierManager.getSupplierById(id).postCode).println();
		String postCode = input.next();
		
		System.out.printf("Country (“%s”):", supplierManager.getSupplierById(id).country).println();
		String country = input.next();
		
		System.out.printf("Phone (“%s”):", supplierManager.getSupplierById(id).phone).println();
		String phone = input.next();
		
		System.out.printf("Email Address (“%s”):", supplierManager.getSupplierById(id).email).println();
		String email = input.next();
		
		LocalDateTime createDateTime = supplierManager.getSupplierById(id).createDateTime;
		LocalDateTime updateDateTime = LocalDateTime.now();
		
		Supplier editedSupplier = new Supplier(name, address, postCode, country, phone, email, createDateTime, updateDateTime);
		
		System.out.printf("‘%s’ is updated.", supplierManager.getSupplierById(id).name);		
		
	}
	
}
