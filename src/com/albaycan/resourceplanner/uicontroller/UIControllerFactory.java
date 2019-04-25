package com.albaycan.resourceplanner.uicontroller;

import com.albaycan.resourceplanner.service.InventoryManager;
import com.albaycan.resourceplanner.service.InventoryManagerImp;
import com.albaycan.resourceplanner.service.OrderManager;
import com.albaycan.resourceplanner.service.OrderManagerImp;
import com.albaycan.resourceplanner.service.SupplierManager;
import com.albaycan.resourceplanner.service.SupplierManagerImp;

public class UIControllerFactory {
	
	private static SupplierManager supplierManager = new SupplierManagerImp();
	private static InventoryManager inventoryManager = new InventoryManagerImp();
	private static OrderManager orderManager = new OrderManagerImp(inventoryManager);
 	
	private static SupplierUIController supplierUIController = new SupplierUIController(supplierManager);
	private static InventoryUIController inventoryUIController = new InventoryUIController(supplierManager, inventoryManager);
	private static OrderUIController orderUIController = new OrderUIController(inventoryManager, orderManager);
	private static ReportUIController reportUIController = new ReportUIController();
	
	public static UIController getUIController(int choice) throws Exception {
					
		switch (choice) {
		case 1:
			return supplierUIController;
		case 2:
			return inventoryUIController;
		case 3:
			return orderUIController;
		case 4:
			return reportUIController;			
		}
		
	throw new Exception("Invalid input.");
	}

		
}
