package com.trms.beans;

/**
 * Beans class for the table that handles the employees of a company
 * @author Lynda
 *
 */
public class Employee {
	private int id;
	private String fname;
	private String lname;
	private int reportsTo;
	private String department;
	private int departmentId;
	private String title;
	private int titleId;
	private float availReimburse;
	private String addr;
	private String city;
	private String state;
	private int zipCode;
	
	
	public Employee() {
	}

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
	
	
	
	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getTitleId() {
		return titleId;
	}

	public void setTitleId(int titleId) {
		this.titleId = titleId;
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
