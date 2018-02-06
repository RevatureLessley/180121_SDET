package com.zensar.bankingsystem.beans;

public class Transaction {

	private int transactionID;
	private String transactionType;
	private int transactionAmount;

	public Transaction() {
	}

	public Transaction(int transactionID, String transactionType,
			int transactionAmount) {
		super();
		this.transactionID = transactionID;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public int getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(int transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	@Override
	public String toString() {
		return "Transaction [transactionID=" + transactionID
				+ ", transactionType=" + transactionType
				+ ", transactionAmount=" + transactionAmount + "]";
	}

}