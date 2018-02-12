package com.trms.beans;

public class Employee {
	private int id;
	private String fname;
	private String lname;
	private int reportsTo;
	private String department;
	private String title;
	private float availReimburse;
	private String addr;
	private String city;
	private String state;
	private int zipCode;
	
	
	public Employee(int id, String fname, String lname, int reportsTo, String department, String title,
			float availReimburse, String addr, String city, String state, int zipCode) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.reportsTo = reportsTo;
		this.department = department;
		this.title = title;
		this.availReimburse = availReimburse;
		this.addr = addr;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	
	public int getId() {
		return id;
	}
	public String getFname() {
		return fname;
	}
	public String getLname() {
		return lname;
	}
	public int getReportsTo() {
		return reportsTo;
	}
	public String getDepartment() {
		return department;
	}
	public String getTitle() {
		return title;
	}
	public float getAvailReimburse() {
		return availReimburse;
	}
	public String getAddr() {
		return addr;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public int getZipCode() {
		return zipCode;
	}
	
	
}
