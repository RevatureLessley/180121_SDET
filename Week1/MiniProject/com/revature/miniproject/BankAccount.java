package com.revature.miniproject;

public class BankAccount{

	private String user;
	private String pw;
	private double amount;
	private boolean approved;
	

	public BankAccount(String user, String pw)
	{
		this.user = user;
		this.pw = pw;
		this.approved = false;
		this.amount = 0;
	}
	public double withdraw(double money)
	{
		if(money > this.amount)
			System.out.println("Insufficient Balance.");
		else
			this.amount -= money;
		return this.amount;
	}

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
