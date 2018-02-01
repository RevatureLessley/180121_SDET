package com.revature.beans;
/*
 * First step in JDBC connection, after the actual connection, 
 * should be to develop a container for the data you are going to be retrieving.
 * Typically, each table gets its own bean.
 */
public class Employee {
	private int empId;
	private String empName;
	private int empSalary;
	private String title;
	//For retrieving full objects from the database
	public Employee(int empId, String empName, int empSalary, String title) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empSalary = empSalary;
		this.title = title;
	}
	
	//For creating empty objects to build then insert
	public Employee() {
		
	}
	
	//For creating objects that assume the ID will be generated
	public Employee(String empName, int empSalary, String title) {
		super();
		this.empName = empName;
		this.empSalary = empSalary;
		this.title = title;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(int empSalary) {
		this.empSalary = empSalary;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empSalary=" + empSalary + ", title=" + title
				+ "]";
	}
	
	
}
