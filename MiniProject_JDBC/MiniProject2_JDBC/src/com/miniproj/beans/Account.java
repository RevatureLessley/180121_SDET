package com.miniproj.beans;

public class Account {
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private double checkingsBalance;
	private double savingsBalance;
	
	//Valid values: 0/1
	private int isAdmin;
	private int isActive;	
	private int isClosed; 
	
	public Account() {
		super();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getCheckingsBalance() {
		return checkingsBalance;
	}

	public void setCheckingsBalance(double checkingsBalance) {
		this.checkingsBalance = checkingsBalance;
	}

	public double getSavingsBalance() {
		return savingsBalance;
	}

	public void setSavingsBalance(double savingsBalance) {
		this.savingsBalance = savingsBalance;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}

	public int getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(char isClosed) {
		this.isClosed = isClosed;
	}

	@Override
	public String toString() {
		return "Account [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", username="
				+ username + ", password=" + password + ", checkingsBalance=" + checkingsBalance + ", savingsBalance="
				+ savingsBalance + ", isAdmin=" + isAdmin + ", isActive=" + isActive + ", isClosed=" + isClosed + "]";
	}
}
