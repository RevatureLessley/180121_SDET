package com.JawaunBank;
import java.io.*;
import java.util.*;

public abstract class Bank implements Serializable{
	
	private static final long serialVersionUID = -837920760249376613L;
	protected HashMap <Integer, Account> accountList; //list of accounts
	protected Scanner iny = new Scanner(System.in); // scanner for text input
	protected Admin admin = null; 

	protected HashMap<Integer, Account> getAccountList() {
		return accountList; //shows the account list in question
	}

	protected void setAccountList(HashMap<Integer, Account> accountList) {
		this.accountList = accountList; //set the account list to your choice
	}
	
	protected Admin getAdmin(String uname, String pword) { //traditional way to get admin
		Admin a = new Admin(uname, pword);
		this.admin=a;
		return this.admin;
	}
	
	protected final Admin registerAdmin() throws IOException {//register a new admin
		Admin regAdm = new Admin(0);
		System.out.println("please enter a new username");
		regAdm.UNAME = iny.next();	//next line is the user name
		System.out.println("please enter a new password");
		regAdm.PWORD = iny.next(); //next line is the password
		return regAdm;
	}
	
	protected void signInAdmin() {
		System.out.println("please enter your username"); 
		String u = iny.next(); //next is username
		System.out.println("please enter your password");
		String p = iny.next(); //next is password
		Admin a = getAdmin(u, p);
		a.menuop(); //goto menu
	}	
	
	protected Account getAccount(String uname, String pword) { //selects an account from account list
		Account requested = new Account();
		 for(HashMap.Entry<Integer, Account> entry : (this.accountList).entrySet()){ //go through every entry in the account list map
				Account retrieved = entry.getValue(); //temp account of current entry
				if((retrieved.getUname().equals(uname)) && ((retrieved.PWORD).equals(pword))) { //with matching uname/pword
					requested = retrieved; //you can assign the temp to the returned object
				} else {continue;}//keep checking if not
		 }	//end for loop
		 //returns an account that matches the user name and password values
		 return requested;
	}

	protected void signin() {//signin and take user to main menu
		System.out.println("please enter your username"); 
		String u = iny.next(); //next is username
		System.out.println("please enter your password");
		String p = iny.next(); //next is password
		Account a = getAccount(u,p); //finds the account with those credentials
		System.out.println("would you like to deposit, withdraw or view balance?");
		a.menu();
	}

	protected void register() {//reg new accounts and add them to hashmap
		Account regAct = new Account();
		System.out.println("please enter a new username");
		regAct.UNAME = iny.next();	//next line is the user name
		System.out.println("please enter a new password");
		regAct.PWORD = iny.next(); //next line is the password
		System.out.println("please enter a start balance");
		regAct.balance = iny.nextDouble(); 
		System.out.println("you are not yet approved");
		Account.numOfAccounts+=1;
		Integer i = Integer.valueOf(Account.numOfAccounts);
		accountList.put(i, regAct);
		signin();
	}
	
}

