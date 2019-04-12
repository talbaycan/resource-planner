package com.albaycan.resourceplanner;

import java.time.LocalDateTime;
import java.util.Random;

enum Country {
	UnitedKingdom, France, Germany, USA,
}

public class Supplier implements java.io.Serializable {
	
	private int id;
	private String name;
	private String address;
	private String postCode;
	private Country country;
	private String phone;
	private String email;
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
	
	public Supplier(String name, String address, String postCode, Country country, String phone, String email, LocalDateTime createDateTime, LocalDateTime updateDateTime) {
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
		this.name = name;
		this.updateDateTime = LocalDateTime.now();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
		this.updateDateTime = LocalDateTime.now();
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
		this.updateDateTime = LocalDateTime.now();
	}

	public Country getCountry() {
		return country;
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
		this.updateDateTime = LocalDateTime.now();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		this.updateDateTime = LocalDateTime.now();
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
