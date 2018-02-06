package com.revaturebank.systemclass;


public class Transaction {
	private String trans_id;
	private String trans_date;
	private Double Amount;
	private String description;
	public Transaction(String trans_id, String trans_date, Double amount,String descr) {
		
		this.trans_id = trans_id;
		this.trans_date = trans_date;
	    this.description= descr;
		Amount = amount;
	}
	public String getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(String trans_id) {
		this.trans_id = trans_id;
	}
	public String getTrans_date() {
		return trans_date;
	}
	public void setTrans_date(String trans_date) {
		this.trans_date = trans_date;
	}
	
	public Double getAmount() {
		return Amount;
	}
	public void setAmount(Double amount) {
		Amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	
	

}
