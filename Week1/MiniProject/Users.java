package com.JawaunBank;

import java.util.*;

public abstract class Users {
	protected String UNAME = null;
	protected String PWORD = null;
	protected int IDNUM;
	protected static Scanner ino = new Scanner(System.in);
	protected final JBBank BANK = JBBank.getBank();
	protected Users() {
		//noargs constructor to create a user
	}
	
	protected Users(String uname, String pword, int idnum) {
		this.IDNUM=idnum; //allows you to create a brand new user with all constructs filled
		this.UNAME=uname;
		this.PWORD=pword;
	}

	protected Users(String uname, String pword) {
		this.UNAME=uname; //allows you to initialize a user based on user name and id
		this.PWORD=pword;
	}
	
	protected void setUname(String uname) {
		System.out.println("your user name is" + uname);
		this.UNAME = uname; //assigns a user name
	}
	protected void setPword(String pword) {
		System.out.println("your password is" + pword);
		this.PWORD = pword; //assigns a password
	}
	protected final int setIdnum(int idnum) {
		this.IDNUM = idnum;
		System.out.println("your id number is" + idnum);
		return this.IDNUM; //allows you to set user id number
	}

	protected String getUname() {
		return UNAME; //returns the username of the user
	}
	protected int getIdnum() {
		return IDNUM; //returns the ID number of the user
	}

	
	
}

