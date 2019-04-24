package com.albaycan.resourceplanner.service;

import java.util.ArrayList;
import java.util.List;

import com.albaycan.resourceplanner.domain.Supplier;

public class SupplierManagerImp implements SupplierManager {
	
	List<Supplier> supplierList = new ArrayList<Supplier>();
			
	@Override
	public int addSupplier(Supplier supplier) {

		supplierList.add(supplier);
				
		return supplier.getId();
	}

	@Override
	public void removeSupplier(int id) {
		
		for (Supplier supplier:supplierList) {
			if (supplier.getId() == id) {
				supplierList.remove(supplier);
				break;
			} 
		}

		// supplierList.removeIf(x->x.getId()==id);
			
	}

	@Override
	public List<Supplier> getSuppliers() {
		
		return supplierList;
	}
	
	@Override
	public void editSupplier(Supplier supplier) {
		removeSupplier(supplier.getId());
		addSupplier(supplier);

	}

	@Override
	public Supplier getSupplierById(int id) {
		
		Supplier existingSupplier = null;

		for (Supplier supplier:supplierList) {
			if (supplier.getId() == id) {
				existingSupplier = supplier;
				break;
			} 
		}
		
		// return supplierList.stream().filter(x->x.getId()==id).findFirst().get();

		return existingSupplier;
	}

	@Override
	public boolean exists(int id) {
		if(getSupplierById(id)==null) {
			return false;
		} else {
			return true;
		}
		
		//return getSupplierById(id)!=null;
	}
	

}
