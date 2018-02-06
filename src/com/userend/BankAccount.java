package com.userend;


/**
 * 
 * Class contains all user info and setters and getters for the user infos
 *
 */
public class BankAccount {

	private static Integer ID = 0;

	private String FirstName;
	private String LastName;
	private String UserName;
	private String Password;
	private Integer Balance = 0;
	private Integer AdminApproval = 0;

	public BankAccount(String fname, String lname, String username, String pw, Integer balance, Integer adminApproval) {

		super();
		FirstName = fname;
		LastName = lname;
		UserName = username;
		Password = pw;
		Balance = balance;
		AdminApproval = adminApproval;
		ID++;
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

	 
	public static Integer getID() {
		return ID;
	}


	public static void setID(Integer iD) {
		ID = iD;
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

	
}
