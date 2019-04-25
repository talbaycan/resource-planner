package com.albaycan.resourceplanner.uicontroller;

import java.util.List;
import java.util.Scanner;

import com.albaycan.resourceplanner.domain.Country;
import com.albaycan.resourceplanner.domain.Order;
import com.albaycan.resourceplanner.service.InventoryManager;
import com.albaycan.resourceplanner.service.OrderManager;

public class OrderUIController implements UIController {
	
	private Scanner input = new Scanner(System.in);
	private OrderManager orderManager;
	private InventoryManager inventoryManager;
	
	public OrderUIController(InventoryManager inventoryManager, OrderManager orderManager) {
		this.inventoryManager = inventoryManager;
		this.orderManager = orderManager;
	}
	
	@Override
	public String showMenu() {

		System.out.println("Order Specific Menu");
		String endOfMenu;
		// Print Order Manager Menu
		do {
		
			System.out.println(orderManagerMenuText());
			int orderManagerMenuChoice = input.nextInt();
			input.nextLine();
		
			// Call methods according to menu selection
			if (orderManagerMenuChoice==1) {
				addOrder();
			} else if (orderManagerMenuChoice==2) {
				removeOrder();
			} else if (orderManagerMenuChoice==3) {
				addRefund();
			} else if (orderManagerMenuChoice==4) {
				getOrders();
			} else if (orderManagerMenuChoice==5) {
				getOrderById();
			} else if (orderManagerMenuChoice==6) {
				getOrdersByProductId();
			} else if (orderManagerMenuChoice==7) {
				getOrdersByProductName();
			} else if (orderManagerMenuChoice==8) {
				editOrder();
			}
		
			System.out.println("Press ‘B’ to go back to Order Manager Menu or ‘*’ to go to Main Menu");
			endOfMenu = input.nextLine();
	
			
		} while (endOfMenu.equals("B"));
		return endOfMenu;
		
	}


