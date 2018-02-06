package com.banksystem.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.banksystem.beans.Account;
import com.banksystem.dao.AccountDaoImpl;
import com.banksystem.util.BankLogger;
import com.banksystem.main.BankSystem;

public class AccountServices {
	
	private static AccountDaoImpl dao = new AccountDaoImpl();
	private static List<Account> accounts = new ArrayList<>();
	private static Scanner sc;
	
	public static List<Account> getAllAccounts(){
		return dao.getAllAccounts();
	}
	
	public static int getMaxUserId(){
		return dao.getMaxUserId();
	}

	public static void addAccount(Account newAccount) {
		dao.addAccount(newAccount);
	}

	public static List<Account> getPendingApproveAccount() {
		return dao.getPendingApproveAccount();
	}
	
	public static void register() {
		String username;
		String password;

		accounts = getAllAccounts();

		// User Input
		sc = new Scanner(System.in);
		System.out.print("Username:");
		username = sc.nextLine();
		System.out.print("Password:");
		password = sc.nextLine();

		// Create account and add to database
		Account newAccount = new Account(getMaxUserId() + 1,
				username, password, 2, 2, 0.0);
		AccountServices.addAccount(newAccount);
		BankLogger.infoMsg("Account '" + newAccount.getUsername()
				+ "' registered.");
		// System.out.println("\nAccount registered.\n");
	}

	public static void approveAccount() {
		System.out.println("Accounts pending for approval:");
		accounts = AccountServices.getPendingApproveAccount();

		printAccounts(accounts);

		String input = "";
		int index;
		while (true) {
			System.out
					.println("Approve an account by selecting its UserId (-1 to go back):");
			sc = new Scanner(System.in);
			input = sc.nextLine();
			
			try{
				index = Integer.parseInt(input);
				
				if(index == -1)
					return;
				
				if(dao.getAccountById(index).getStatus() == 2){
					dao.approveAccount(index);
					
					BankLogger.infoMsg("Account '" + getAccountById(index).getUsername()
							+ "' is Approved.");
					return;
				}
				
				System.out.println("Invalid input.");
				
			}catch(Exception e){
				System.out.println("Invalid input.");
			}
		}

	}
	public static void printAccounts(List<Account> accs) {
		System.out.printf("\n%-10s%-20s%-20s%-10s%-10s%-10s\n", "UserId",
				"Username", "Password", "Role", "Status", "Balance");
		for (Account a : accs) {
			System.out.printf("%-10s%-20s%-20s%-10s%-10s%-10s\n",
					a.getUserid(), a.getUsername(), a.getPassword(),
					a.getRole(), a.getStatus(), a.getBalance());
		}
	}

	public static void viewAllAccount(){
		dao.getAllAccountsView();
	}
	
	public static void login() {
		String username;
		String password;
		sc = new Scanner(System.in);
		System.out.print("Username:");
		username = sc.nextLine();
		System.out.print("Password:");
		password = sc.nextLine();

		// Get All Account
		accounts = AccountServices.getAllAccounts();

		// Validate
		for (Account a : accounts) {
			if (a.getUsername().equals(username)
					&& a.getPassword().equals(password)) {
				if (a.getStatus() == 1) {
					BankSystem.mainMenu(a);
					return;
				} else if (a.getStatus() == 2) {
					System.out.println("\nYour account need to be approved.\n");
					return;
				} else if (a.getStatus() == 3) {
					System.out.println("\nYour account was frozen.");
					return;
				}
			}
		}
		System.out.println("\nInvalid credentials.\n");

	}
	public static Account getAccountById(int id){
		return dao.getAccountById(id);
	}
	
	public static void withdraw(Account a) {
		sc = new Scanner(System.in);
		double amount;
		System.out.println("Enter the amount:");
		try {
			amount = Double.parseDouble(sc.nextLine());
			if (amount < dao.getAccountById(a.getUserid()).getBalance()) {
				dao.updateBalance(a.getUserid(), dao.getAccountById(a.getUserid()).getBalance() - amount);
				System.out.println();
				BankLogger.infoMsg("$" + amount + " is withdrew from account '"
						+ a.getUsername()+ "'.");
				System.out.println();
			} else {
				System.out.println("You have insuffcient balance.");
			}

		} catch (NumberFormatException e) {
			System.out.println("Invalid input.");
		}

	}

	public static void deposit(Account a) {
		sc = new Scanner(System.in);
		double amount;
		System.out.println("Enter the amount:");
		try {
			amount = Double.parseDouble(sc.nextLine());
			dao.updateBalance(a.getUserid(), dao.getAccountById(a.getUserid()).getBalance() + amount);
			System.out.println();
			BankLogger.infoMsg("$" + amount + " is deposited into account '"
					+ a.getUsername()+ "'.");
			System.out.println();
		} catch (NumberFormatException e) {
			System.out.println("Invalid input.");
		}
	}

	public static void viewBalance(Account a) {
		System.out.println("Your balance is $"
				+ dao.getBalanceById(a.getUserid()));

		
	}
}
