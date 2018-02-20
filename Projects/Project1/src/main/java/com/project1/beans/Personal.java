package com.project1.beans;

public class Personal {
    String email;
    String firstName;
    String lastName;
    String address;
    String jobTitle;
    String joinDate;
    
	public Personal(String email, String firstName, String lastName, String address, String jobTitle, String joinDate) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.jobTitle = jobTitle;
		this.joinDate = joinDate;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
}