	private String orderManagerMenuText() {
		StringBuilder sb = new StringBuilder();
		sb.append("***********Order Manager************\n");
		sb.append("Please select an operation below:\n");
		sb.append("       (1)Add Order\n");
		sb.append("       (2)Remove Order\n");
		sb.append("       (3)Add Refund\n");
		sb.append("       (4)List Orders\n");
		sb.append("       (5)Find Order by OrderId\n");
		sb.append("       (6)Find Orders by ProductId\n");
		sb.append("       (7)Find Orders by ProductName\n");
		sb.append("       (8)Edit Order\n");
		
		return sb.toString();
	}
	
	
	private void addOrder() {
		
		System.out.println("***********Add Order************\n");
		
		int productId;
		boolean productExist;
		do {
		System.out.println("Product Id:");
		productId = input.nextInt();
		input.nextLine();
		
		productExist=inventoryManager.exist(productId);
		
		if(!productExist)
			System.out.println("Invalid Product ID, please try again");
		} while (!productExist);
		
		System.out.println("Sale Price");
		double salePrice = input.nextDouble();
		
		System.out.println("Shipping Cost");
		double shippingCost = input.nextDouble();
		
		System.out.println("Packaging Cost");
		double packagingCost = input.nextDouble();
		
		int qty;
		int currentStock;
		do {
			
		System.out.println("Quantity:");
		qty = input.nextInt();
		input.nextLine();
		
		currentStock= inventoryManager.getProductById(productId).getStock();
		if(currentStock<qty)
			System.out.println("Unsifficient stock, please enter less quantity");
		
		} while (currentStock<qty);
		
		System.out.println("Customer Name:");
		String customerName = input.nextLine();
		
		System.out.println("Customer Address:");
		String customerAddress = input.nextLine();
		
		System.out.println("Customer Postcode:");
		String customerPostCode = input.nextLine();
		
		System.out.println("Customer Country:");
		System.out.println("Please select 1 of these countries: UnitedKingdom, France, Germany or USA");
		Country customerCountry = Country.valueOf(input.nextLine());
		
		System.out.println("Customer Phone:");
		String customerPhone = input.nextLine();
		
		System.out.println("Customer Email:");
		String customerEmail = input.nextLine();
						
		Order order = new Order(productId, salePrice, shippingCost, packagingCost, qty, 
				customerName, customerAddress, customerPostCode, customerCountry, 
				customerPhone, customerEmail, false);
		
		int orderId = orderManager.addOrder(order);
		System.out.printf("Order is created with Order Id '%d'", orderId).println();	
		
	}
	
	
	private void removeOrder() {
		System.out.println("***********Remove Order************\n");
		
		System.out.println("Order Id:\n");
		int id = input.nextInt();
		input.nextLine();
		
		Order order = orderManager.getOrderById(id);
		
		if(order==null) {
			System.out.println("Order not found");
		} else {
		orderManager.removeOrder(id);
		System.out.printf("Order Id '%d' is removed", order.getId()).println();
		}
	}
	
	
	private void addRefund() {
		System.out.println("***********Add Refund************\n");
		
		System.out.println("Please enter the Order Id for the order to be refunded:\n");
		int id = input.nextInt();
		input.nextLine();
		
		Order order = orderManager.getOrderById(id);
		
		if(order==null) {
			System.out.println("Order not found");
		} else {
		orderManager.addRefund(id);
		System.out.printf("'%d' order is refunded", order.getId()).println();
		}
		
	}
	
	
	private void getOrders() {
		System.out.println("***********List of All Products************\n");
		
		printOrderListAsTable(orderManager.getOrders());
			
	}
	
	
	private void getOrderById() {
		System.out.println("***********Find Order by Id************\n");
		
		System.out.println("Order Id:\n");
		int id = input.nextInt();
		input.nextLine();
		
		Order order = orderManager.getOrderById(id);
		
		if(order==null) {
			System.out.println("Order not found");
		} else {
		
		String leftAlignFormat = "%-11d | %-11d | %-11f | %-11f | %-11f | %-11d | %-20s | %-9s | %-14s | %-12s | %-15s | %-9s | %-9t | %-22t | %-20s  %n";

		System.out.format(
				"------------|------------|----------------|-------------------|--------------------|----------|----------------|----------------------|-------------------|------------------|----------------|------------------|----------------------|----------------------|----------%n");
		System.out.format(
				" Id         | Product Id | Sale Price (£) | Shipping Cost (£) | Packaging Cost (£) | Quantity | Customer Name  | Customer Address     | Customer PostCode | Customer Country | Customer Phone | Customer Email   | Created Date Time    | Updated Date Time    | Refunded %n");
		System.out.format(
				"------------|------------|----------------|-------------------|--------------------|----------|----------------|----------------------|-------------------|------------------|----------------|------------------|----------------------|----------------------|----------%n");
		
		System.out.format(leftAlignFormat, order.getId(), order.getProductId(), order.getSalePrice(), order.getShippingCost(), order.getPackagingCost(), order.getQty(), order.getCustomerName(), order.getCustomerAddress(), order.getCustomerPostCode(), order.getCustomerCountry(), order.getCustomerPhone(), order.getCustomerEmail(), order.getCreateDateTime(), order.getUpdateDateTime(), order.isRefunded());
		}
		
	}
	
	
	private void getOrdersByProductId() {
		System.out.println("***********Find Orders by Product Id************\n");
		
		System.out.println("Product Id:\n");
		int id = input.nextInt();
		input.nextLine();
		
		List<Order> orders = orderManager.getOrdersByProductId(id);
		
		if(orders==null) {
			System.out.println("No Order/s found");
		} else {		
		printOrderListAsTable(orders);
		}
		
	}
	
	
	private void getOrdersByProductName() {
		System.out.println("***********Find Orders by Product Name************\n");
		
		System.out.println("Product Id:\n");
		String productName = input.nextLine();
		
		printOrderListAsTable(orderManager.getOrdersByProductName(productName));		
		
	}
		
	
	private void editOrder() {
		System.out.println("***********Edit Order************\n");		
		
		System.out.println("Enter the Id of the order you wish to edit:\n");
		int id = input.nextInt();
		input.nextLine();
		
		System.out.println("Tip: The values in brackets are existing values. Press Enter to skip or enter a new value to change...\n");
		
		Order order = orderManager.getOrderById(id);

		System.out.printf("Product Id (“%d”):", order.getProductId()).println();
		
		int productId;
		boolean productExist;
		
		do {
			
		String productIdStr = input.nextLine();
		productId = order.getProductId();
		if(!productIdStr.equals("")) {
			productId = Integer.parseInt(productIdStr);
		}
		productExist = inventoryManager.exist(productId);
		if(productExist!=true) {
			System.out.println("Invalid Product ID, please try again");
		}
		
		} while (productExist!=true);
		
		
		System.out.printf("Sale Price (£) (“%f”):", order.getSalePrice()).println();
		String salePriceStr = input.nextLine();
		double salePrice = order.getSalePrice();
		if(!salePriceStr.equals("")) {
			salePrice = Integer.parseInt(salePriceStr);
		}
		
		
		System.out.printf("Shipping Cost (£) (“%f”):", order.getShippingCost()).println();
		String shippingCostStr = input.nextLine();
		double shippingCost = order.getShippingCost();
		if(!shippingCostStr.equals("")) {
			shippingCost = Integer.parseInt(shippingCostStr);
		}
		
		
		System.out.printf("Packaging Cost (£) (“%f”):", order.getPackagingCost()).println();
		String packagingCostStr = input.nextLine();
		double packagingCost = order.getPackagingCost();
		if(!packagingCostStr.equals("")) {
			packagingCost = Integer.parseInt(packagingCostStr);
		}
		
				
		System.out.printf("Quantity (“%d”):", order.getQty()).println();
		String qtyStr = input.nextLine();
		int qty = order.getQty();
		if(!qtyStr.equals("")) {
			qty = Integer.parseInt(qtyStr);
		}
		
		
		System.out.printf("Customer Name (“%s”):", order.getCustomerName()).println();
		String customerName = input.nextLine();
		if(customerName.equals("")) {
			customerName = order.getCustomerName();
		}
		
		
		System.out.printf("Customer Address (“%s”):", order.getCustomerAddress()).println();
		String customerAddress = input.nextLine();
		if(customerAddress.equals("")) {
			customerAddress = order.getCustomerAddress();
		}
		
		
		System.out.printf("Customer Postcode (“%s”):", order.getCustomerPostCode()).println();
		String customerPostCode = input.nextLine();
		if(customerPostCode.equals("")) {
			customerPostCode = order.getCustomerPostCode();
		}
		
		
		System.out.printf("Customer Country (“%d”):", order.getCustomerCountry()).println();
		String customerCountryStr = input.nextLine();
		Country customerCountry = order.getCustomerCountry();
		if(!customerCountryStr.equals("")) {
			customerCountry = Country.valueOf(customerCountryStr);
		}
		
		
		System.out.printf("Customer Phone (“%s”):", order.getCustomerPhone()).println();
		String customerPhone = input.nextLine();
		if(customerPhone.equals("")) {
			customerPhone = order.getCustomerPhone();
		}
		
		
		System.out.printf("Customer Email (“%s”):", order.getCustomerEmail()).println();
		String customerEmail = input.nextLine();
		if(customerEmail.equals("")) {
			customerEmail = order.getCustomerEmail();
		}
		
		
		order.setProductId(productId);
		order.setSalePrice(salePrice);
		order.setShippingCost(shippingCost);
		order.setPackagingCost(packagingCost);
		order.setQty(qty);
		order.setCustomerName(customerName);
		order.setCustomerAddress(customerAddress);
		order.setCustomerPostCode(customerPostCode);
		order.setCustomerCountry(customerCountry);
		order.setCustomerPhone(customerPhone);
		order.setCustomerEmail(customerEmail);
		
		
		orderManager.editOrder(order);

		System.out.printf("Order Id ‘%d’ is updated.", order.getId()).println();
		
	}
	
