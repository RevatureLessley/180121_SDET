package com.miniprojectbankingsystem.beans;

import java.util.ArrayList;

public class Customer {

	private int customerId;
	private String customerName;
	private Address laddress;
	private Address haddress;
	private ArrayList<Account> account = new ArrayList<Account>(3);

	public Customer() {
		super();
	}

	public Customer(int customerId, String customerName, Address laddress,
			Address haddress) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.laddress = laddress;
		this.haddress = haddress;
	}

	public Customer(String customerName, Address laddress, Address haddress) {
		super();
		this.customerName = customerName;
		this.laddress = laddress;
		this.haddress = haddress;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Address getLaddress() {
		return laddress;
	}

	public void setLaddress(Address laddress) {
		this.laddress = laddress;
	}

	public Address getHaddress() {
		return haddress;
	}

	public void setHaddress(Address haddress) {
		this.haddress = haddress;
	}

	public ArrayList<Account> getAccount() {
		return account;
	}

	public void setAccount(ArrayList<Account> account) {
		this.account = account;
	}
	
	

}