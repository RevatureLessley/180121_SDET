package com.revaturebank.systemclass;

import org.apache.log4j.Logger;

import com.revaturebank.dao.UserAccountDAOClass;

// this is a class for the clients that granted an account with the revatureBank.
public class UserAccount extends Person{
   final static Logger logger =Logger.getLogger(UserAccount.class);
   private UserAccountDAOClass uaDao =new UserAccountDAOClass();
   private String accountNumber;
   private String username;
   private String password;
   private Double balanceAccount;
   
   
   public UserAccount() {
	   
   }
    
   public UserAccount(String accountNumber, String username, String password, Double balanceAccount) {
	super();
	
	this.username = username;
	this.password = password;
	this.balanceAccount = balanceAccount;
	
}
public UserAccount(String ssNumber, String firsName, String lastName, String phone ,String email,
     String username, String password) {
	super(ssNumber, firsName, lastName, phone, email);

	this.username = username;
	this.password = password;
	
	}

public UserAccount(String ssNumber, String firsName, String lastName, String phone ,String email,
	     String username, String password,double amount) {
		super(ssNumber, firsName, lastName, phone, email);

		this.username = username;
		this.password = password;
		this.balanceAccount=amount;
		
		}




public String getAccountNumber() {
	return accountNumber;
}
public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Double getBalanceAccount() {
	return balanceAccount;
}
public void setBalanceAccount(Double balanceAccount) {
	this.balanceAccount = balanceAccount;
}
//add fund to the account
public void deposit(double amount) {
	   this.balanceAccount+=amount;
	   this.uaDao.balanceUpdate(this.accountNumber, amount,"direct depost",this.balanceAccount);
	   System.out.println("your deposit succefully done");
   }
//withdraw fund from the account if there is any.
   public void withdraw(double cash) {
	   if (this.balanceAccount>=cash) {
	   this.balanceAccount-=cash;
	   this.uaDao.balanceUpdate(this.accountNumber, -cash,"cash withdraw", this.balanceAccount);
	   System.out.println("$"+cash+" withdrawn ");
	   }
	   else {
		   System.out.println("Request denied: 'there is no enough fund in your account' ");
		   logger.warn("an attemps to withdraw under coverage");
	   }
   }
   
public void payCreditCard(Double pay) {
	if (this.balanceAccount>=pay) {
		   this.balanceAccount-=pay;
		   this.uaDao.balanceUpdate(this.accountNumber, -pay,"credit card payment", this.balanceAccount);
		   System.out.println("$"+pay+" payements has been made ");
		   }
		   else {
			   System.out.println("Request denied: 'there is no enough fund in your account' ");
			   logger.warn("an attemps to withdraw under coverage");
		   }
	
}
   
   public UserAccount authentication(String uName,String pWord){
       return this;
       
   }
   
}
