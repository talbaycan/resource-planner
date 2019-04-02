package com.albaycan.resourceplanner;

import java.util.List;

public interface SupplierManager {

	int addSupplier(Supplier supplier);
	void removeSupplier(int id);
	List<Supplier> getSuppliers();
	void editSupplier(Supplier supplier);
	Supplier getSupplierById(int id);
	
}
