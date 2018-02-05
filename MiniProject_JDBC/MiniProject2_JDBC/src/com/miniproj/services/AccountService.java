package com.miniproj.services;

import java.util.List;
import com.miniproj.beans.Account;
import com.miniproj.dao.AccountDao;
import com.miniproj.dao.AccountDaoImpl;

public class AccountService {
	AccountDao dao = new AccountDaoImpl();
	Account account;
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
	
	public Account getAccount(String email) {
		account = dao.selectAccountByEmail(email);
		return account;
	}
	
	public List<Account> getAccounts() {
		return dao.getAllAccounts();
	}
	
	public void depositCheckings(Account acc, double amount) {
		gatherAccountInstances(acc);
		double newVal = acc.getCheckingsBalance() + amount;
		String column = "b_checkings";
		dao.updateBalanceInfo(email, column, newVal);
	}
	
	public void depositSavings(Account acc, double amount) {
		gatherAccountInstances(acc);
		double newVal = acc.getSavingsBalance() + amount;
		String column = "b_savings";
		dao.updateBalanceInfo(email, column, newVal);
	}
	
	public void withdrawCheckings(Account acc, double amount) {
		gatherAccountInstances(acc);
		double newVal = acc.getCheckingsBalance() - amount;
		String column = "b_checkings";
		dao.updateBalanceInfo(email, column, newVal);
	}
	
	public void withdrawSavings(Account acc, double amount) {
		gatherAccountInstances(acc);
		double newVal = acc.getCheckingsBalance() - amount;
		String column = "b_checkings";
		dao.updateBalanceInfo(email, column, newVal);
	}
	
	public void updateAccountStatus(String e, int val, char x) {
		if (x == 'a') dao.updateAccountStatusInfo(e, "a_is_active", val); 
		else dao.updateAccountStatusInfo(e, "a_is_closed", val); 
	}
}
