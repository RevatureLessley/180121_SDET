package com.HKBank.code;

public class User {
	
	private String firstName;
	private String LasrName;
	private String passWord;
	private float amount;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLasrName() {
		return LasrName;
	}
	public void setLasrName(String lasrName) {
		LasrName = lasrName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	public User(String firstName, String lasrName, String passWord, float amount) {
		super();
		this.firstName = firstName;
		LasrName = lasrName;
		this.passWord = passWord;
		this.amount = amount;
	}
	public User() {
		super();
	}
	
}
