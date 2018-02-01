package com.bank.beans;

public class User {
	
	private String userName;
	private String password;
	private double amount;
	private String status;
	
	public User(String userName, String password, double amount, String status) {
		super();
		this.userName = userName;
		this.password = password;
		this.amount = amount;
		this.status = status;
	}
	
	public User() {
		super();
	}
	
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", amount=" + amount + ", status=" + status
				+ "]";
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
