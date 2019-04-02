package com.albaycan.resourceplanner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SupplierManagerImp implements SupplierManager {
	
	List<Supplier> supplierList = new ArrayList<Supplier>();
	Supplier existingSupplier;
			
	@Override
	public int addSupplier(Supplier supplier) {

		supplierList.add(supplier);
			System.out.printf("'%s' is created with Supplier Id '%d'", supplier.name, supplier.id).println();
				
		return supplier.id;
	}

	@Override
	public void removeSupplier(int id) {
		
		for (Supplier supplier:supplierList) {
			if(supplier.id == id) {
				supplierList.remove(supplier);
				System.out.printf("'%s' is removed", supplier.name);
			} 
		}
			
	}

	@Override
	public List<Supplier> getSuppliers() {
		
		String leftAlignFormat = "%-8d | %-9s |  %-20s  | %-9s   | %-14s  | %-12s   | %-15s      | %-18s    | %-14s  %n";

		System.out.format("---------|-----------|------------------------|-------------|-----------------|----------------|----------------------|-----------------------|-----------------------%n");
		System.out.format(" Id      | Name      | Address                | Post Code   | Country         | Phone          | Email                | Created Date Time     | Updated Date Time     %n");
		System.out.format("---------|-----------|------------------------|-------------|-----------------|----------------|----------------------|-----------------------|-----------------------%n");
		for (Supplier supplier:supplierList) {
			System.out.format(leftAlignFormat, supplier.id, supplier.name, supplier.address, supplier.postCode, supplier.country, supplier.phone, supplier.email, supplier.createDateTime, supplier.updateDateTime);
		}
		
		
		return null;
	}
	
	@Override
	public void editSupplier(Supplier editedSupplier) {
		
		for (Supplier supplier:supplierList) {
			if(supplier.id == editedSupplier.id) {
				supplier.setName(editedSupplier.name);
				supplier.setAddress(editedSupplier.address);
				supplier.setPostCode(editedSupplier.postCode);
				supplier.setCountry(editedSupplier.country);
				supplier.setPhone(editedSupplier.phone);
				supplier.setEmail(editedSupplier.email);
				supplier.updateDateTime = editedSupplier.updateDateTime;
				
			} 
		}
		
	}

	@Override
	public Supplier getSupplierById(int id) {
		
		for (Supplier supplier:supplierList) {
			if(supplier.id == id) {
				Supplier existingSuplier = supplier;
			} 
		}
		
		return existingSupplier;
	}
	

}
