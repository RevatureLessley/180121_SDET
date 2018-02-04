package com.bank.beans;

public class Customer extends User {
	
	private double Amount;

	public Customer() {
		super();
	}

	public Customer(String userName, String password, String status) {
		super(userName, password, status);
	}
	
	public Customer(String userName, String password, String status, double amount) {
		super(userName, password, status);
		Amount = amount;
	}

	public double getAmount() {
		return Amount;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}
	
	

}
