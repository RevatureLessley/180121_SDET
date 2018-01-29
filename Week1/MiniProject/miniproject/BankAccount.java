package com.revature.miniproject;

import java.util.ArrayList;

public class BankAccount {

	private String user;
	private String pw;
	private static ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
	private double amount;
	private boolean approved;
	
	/*
	 * user represents username
	 * pw represents password
	 * amount represents the amount of money associated with the account
	 * approved show whether the account has been approved by the admin
	 * 
	 */
	public BankAccount(String user, String pw)
	{
		this.user = user;
		this.pw = pw;
		this.approved = false;
		this.amount = 0;
	}
	/*
	 * Takes an argument of a double variable to subtract from the amount
	 */
	public double withdraw(double money)
	{
		if(money > this.amount)
			System.out.println("Insufficient Balance.");
		else
			this.amount -= money;
		return this.amount;
	}
	
	/*
	 * Takes an argument of a double variable to add to the amount
	 */
	public double deposit(double money)
	{
		if(money <= 0)
			System.out.println("Deposit amount should be more than 0.");
		else
			this.amount += money;
		return this.amount;
	}
	
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public double getAmount() {
		return amount;
	}
	public boolean isApproved() {
		return this.approved;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setApproval(boolean approval) {
		this.approved = approval;
	}
	public String toString()
	{
		return this.user  + " " + this.pw + " " + this.amount + " " + this.approved ;	
	}

}