	private void printOrderListAsTable(List<Order> orderList) {
		
		String leftAlignFormat = "%-11d | %-11d | %-11f | %-11f | %-11f | %-11d | %-20s | %-9s | %-14s | %-12s | %-15s | %-9s | %-9t | %-22t | %-20s  %n";

		System.out.format(
				"------------|------------|----------------|-------------------|--------------------|----------|----------------|----------------------|-------------------|------------------|----------------|------------------|----------------------|----------------------|----------%n");
		System.out.format(
				" Id         | Product Id | Sale Price (£) | Shipping Cost (£) | Packaging Cost (£) | Quantity | Customer Name  | Customer Address     | Customer PostCode | Customer Country | Customer Phone | Customer Email   | Created Date Time    | Updated Date Time    | Refunded %n");
		System.out.format(
				"------------|------------|----------------|-------------------|--------------------|----------|----------------|----------------------|-------------------|------------------|----------------|------------------|----------------------|----------------------|----------%n");

		List<Order> ordersList = orderManager.getOrders();

		for (Order order : orderList) {
			System.out.format(leftAlignFormat, order.getId(), order.getProductId(), order.getSalePrice(), order.getShippingCost(), order.getPackagingCost(), order.getQty(), order.getCustomerName(), order.getCustomerAddress(), order.getCustomerPostCode(), order.getCustomerCountry(), order.getCustomerPhone(), order.getCustomerEmail(), order.getCreateDateTime(), order.getUpdateDateTime(), order.isRefunded());
		}
	}
	

	
}
