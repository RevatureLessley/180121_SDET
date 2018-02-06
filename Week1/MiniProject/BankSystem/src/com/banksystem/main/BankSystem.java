package com.banksystem.main;
import java.io.IOException;
import java.util.Scanner;

import com.banksystem.beans.Account;
import com.banksystem.services.AccountServices;

public class BankSystem {
	private static Scanner sc;

	public static void main(String[] args) throws IOException {

		// for (Account a : accounts)
		// System.out.println(a);
		//AccountServices.printAccounts(AccountServices.getAllAccounts());

//		AccountServices.viewAllAccount();
//		System.exit(0);
		// Read user input
		sc = new Scanner(System.in);
		String option = "";

		// Welcome menu
		System.out.println("=======Welcome to Jiaqi's Bank System=======");
		while (!option.equals("0")) {
			System.out.println("Please select your option:");
			System.out.println("1 - Login");
			System.out.println("2 - Register");
			System.out.println("0 - Exit");

			option = sc.nextLine();

			switch (option) {
			case "0":
				System.out
						.println("Exited. Thank you for using Jiaqi's Bank System.");
				break;
			case "1":
				System.out.println("Logging in to your account...");
				AccountServices.login();
				break;
			case "2":
				System.out.println("Registering a new account...");
				AccountServices.register();
				break;
			default:
				System.out.println("Invalid option.");
				break;
			}
		}

		
		sc.close();
	}

	public static void mainMenu(Account a) {

		sc = new Scanner(System.in);
		String option = "";

		while (true) {
			System.out.println("\n==========================================");
			System.out.println("Welcome, " + a.getUsername()
					+ ". Please select an operation:");
			System.out.println("1 - Deposit Money");
			System.out.println("2 - Withdraw Money");
			System.out.println("3 - Check Balance");
			if (a.getRole() == 1){
				System.out.println("4 - Approve Account");
				System.out.println("5 - View All Accounts");
			}
			System.out.println("0 - Logout");

			option = sc.nextLine();

			switch (option) {
			case "0":
				a = null;
				System.out.println("\nYou have logged out.\n");
				return;
			case "1":
				System.out.println("Making a deposit...");
				AccountServices.deposit(a);
				break;
			case "2":
				System.out.println("Withdrawing from your account...");
				AccountServices.withdraw(a);
				break;
			case "3":
				AccountServices.viewBalance(a);
				break;
			case "4":
				if (a.getRole() == 1) {
					AccountServices.approveAccount();
					break;
				}
			case "5":
				if (a.getRole() == 1) {
					AccountServices.viewAllAccount();
					break;
				}
			default:
				System.out.println("Invalid option.");
				break;
			}
		}

	}

}
