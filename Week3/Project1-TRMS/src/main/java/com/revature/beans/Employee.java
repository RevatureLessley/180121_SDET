package com.revature.beans;

public class Employee {
	
	private int emp_id; 
	private String fname;
	private String lname;
	private String username;
	private String password;
	private String email;
	private String dob;
	private String address;
	private String city;
	private String state;
	private int zipcode;
	private String role;
	private String department;
	private String supervisor;
	
	public Employee(int emp_id, String fname, String lname, String username, String password, String email, String dob,
			String address,String city, String state, int zipcode, String role, String department, String supervisor) {
		super();
		this.emp_id = emp_id;
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.dob = dob;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.role = role;
		this.department = department;
		this.supervisor = supervisor;
	}
	
	public void setEmp_id(int emp_id) {this.emp_id = emp_id;}
	public void setFname(String fname) {this.fname = fname;}
	public void setLname(String lname) {this.lname = lname;}
	public void setUsername(String username) {this.username = username;}
	public void setPassword(String password) {this.password = password;}
	public void setEmail(String email) {this.email = email;}
	public void setDob(String dob) {this.dob = dob;}
	public void setAddress(String address) {this.address = address;}
	public void setCity(String city) {this.city = city;}
	public void setState(String state) {this.state = state;}
	public void setZipcode(int zipcode) {this.zipcode = zipcode;}
	public void setHead(String role) {this.role = role;}
	public void setDepartment(String department) {this.department = department;}
	public void setSupervisor(String supervisor) {this.supervisor = supervisor;}
	
	public int getEmp_id() {return emp_id;}
	public String getFname() {return fname;}
	public String getLname() {return lname;}
	public String getUsername() {return username;}
	public String getPassword() {return password;}
	public String getEmail() {return email;}
	public String getDob() {return dob;}
	public String getAddress() {return address;}
	public String getCity() {return city;}
	public String getState() {return state;}
	public int getZipcode() {return zipcode;}
	public String getRole() {return role;}
	public String getDepartment() {return department;}
	public String getSupervisor() {return supervisor;}
	
	@Override
	public String toString() {
		return "Employee [empId=" + emp_id + ",first name=" +  fname + ",last name=" + 
				lname + ",username=" + username + ",password="
				+ password + ",email=" + email + ",date of birth=" + 
				dob + ",address=" + address + ",zipcode=" + zipcode + ",role="+ 
				role + ",department=" + department + ",supervisor=" + supervisor+ "]";
	}
	
}
