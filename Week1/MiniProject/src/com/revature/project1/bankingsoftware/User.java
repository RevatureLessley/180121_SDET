package com.revature.project1.bankingsoftware;

import java.io.Serializable;
/* * This is the class that handles data from users attempting to use the SleepyPupper Banking System.
 * It has 5 private variables; a string username for the users username, a string password for the users password
 * a boolean val to acertian if the user has had their account validated, an int bits to hold the users balance,
 * and finally a serialVerisionUID as provided by eclipse so that the object may be serialized.*/
public class User implements Serializable {
	private String username;
	private String password;
	private boolean val;
	private int bits;
	private static final long serialVersionUID = -331441734481213165L;
	//Default constructor
	public User() {super();}
	//Construtor used for populating the values for an instance of user.
	public User(String username, String password, boolean val, int bits) {
		super();
		this.username = username;
		this.password = password;
		this.val = val;
		this.bits = bits;
	}
	//classic pojo geters
	protected String getUsername() {return username;}
	protected String getPassword() {return password;}
	protected boolean getVal() {return val;}
	protected int getBits() {return bits;}
	//classic popjo seters
	protected void setUsername(String username) {this.username = username;}
	protected void setPassword(String password) {this.password = password;}
	protected void setVal(boolean val) {this.val = val;}
	protected void setBits(int bits) {this.bits = bits;}

	// toString is overriden to allow users variables to be return as a string for debugging.
	@Override
	public String toString() {return username + "," + password + "," + val + "," + bits;}
	
	//This function is used to make a deposit into the user's bits field
	protected boolean makeDeposit(int deposit){
		bits = bits + deposit;
		return true;
	};
	//This function is used to make a withdrawl from the user's bits field. if the user does not have enough bits, false is returned. Otherwise it returns true.
	protected boolean makeWithdrawl(int withdraw){
		if(withdraw > bits) {
			return false;
		}else {
			bits = bits - withdraw;
			return true;
		}
	}

}
