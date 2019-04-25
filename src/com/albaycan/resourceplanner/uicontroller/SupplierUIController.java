package com.albaycan.resourceplanner.uicontroller;

import java.util.List;
import java.util.Scanner;

import com.albaycan.resourceplanner.domain.Country;
import com.albaycan.resourceplanner.domain.Supplier;
import com.albaycan.resourceplanner.service.SupplierManager;

public class SupplierUIController implements UIController {
	private Scanner input = new Scanner(System.in);
	private SupplierManager supplierManager;
	
	public SupplierUIController(SupplierManager supplierManager) {
		this.supplierManager = supplierManager;
	}

	@Override
	public String showMenu() {
		String endOfMenu;
		
		// Print Supplier Manager Menu
		do {

			System.out.println(supplierManagerMenuText());
			int menuChoice = input.nextInt();
			input.nextLine();

			// Call methods according to menu selection
			if (menuChoice == 1) {
				addSupplier();
			} else if (menuChoice == 2) {
				removeSupplier();
			} else if (menuChoice == 3) {
				getSuppliers();
			} else if (menuChoice == 4) {
				editSupplier();
			}

			System.out.println("Press ‘B’ to go back to Supplier Manager Menu or ‘*’ to go to Main Menu");
			endOfMenu = input.nextLine();

		} while (endOfMenu.equals("B"));
		return endOfMenu;

	}

	private String supplierManagerMenuText() {
		StringBuilder sb = new StringBuilder();
		sb.append("***********Supplier Manager************\n");
		sb.append("Please select an operation below:\n");
		sb.append("       (1)Add New Supplier\n");
		sb.append("       (2)Remove Supplier\n");
		sb.append("       (3)Show All Suppliers\n");
		sb.append("       (4)Edit Supplier\n");

		return sb.toString();
	}

	// Supplier Manager methods
	private void addSupplier() {

		System.out.println("***********Add New Supplier************\n");

		System.out.println("Supplier Name:");
		String name = input.nextLine();

		System.out.println("Supplier Address:");
		String address = input.nextLine();

		System.out.println("Post code:");
		String postCode = input.nextLine();

		System.out.println("Country:");
		System.out.println("Please select 1 of these countries: UnitedKingdom, France, Germany or USA");
		Country country = Country.valueOf(input.nextLine());

		System.out.println("Phone:");
		String phone = input.nextLine();

		System.out.println("Email Address:");
		String email = input.nextLine();

		Supplier supplier = new Supplier(name, address, postCode, country, phone, email);

		int supplierId = supplierManager.addSupplier(supplier);
		System.out.printf("'%s' is created with Supplier Id '%d'", name, supplierId).println();

	}

	private void removeSupplier() {
		System.out.println("***********Remove Supplier************\n");

		System.out.println("Supplier Id:\n");
		int id = input.nextInt();
		input.nextLine();

		Supplier supplier = supplierManager.getSupplierById(id);
		
		if(supplier==null) {
			System.out.println("Supplier not found");
		} else {
		supplierManager.removeSupplier(id);
		System.out.printf("'%s' is removed", supplier.getName()).println();
		}
		
	}

	private void getSuppliers() {
		System.out.println("***********List of All Suppliers************\n");

		String leftAlignFormat = "%-11d | %-9s |  %-20s  | %-9s   | %-14s  | %-12s   | %-15s      | %-22s   | %-20s  %n";

		System.out.format(
				"------------|-----------|------------------------|-------------|-----------------|----------------|----------------------|---------------------------------|------------------------------------%n");
		System.out.format(
				" Id         | Name      | Address                | Post Code   | Country         | Phone          | Email                | Created Date Time               | Updated Date Time                  %n");
		System.out.format(
				"------------|-----------|------------------------|-------------|-----------------|----------------|----------------------|---------------------------------|------------------------------------%n");

		List<Supplier> supplierList = supplierManager.getSuppliers();

		for (Supplier supplier : supplierList) {
			System.out.format(leftAlignFormat, supplier.getId(), supplier.getName(), supplier.getAddress(),
					supplier.getPostCode(), supplier.getCountry(), supplier.getPhone(), supplier.getEmail(),
					supplier.getCreateDateTime(), supplier.getUpdateDateTime());
		}

	}

	private void editSupplier() {
		System.out.println("***********Edit Supplier************\n");

		System.out.println("Enter the Id of the supplier you wish to edit:\n");
		int id = input.nextInt();
		input.nextLine();

		System.out.println(
				"Tip: The values in brackets are existing values. Press Enter to skip or enter a new value to change...\n");

		Supplier supplier = supplierManager.getSupplierById(id);

		System.out.printf("Supplier Name (“%s”):", supplier.getName()).println();
		String name = input.nextLine();
		if (name.equals("")) {
			name = supplier.getName();
		}

		System.out.printf("Supplier Address (“%s”):", supplier.getAddress()).println();
		String address = input.nextLine();
		if (address.equals("")) {
			address = supplier.getAddress();
		}

		System.out.printf("Post code(“%s”):", supplier.getPostCode()).println();
		String postCode = input.nextLine();
		if (postCode.equals("")) {
			postCode = supplier.getPostCode();
		}

		System.out.printf("Country (“%s”):", supplier.getCountry()).println();
		System.out.println("Please select 1 of these countries: UnitedKingdom, France, Germany or USA");
		String strCountry = input.nextLine();
		Country country = supplier.getCountry();
		if (!strCountry.equals("")) {
			country = Country.valueOf(strCountry);
		}

		System.out.printf("Phone (“%s”):", supplier.getPhone()).println();
		String phone = input.nextLine();
		if (phone.equals("")) {
			phone = supplier.getPhone();
		}

		System.out.printf("Email Address (“%s”):", supplier.getEmail()).println();
		String email = input.nextLine();
		if (email.equals("")) {
			email = supplier.getEmail();
		}

		supplier.setName(name);
		supplier.setAddress(address);
		supplier.setPostCode(postCode);
		supplier.setCountry(country);
		supplier.setPhone(phone);
		supplier.setEmail(email);

		supplierManager.editSupplier(supplier);

		System.out.printf("‘%s’ is updated.", supplier.getName()).println();

	}

}
