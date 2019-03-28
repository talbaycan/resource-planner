package com.albaycan.resourceplanner;

import java.time.LocalDateTime;

public class Supplier implements java.io.Serializable {
	
	int Id;
	String Name;
	String Address; 
	String PostCode;
	String Country;
	String Phone;
	String Email;
	LocalDateTime CreateDateTime;
	LocalDateTime UpdateDateTime;

}
