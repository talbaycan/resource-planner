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
