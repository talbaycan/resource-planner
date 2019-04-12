package com.albaycan.resourceplanner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;


public class AppRunner {

	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {		
		
		String endOfMenu = null; 
		
		do {
		// Print First Menu
		System.out.println(firstMenuText());
		int firstMenuChoice = input.nextInt();
			input.nextLine();
		
		if(firstMenuChoice==1) {
			
			// Print Supplier Manager Menu
			do {
			
				System.out.println(supplierManagerMenuText());
				int supplierManagerMenuChoice = input.nextInt();
					input.nextLine();
				
				// Call methods according to menu selection
				if (supplierManagerMenuChoice==1) {
						addSupplier();
				} else if (supplierManagerMenuChoice==2) {
						removeSupplier();
				} else if (supplierManagerMenuChoice==3) {
						getSuppliers();
				} else if (supplierManagerMenuChoice==4) {
						editSupplier();
				}
				
				System.out.println("Press ‘B’ to go back to Supplier Manager Menu or ‘*’ to go to Main Menu");
					endOfMenu = input.nextLine();
				
			} while (endOfMenu.equals("B"));
		
		} else if(firstMenuChoice==2) {
			
			// Print Inventory Manager Menu
			do {
			
				System.out.println(inventoryManagerMenuText());
				int inventoryManagerMenuChoice = input.nextInt();
				input.nextLine();
			
				// Call methods according to menu selection
				if (inventoryManagerMenuChoice==1) {
					addProduct();
				} else if (inventoryManagerMenuChoice==2) {
					removeProduct();
				} else if (inventoryManagerMenuChoice==3) {
					getProducts();
				} else if (inventoryManagerMenuChoice==4) {
					editProduct();
				} else if (inventoryManagerMenuChoice==5) {
					getProductById();
				} else if (inventoryManagerMenuChoice==6) {
					getProductByName();
				} else if (inventoryManagerMenuChoice==7) {
					getOutOfStockProducts();
				} else if (inventoryManagerMenuChoice==8) {
					getProductWhichWillSoldOutInAWeek();
				}
			
				System.out.println("Press ‘B’ to go back to Supplier Manager Menu or ‘*’ to go to Main Menu");
				endOfMenu = input.nextLine();
		
				
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
	
	private static String inventoryManagerMenuText() {
		StringBuilder sb = new StringBuilder();
		sb.append("***********Inventory Manager************\n");
		sb.append("Please select an operation below:\n");
		sb.append("       (1)Add Product\n");
		sb.append("       (2)Remove Product\n");
		sb.append("       (3)Show All Products\n");
		sb.append("       (4)Edit Product\n");
		sb.append("       (5)Find Product by Product Id\n");
		sb.append("       (6)Find Product by Product Name\n");
		sb.append("       (7)Find Out of Stock Products\n");
		sb.append("       (8)Find Product Which Will Sold Out In a Week’s Time\n");
		
		return sb.toString();
	}
	
	private static SupplierManager supplierManager = new SupplierManagerImp();
	
	private static InventoryManager inventoryManager = new InventoryManagerImp();
	
	private static Country country;	
	
	private static Category category;
	
	
	//Supplier Manager methods
	private static void addSupplier() {
				
		System.out.println("***********Add New Supplier************\n");
		
		System.out.println("Supplier Name:");
		String name = input.nextLine();
		
		System.out.println("Supplier Address:");
		String address = input.nextLine();
		
		System.out.println("Post code:");
		String postCode = input.nextLine();
		
		System.out.println("Country:");
		Country country = getCountry();
		
		System.out.println("Phone:");
		String phone = input.nextLine();
		
		System.out.println("Email Address:");
		String email = input.nextLine();
		
		LocalDateTime createDateTime = LocalDateTime.now();
		LocalDateTime updateDateTime = LocalDateTime.now();		
		
		Supplier supplier = new Supplier(name, address, postCode, country, phone, email, createDateTime, updateDateTime);		
		
		int supplierId = supplierManager.addSupplier(supplier);
		System.out.printf("'%s' is created with Supplier Id '%d'", name, supplierId).println();
		
	}
	
	private static void removeSupplier() {
		System.out.println("***********Remove Supplier************\n");
		
		System.out.println("Supplier Id:\n");
		int id = input.nextInt();
		input.nextLine();
		
		Supplier supplier = supplierManager.getSupplierById(id);

		supplierManager.removeSupplier(id);
		System.out.printf("'%s' is removed", supplier.getName()).println();
		
	}
	
	private static void getSuppliers() {
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
	
	private static void editSupplier() {
		System.out.println("***********Edit Supplier************\n");
		
		
		System.out.println("Enter the Id of the supplier you wish to edit:\n");
		int id = input.nextInt();
		input.nextLine();
		
		System.out.println("Tip: The values in brackets are existing values. Press Enter to skip or enter a new value to change...\n");
		
		Supplier supplier = supplierManager.getSupplierById(id);

		System.out.printf("Supplier Name (“%s”):", supplier.getName()).println();
		String name = input.nextLine();
		if (name.equals("")) {
			name=supplier.getName();
		}
		
		System.out.printf("Supplier Address (“%s”):", supplier.getAddress()).println();
		String address = input.nextLine();
		if (address.equals("")) {
			address=supplier.getAddress();
		}
		
		System.out.printf("Post code(“%s”):", supplier.getPostCode()).println();
		String postCode = input.nextLine();
		if (postCode.equals("")) {
			postCode = supplier.getPostCode();
		}
		
		System.out.printf("Country (“%s”):", supplier.getCountry()).println();
		Country country = Country.valueOf(input.nextLine());
		if (country.equals("")) {
			country = supplier.getCountry();
		}
		
		System.out.printf("Phone (“%s”):", supplier.getPhone()).println();
		String phone = input.nextLine();
		if (phone.equals("")) {
			phone = supplier.getPhone();
		}
		
		System.out.printf("Email Address (“%s”):", supplier.getEmail()).println();
		String email = input.nextLine();
		if (email.equals("")) {
			email=supplier.getEmail();
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
		
	
	//Inventory Manager methods
	private static void addProduct() {		
			
		System.out.println("***********Add Product************\n");
			
		System.out.println("Product Name:");
		String name = input.nextLine();
			
		System.out.println("SKU:");
		String SKU = input.nextLine();
			
		System.out.println("Category:");
		Category category = getCategory();
			
		System.out.println("Supplier Id:");
		int supplierId = input.nextInt();
		input.nextLine();
			
		System.out.println("Buy Price (GBP)");
		double buyPrice = input.nextDouble();
			
		System.out.println("Sell Price (GBP):");
		double sellPrice = input.nextDouble();
		
		System.out.println("Weight (Grams)");
		int weight = input.nextInt();
		input.nextLine();
		
		System.out.println("Stock:");
		int stock = input.nextInt();
		input.nextLine();
		
		LocalDateTime createDateTime = LocalDateTime.now();
		LocalDateTime updateDateTime = LocalDateTime.now();		
					
		Product product = new Product(SKU, supplierId, category, name, buyPrice, sellPrice, weight, stock, createDateTime, updateDateTime);		
			
		int productId = inventoryManager.addProduct(product);
		System.out.printf("'%s' is created with Product Id '%d'", name, productId).println();	
		
	}
	
	
	private static void removeProduct() {
		System.out.println("***********Remove Product************\n");
		
		System.out.println("Product Id:\n");
		int id = input.nextInt();
		input.nextLine();
		
		Product product = inventoryManager.getProductById(id);

		inventoryManager.removeProduct(id);
		System.out.printf("'%s' is removed", product.getName()).println();
		
	}
	
	
	private static void getProducts() {
		System.out.println("***********List of All Products************\n");

		String leftAlignFormat = "%-11d | %-9s |  %-20s  | %-9s   | %-14s  | %-12s   | %-15s      | %-9s   | %-9s   | %-22s   | %-20s  %n";

		System.out.format(
				"------------|-----------|------------------------|-------------|-----------------|----------------|----------------------|-------------|-------------|---------------------------------|------------------------------------%n");
		System.out.format(
				" Id         | Name      | SKU                    | Category    | Supplier Id     | Buy Price (£)  | Sell Price (£)       | Weight (g)  | Stock       |Created Date Time               | Updated Date Time                  %n");
		System.out.format(
				"------------|-----------|------------------------|-------------|-----------------|----------------|----------------------|-------------|-------------|---------------------------------|------------------------------------%n");

		List<Product> productList = inventoryManager.getProducts();

		for (Product product : productList) {
			System.out.format(leftAlignFormat, product.getId(), product.getName(), product.getSKU(), product.getCategory(), product.getSupplierId(), product.getBuyPrice(), product.getSellPrice(), product.getWeight(), product.getStock(), product.getCreateDateTime(), product.getUpdateDateTime());
		}
		
	}
	
	
	private static void editProduct() {
		System.out.println("***********Edit Product************\n");
		
		
		System.out.println("Enter the Id of the product you wish to edit:\n");
		int id = input.nextInt();
		input.nextLine();
		
		System.out.println("Tip: The values in brackets are existing values. Press Enter to skip or enter a new value to change...\n");
		
		Product product = inventoryManager.getProductById(id);

		System.out.printf("Product Name (“%s”):", product.getName()).println();
		String name = input.nextLine();
		if (name.equals("")) {
			name=product.getName();
		}
		
		System.out.printf("Product SKU (“%s”):", product.getSKU()).println();
		String SKU = input.nextLine();
		if (SKU.equals("")) {
			SKU=product.getSKU();
		}
		
		System.out.printf("Category(“%s”):", product.getCategory()).println();
		Category category = Category.valueOf(input.nextLine());
		if (category.equals("")) {
			category = product.getCategory();
		}
		
		System.out.printf("Supplier Id (“%s”):", product.getSupplierId()).println();
		String supId = input.nextLine();
		int supplierId;
		if (!supId.equals("")) {
			supplierId=(int) supId;
		} else supplierId=product.getSupplierId();
		
		System.out.printf("Buy Price £ (“%s”):", product.getBuyPrice()).println();
		String bPrice = input.nextLine();
		double buyPrice=Double.valueOf(bPrice);
		if (bPrice.equals("")) {
			buyPrice = product.getBuyPrice();
		}
		
		System.out.printf("Sell Price £ (“%s”):", product.getSellPrice()).println();
		String sPrice = input.nextLine();
		double sellPrice=Double.valueOf(sPrice);
		if (sPrice.equals("")) {
			sellPrice = product.getSellPrice();
		}
		
		System.out.printf("Weight g (“%s”):", product.getWeight()).println();
		String wei = input.nextLine();
		int weight=Integer.valueOf(wei);
		if (wei.equals("")) {
			weight=product.getWeight();
		}
		
		System.out.printf("Stock (“%s”):", product.getStock()).println();
		String sto = input.nextLine();
		int stock=Integer.valueOf(sto);
		if (sto.equals("")) {
			stock=product.getStock();
		}
		
		product.setName(name);
		product.setSKU(SKU);
		product.setCategory(category);
		product.setSupplierId(supplierId);
		product.setBuyPrice(buyPrice);
		product.setSellPrice(sellPrice);
		product.setWeight(weight);
		product.setStock(stock);

		inventoryManager.editProduct(product);		

		System.out.printf("‘%s’ is updated.", product.getName()).println();
		
	}
	
	
	private static void getProductById() {
		System.out.println("***********Find Product by Product Id************\n");
		
		System.out.println("Product Id:\n");
		int id = input.nextInt();
		input.nextLine();
		
		Product product = inventoryManager.getProductById(id);
		
		String leftAlignFormat = "%-11d | %-9s |  %-20s  | %-9s   | %-14s  | %-12s   | %-15s      | %-9s   | %-9s   | %-22s   | %-20s  %n";

		System.out.format(
				"------------|-----------|------------------------|-------------|-----------------|----------------|----------------------|-------------|-------------|---------------------------------|------------------------------------%n");
		System.out.format(
				" Id         | Name      | SKU                    | Category    | Supplier Id     | Buy Price (£)  | Sell Price (£)       | Weight (g)  | Stock       |Created Date Time               | Updated Date Time                  %n");
		System.out.format(
				"------------|-----------|------------------------|-------------|-----------------|----------------|----------------------|-------------|-------------|---------------------------------|------------------------------------%n");

		System.out.format(leftAlignFormat, product.getId(), product.getName(), product.getSKU(), product.getCategory(), product.getSupplierId(), product.getBuyPrice(), product.getSellPrice(), product.getWeight(), product.getStock(), product.getCreateDateTime(), product.getUpdateDateTime());
				
	}
	
	
	private static void getProductByName() {
		System.out.println("***********Find Product by Product Name************\n");
		
		System.out.println("Product Name Contains:\n");
		String name = input.nextLine();

		List<Product> productList = inventoryManager.getProductByName(name);		
		
		String leftAlignFormat = "%-11d | %-9s |  %-20s  | %-9s   | %-14s  | %-12s   | %-15s      | %-9s   | %-9s   | %-22s   | %-20s  %n";

		System.out.format(
				"------------|-----------|------------------------|-------------|-----------------|----------------|----------------------|-------------|-------------|---------------------------------|------------------------------------%n");
		System.out.format(
				" Id         | Name      | SKU                    | Category    | Supplier Id     | Buy Price (£)  | Sell Price (£)       | Weight (g)  | Stock       |Created Date Time               | Updated Date Time                  %n");
		System.out.format(
				"------------|-----------|------------------------|-------------|-----------------|----------------|----------------------|-------------|-------------|---------------------------------|------------------------------------%n");

		for (Product product : productList) {
			System.out.format(leftAlignFormat, product.getId(), product.getName(), product.getSKU(), product.getCategory(), product.getSupplierId(), product.getBuyPrice(), product.getSellPrice(), product.getWeight(), product.getStock(), product.getCreateDateTime(), product.getUpdateDateTime());
		}
				
	}
	
	
	private static void getOutOfStockProducts() {
		System.out.println("***********Find Out of Stock Products************\n");
		
		List<Product> productList = inventoryManager.getOutOfStockProducts();
		
		String leftAlignFormat = "%-11d | %-9s |  %-20s  | %-9s   | %-14s  | %-12s   | %-15s      | %-9s   | %-9s   | %-22s   | %-20s  %n";

		System.out.format(
				"------------|-----------|------------------------|-------------|-----------------|----------------|----------------------|-------------|-------------|---------------------------------|------------------------------------%n");
		System.out.format(
				" Id         | Name      | SKU                    | Category    | Supplier Id     | Buy Price (£)  | Sell Price (£)       | Weight (g)  | Stock       |Created Date Time               | Updated Date Time                  %n");
		System.out.format(
				"------------|-----------|------------------------|-------------|-----------------|----------------|----------------------|-------------|-------------|---------------------------------|------------------------------------%n");

		for (Product product : productList) {
			System.out.format(leftAlignFormat, product.getId(), product.getName(), product.getSKU(), product.getCategory(), product.getSupplierId(), product.getBuyPrice(), product.getSellPrice(), product.getWeight(), product.getStock(), product.getCreateDateTime(), product.getUpdateDateTime());
		}
		
	}
	
	
	private static void getProductWhichWillSoldOutInAWeek() {
		System.out.println("Coming Soon");
		
	}	
	
	
	//ENUM methods
	private static Country getCountry() {
		
		System.out.println("Please select 1 of these countries: UnitedKingdom, France, Germany or USA");
		Country country = Country.valueOf(input.nextLine());	
		 
		return country;
					
	}
	
	private static Category getCategory() {
		
		System.out.println("Please select 1 of these categories: Gift, Toy, Computer");
		Category category = Category.valueOf(input.nextLine());	
		 
		return category;
					
	}
	
}
