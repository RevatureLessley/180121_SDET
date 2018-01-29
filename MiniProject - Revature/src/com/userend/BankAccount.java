package com.userend;

import java.io.Serializable;

/**
 * 
 * @author Amr Hosny
 * Class cotains all user info and setters and getters for the user infos
 *
 */
public class BankAccount implements Serializable {

	private static final long serialVersionUID = 1L;

	private String FirstName;
	private String LastName;
	// private transient int ID;
	private String UserName;
	private String Password;
	private int Balance = 0;
	private boolean AdminApproval = false;

	public BankAccount(String fname, String lname, String username, String pw, int balance, boolean adminApproval) {

		super();
		FirstName = fname;
		LastName = lname;
		// ID = id;
		UserName = username;
		Password = pw;
		Balance = balance;
		AdminApproval = adminApproval;
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

	// public int getID() {
	// return ID;
	// }
	//
	// public void setID(int iD) {
	// ID = iD;
	// }

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

	public int getBalance() {
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

	public boolean isAdminApproval() {
		return AdminApproval;
	}

	public void setAdminApproval(boolean adminApproval) {
		AdminApproval = adminApproval;
	}

}
