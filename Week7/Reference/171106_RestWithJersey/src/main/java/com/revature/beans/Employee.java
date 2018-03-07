package com.revature.beans;

public class Employee {
	private String fname;
	private String lname;
	private int empId;
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getlname() {
		return lname;
	}
	public void setlname(String lname) {
		this.lname = lname;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public Employee(String fname, String lname, int empId) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.empId = empId;
	}
	@Override
	public String toString() {
		return "Employee [fname=" + fname + ", lname=" + lname + ", empId=" + empId + "]";
	}
<<<<<<< HEAD
	// Is the MOST important constructor as the JSON converter will create the
	//no-agrs verison of the okbject before populating it.
=======
	//This is the most important constructor as the JSON converter will create the
	//no-args version of the object before populating it.
>>>>>>> c379323b8095d7f29594268f8d7c3a0e8adce5d5
	public Employee() {
		super();
	}
	
	
	
}
