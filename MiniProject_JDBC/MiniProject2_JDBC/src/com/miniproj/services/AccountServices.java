package com.miniproj.services;

import com.miniproj.beans.Account;
import com.miniproj.dao.AccountDao;
import com.miniproj.dao.AccountDaoImpl;

public class AccountServices {
	AccountDao dao;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private double checkingsBalance;
	private double savingsBalance;
	private int isAdmin;
	private int isActive;	
	private int isClosed; 
	
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
	}
	public void insertNewUser(Account acc) {
		dao = new AccountDaoImpl();
		dao.insertIntoUserInfo(email, firstName, lastName);
		dao.insertIntoAccountInfo(email, username, password, isAdmin, isActive, isClosed);
		dao.insertIntoBalanceInfo(email, savingsBalance, checkingsBalance);
	}
}
