package com.albaycan.resourceplanner.service;

import java.util.List;

import com.albaycan.resourceplanner.domain.Supplier;

public interface SupplierManager {

	int addSupplier(Supplier supplier);
	void removeSupplier(int id);
	List<Supplier> getSuppliers();
	void editSupplier(Supplier supplier);
	Supplier getSupplierById(int id);
	boolean exists(int id);
	
}
