package com.userend;

/**
 * 
 * Class contains all user info and setters and getters for the user infos
 * when a user gets created, a record in the Bank account table in the DB is
 * also created with all the user fields
 *
 */
public class BankAccount {

	private String FirstName;
	private String LastName;
	private String UserName;
	private String Password;
	private Integer Balance = 0;
	private Integer Account_ID = 0;
	private Integer AdminApproval = 0;

	public BankAccount(String fname, String lname, String username, String pw, Integer balance, Integer adminApproval) {

		super();
		FirstName = fname;
		LastName = lname;
		UserName = username;
		Password = pw;
		Balance = balance;
		AdminApproval = adminApproval;
	}
	
	public BankAccount(Integer account_id, String fname, String lname, String username, Integer balance) {

		super();
		Account_ID = account_id;
		FirstName = fname;
		LastName = lname;
		UserName = username;
		Balance = balance;
	}

	public BankAccount() {
		
	}


	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}


	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Integer getBalance() {
		return Balance;
	}

	public void setBalance(int balance) {
		Balance = balance;
	}

	public void Withdraw(int withdrawAmt) {
		Balance = Balance - withdrawAmt;

	}

	public void Deposit(int depositAmt) {
		Balance = Balance + depositAmt;

	}


	public Integer getAdminApproval() {
		return AdminApproval;
	}


	public void setAdminApproval(Integer adminApproval) {
		AdminApproval = adminApproval;
	}

	public Integer getAccount_ID() {
		return Account_ID;
	}

	public void setAccount_ID(Integer account_ID) {
		Account_ID = account_ID;
	}

	public void setBalance(Integer balance) {
		Balance = balance;
	}

	
}
