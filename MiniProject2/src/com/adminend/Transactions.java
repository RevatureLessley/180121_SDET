package com.adminend;


public class Transactions {

	private Integer Transaction_ID;
	private Integer Account_ID;
	private String UserName;
	private String Transaction_Type;
	private Integer Amount;
	
	public Transactions(Integer transaction_ID, Integer account_ID, String userName, String transaction_Type, Integer amount) {
		super();
		Transaction_ID = transaction_ID;
		Account_ID = account_ID;
		UserName = userName;
		Transaction_Type = transaction_Type;
		Amount = amount;
	}

	public Integer getTransaction_ID() {
		return Transaction_ID;
	}

	public void setTransaction_ID(Integer transaction_ID) {
		Transaction_ID = transaction_ID;
	}

	public Integer getAccount_ID() {
		return Account_ID;
	}

	public void setAccount_ID(Integer account_ID) {
		Account_ID = account_ID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getTransaction_Type() {
		return Transaction_Type;
	}

	public void setTransaction_Type(String transaction_Type) {
		Transaction_Type = transaction_Type;
	}

	public Integer getAmount() {
		return Amount;
	}

	public void setAmount(Integer amount) {
		Amount = amount;
	}




}
