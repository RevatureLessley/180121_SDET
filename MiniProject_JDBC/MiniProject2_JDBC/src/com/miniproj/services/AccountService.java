package com.miniproj.services;

import com.miniproj.beans.Account;
import com.miniproj.dao.AccountDao;
import com.miniproj.dao.AccountDaoImpl;

public class AccountService {
	AccountDao dao;
	private String firstName, lastName, email, username, password;
	private double checkingsBalance, savingsBalance;
	private int isAdmin, isActive, isClosed;
	private String isAdminStatus, isActiveStatus, isClosedStatus;
	
	public void gatherAccountInstances(Account acc) {
		firstName = acc.getFirstName();
		lastName = acc.getLastName();
		email = acc.getEmail();
		username = acc.getUsername();
		password = acc.getPassword();
		checkingsBalance = acc.getCheckingsBalance();
		savingsBalance = acc.getSavingsBalance();
		isAdmin = acc.getIsAdmin();
		isActive = acc.getIsActive();	
		isClosed = acc.getIsClosed();
		accountStatus();
	}
	public void accountStatus() {
		if(isAdmin == 0) isAdminStatus = "Regular account";
		else isAdminStatus = "Administrator";	
		
		if(isActive == 0) isActiveStatus = "Account has not been activated";
		else isActiveStatus = "Account has been activated";	
		
		if(isClosed == 0) isClosedStatus = "Not active";
		else if (isClosed == 1) isClosedStatus = "In use";
		else isClosedStatus = "Account has been terminated";
	}
	
	public void insertNewUser(Account acc) {
		gatherAccountInstances(acc);
		dao = new AccountDaoImpl();
		dao.insertIntoUserInfo(email, firstName, lastName);
		dao.insertIntoAccountInfo(email, username, password, isAdmin, isActive, isClosed);
		dao.insertIntoBalanceInfo(email, savingsBalance, checkingsBalance);
	}
	
	public void displayInfoForAdmin(Account acc) {
		gatherAccountInstances(acc);
		System.out.println("=========================================== \n"
				           + "Name: " + firstName + " " + lastName + "\n"
				           + "E-Mail: " + email + "\n"
				           + "Username: " + username + "\n"
				           + "Checkings balance: " + checkingsBalance + "\n"
				           + "Savings balance: " + savingsBalance + "\n"
				           + isAdminStatus + "\n"
				           + isActiveStatus + "\n"
				           + isClosedStatus + "\n"
				           + "===========================================");
	}
	
	public void displayInfoForUser(Account acc) {
		gatherAccountInstances(acc);
		System.out.println("=========================================== \n"
				           + "Name: " + firstName + " " + lastName + "\n"
				           + "E-Mail: " + email + "\n"
				           + "Username: " + username + "\n"
				           + "Checkings balance: " + checkingsBalance + "\n"
				           + "Savings balance: " + savingsBalance + "\n"
				           + "===========================================");
	}
	
}
