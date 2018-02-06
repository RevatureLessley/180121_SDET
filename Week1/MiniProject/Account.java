package com.miniprojectbankingsystem.beans;

import java.util.ArrayList;

public class Account {

	private int accountId;
	private String accountType;
	private int balance;
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

	public Account() {
		super();
	}

	public Account(int accountId, String accountType, int balance) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
		this.balance = balance;
	}

	public Account(String accountType, int balance) {
		super();
		this.accountType = accountType;
		this.balance = balance;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountType="
				+ accountType + ", balance=" + balance + ", transactions="
				+ transactions + "]";
	}

}