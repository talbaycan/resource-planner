package com.albaycan.resourceplanner.uicontroller;

import java.util.List;
import java.util.Scanner;

import com.albaycan.resourceplanner.domain.Category;
import com.albaycan.resourceplanner.domain.Product;
import com.albaycan.resourceplanner.service.InventoryManager;
import com.albaycan.resourceplanner.service.SupplierManager;

public class InventoryUIController implements UIController{
	
	private Scanner input = new Scanner(System.in);
	SupplierManager supplierManager;
	InventoryManager inventoryManager;
	
	public InventoryUIController(SupplierManager supplierManager, InventoryManager inventoryManager) {
		this.supplierManager = supplierManager;
		this.inventoryManager = inventoryManager;
	}

	
	@Override
	public String showMenu()
	{
		System.out.println("Invetory Specific Menu");
		String endOfMenu;
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
		return endOfMenu;
		
				
	}
	
	private String inventoryManagerMenuText() {
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
	
	//Inventory Manager methods
		private void addProduct() {		
				
			System.out.println("***********Add Product************\n");
				
			System.out.println("Product Name:");
			String name = input.nextLine();
				
			System.out.println("SKU:");
			String SKU = input.nextLine();
				
			System.out.println("Category:");
			System.out.println("Please select 1 of these categories: Gift, Toy, Computer");
			Category category = Category.valueOf(input.nextLine());
				
			System.out.println("Supplier Id:");
			boolean supplierExist;
			int supplierId;
			do {
				supplierId = input.nextInt();
				
				supplierExist=supplierManager.exists(supplierId);
				
				if (supplierExist==false) {
					System.out.println("Invalid Supplier ID, please enter again");
				}
			} while (supplierExist=false); 
			
				
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
						
			Product product = new Product(SKU, supplierId, category, name, buyPrice, sellPrice, weight, stock);		
				
			int productId = inventoryManager.addProduct(product);
			System.out.printf("'%s' is created with Product Id '%d'", name, productId).println();	
			
		}
		
		
		private void removeProduct() {
			System.out.println("***********Remove Product************\n");
			
			System.out.println("Product Id:\n");
			int id = input.nextInt();
			input.nextLine();
			
			Product product = inventoryManager.getProductById(id);
			
			if(product==null) {
				System.out.println("Product not found");
			} else {
			inventoryManager.removeProduct(id);
			System.out.printf("'%s' is removed", product.getName()).println();
			}
			
		}
		
		
		private void getProducts() {
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
		
		
		private void editProduct() {
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
			System.out.println("Please select 1 of these categories: Gift, Toy, Computer");
			String strCategory = input.nextLine();
			Category category = product.getCategory();
			if (!strCategory.equals("")) {
				category = Category.valueOf(strCategory);
			}
			
			System.out.printf("Supplier Id (“%s”):", product.getSupplierId()).println();
			
			int supplierId;
			boolean supplierExist;
			
			do {
				//get supplier id as string
				String supId = input.nextLine();
				//set old value
				supplierId = product.getSupplierId();
				//check and set new value
				if (!supId.equals("")) {
					supplierId = Integer.parseInt(supId);
				}
				//check if supplier exists
				supplierExist = supplierManager.exists(supplierId);
				if (!supplierExist) {
					//try again
					System.out.println("Invalid Supplier ID, please try again");
				} 
			} while (!supplierExist);
			
			System.out.printf("Buy Price £ (“%s”):", product.getBuyPrice()).println();
			String bPrice = input.nextLine();
			double buyPrice;
			if (bPrice.equals("")) {
				buyPrice = product.getBuyPrice();
			} else {
				buyPrice=Double.valueOf(bPrice);
			}
			
			System.out.printf("Sell Price £ (“%s”):", product.getSellPrice()).println();
			String sPrice = input.nextLine();
			double sellPrice;
			if (sPrice.equals("")) {
				sellPrice = product.getSellPrice();
			} else {
				sellPrice=Double.valueOf(sPrice);
			}
			
			System.out.printf("Weight g (“%s”):", product.getWeight()).println();
			String wei = input.nextLine();
			int weight;
			if (wei.equals("")) {
				weight=product.getWeight();
			} else {
				weight=Integer.valueOf(wei);
			}
			
			System.out.printf("Stock (“%s”):", product.getStock()).println();
			String sto = input.nextLine();
			int stock;
			if (sto.equals("")) {
				stock=product.getStock();
			} else {
				stock=Integer.valueOf(sto);
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
		
		
		private void getProductById() {
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
		
		
		private void getProductByName() {
			System.out.println("***********Find Product by Product Name************\n");
			
			System.out.println("Product Name Contains:\n");
			String name = input.nextLine();	
			
			printProductListAsTable(inventoryManager.getProductByName(name));	
					
		}
		
		
		private void getOutOfStockProducts() {
			System.out.println("***********Find Out of Stock Products************\n");
			
			printProductListAsTable(inventoryManager.getOutOfStockProducts());			
			
		}
		
		
		private void getProductWhichWillSoldOutInAWeek() {
			System.out.println("Coming Soon");
			
		}	
		
		
		private void printProductListAsTable(List<Product> productList) {
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

}
