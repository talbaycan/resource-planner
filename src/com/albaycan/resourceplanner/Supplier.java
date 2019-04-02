package com.albaycan.resourceplanner;

import java.time.LocalDateTime;
import java.util.Random;

public class Supplier implements java.io.Serializable {
	
	int id;
	String name;
	String address; 
	String postCode;
	String country;
	String phone;
	String email;
	LocalDateTime createDateTime;
	LocalDateTime updateDateTime;
	
	public Supplier(String name, String address, String postCode, String country, String phone, String email, LocalDateTime createDateTime, LocalDateTime updateDateTime) {
		this.id= new Random().nextInt((Integer.MAX_VALUE));
		this.name = name;
		this.address = address;
		this.postCode = postCode;
		this.country = country;
		this.phone = phone;
		this.email = email;
		this.createDateTime = createDateTime;
		this.updateDateTime = updateDateTime;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		postCode = postCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		email = email;
	}

	public int getId() {
		return id;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

}
