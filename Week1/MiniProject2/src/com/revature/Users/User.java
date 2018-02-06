package com.revature.Users;

public class User {
	
	private String username;
	private String password;
	private boolean val;
	private int bits;
	
	//Default constructor
	public User() {super();}
	//Constructor used for populating the values for an instance of user.
	public User(String username, String password, boolean val, int bits) {
		super();
		this.username = username;
		this.password = password;
		this.val = val;
		this.bits = bits;
	}
	//classic pojo getters
	public String getUsername() {return username;}
	public String getPassword() {return password;}
	public boolean getVal() {return val;}
	public int getBits() {return bits;}
	
	//classic pojo setters
	public void setUsername(String username) {this.username = username;}
	public void setPassword(String password) {this.password = password;}
	public void setVal(boolean val) {this.val = val;}
	public void setBits(int bits) {this.bits = bits;}

	@Override
	public String toString() {return username + "," + password + "," + val + "," + bits;}

	//This function is used to make a deposit into the user's bits field
	public boolean makeDeposit(int deposit){
		bits = bits + deposit;
		return true;
	};
	//This function is used to make a withdrawl from the user's bits field. if the user does not have enough bits, false is returned. Otherwise it returns true.
	public boolean makeWithdrawl(int withdraw){
		if(withdraw > bits) {
			return false;
		}else {
			bits = bits - withdraw;
			return true;
		}
	}
	
	
}