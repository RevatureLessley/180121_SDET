package com.miniproj.main;

import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import org.apache.log4j.Logger;

import com.miniproj.beans.Account;
import com.miniproj.services.AccountService;

public class Main {
	private String username;
	private String password;
	private String userSelection;
	private String email;
	private Account newUser;
	private Account currentUser;
	private double amount;
	Scanner input;
	AccountService as = new AccountService();
	List<Account> accounts = as.getAccounts();
	final static Logger logger = Logger.getLogger(Main.class);
	
	public static void main(String[] args) throws IOException {
		Main m = new Main();
		m.selectionMenu();
		m.input.close();
	}
	
	public void selectionMenu() throws IOException {
		userSelection = "";	
		input = new Scanner(System.in);
		while (true) {
			System.out.println("=========================================== \n"
					+ "Welcome to RR Bank! \n"
					+ "1) Create new account \n"
					+ "2) Sign in \n"
					+ "Type 'exit' to quit \n"
					+ "===========================================");
		
			userSelection = input.nextLine();
			if (userSelection.equals("1")) {
				newUser = new Account();
				System.out.println("Enter your first name: ");
				newUser.setFirstName(input.nextLine());
				System.out.println("Enter your last name: ");
				newUser.setLastName(input.nextLine());
				newUser.setEmail(addEmail());
				newUser.setUsername(createUsername());
				newUser.setPassword(createPassword());
				newUser.setIsAdmin(0);
				newUser.setIsActive(0);
				newUser.setIsClosed(1);
				logger.info("New account created.");
				System.out.println("Success!");
					
				//stores new user info in accounts and database
				accounts.add(newUser);
				as.insertNewUser(newUser);
			} else if (userSelection.equals("2")) {
				signIn();
			} else if (userSelection.equals("exit")){
				logger.info("user has quit the program.");
				System.out.println("Goodbye!");
				break;
			} else {
				System.out.println("Invalid entry.");
			}
		}
	}
	
//============ Creates new username ============//
	public String createUsername() {
			System.out.println("Create new username: \n");
			
			//username will hold user input, and input will read in from the user.
			input = new Scanner(System.in);
			username = input.nextLine();
			
			//If there are spaces, user will be asked to try again (no spaces allowed)
			while(username.contains(" ")) {
				System.out.println("No spaces allowed. Try again: \n");
				username = input.nextLine();
			}
			
			//Now we check to see if the name already exists in the database
			while (true) {
				boolean duplicate = false;
				String x = username.toUpperCase();
				for (Account a : accounts) {
					String y = a.getUsername().toUpperCase();
					if (x.equals(y)) {
						duplicate = true;
						System.out.println("This username already exists. Try again: ");
						break;
					}
				}
				if (duplicate) {
					username = input.nextLine();
					continue;
				}else {
					return username;
				}
			}
	}
	
//===========================Checks for email duplicate==============================//
	public String addEmail() {
		System.out.println("Enter your email: \n");
		
		//email will hold user input, and input will read in from the user.
		input = new Scanner(System.in);
		email = input.nextLine();
		
		//Now we check to see if the name already exists in the database
		while (true) {
			boolean duplicate = false;
			String x = email.toUpperCase();
			for (Account a : accounts) {
				 String y = a.getEmail().toUpperCase();
				if (x.equals(y)) {
					duplicate = true;
					System.out.println("This email already exists. Try again: ");
					break;
				}
			}
			if (duplicate) {
				email = input.nextLine();
				continue;
			}else {
				return email;
			}
		}
}
	
//============ Creates new password ============//
	public String createPassword() {
			System.out.println("Create new password: ");
			
			//password will hold user input, and input will read in from the user.
			input = new Scanner(System.in);
			
			//If there are spaces, user will be asked to try again (no spaces allowed)
			password = input.nextLine();
			while(password.contains(" ")) {
				System.out.println("No spaces allowed. Try again: ");
				password = input.nextLine();
			}
			return password;
	}		
	
	public void signIn() throws IOException {
		input = new Scanner(System.in);
		System.out.println("Enter your username: ");
		username = input.nextLine();
		while (true) {
			boolean validUser = false;
			for (Account a : accounts) {
				if (username.equals(a.getUsername())) {
					currentUser = a;
					validUser = true;
				}	
			}
			if (validUser) {
				break;
			} else {
				System.out.println("We do not recognize your username. Try again: ");
				username = input.nextLine();
			}
		}
		System.out.println("Hello " + currentUser.getUsername() + ", enter your password: ");
		password = input.nextLine();
		while (!password.equals(currentUser.getPassword())) {
			System.out.println("That is incorrect. Try again: ");
			password = input.nextLine();
		}
		if (currentUser.getIsAdmin() == 1 && currentUser.getIsActive() == 1) administrative(currentUser);
		else if (currentUser.getIsClosed() == 2) {
			System.out.println("Sorry. Your account has been terminated. Contact administrator for details.");
			return;
		} else if (currentUser.getIsActive() == 1 && currentUser.getIsClosed() == 1) userMenu(currentUser);
		else System.out.println("Your account has not been activated. You cannot sign in. \n\n");
	}

//============ User main menu ============//
	public void userMenu(Account a) throws IOException {
		logger.info("User has successsfully signed in. \n");
		input = new Scanner(System.in);
		boolean signout;
		while (true) {
			signout = false;
			System.out.println("=========================================== \n"
					+ "Welcome! What would you like to do? \n"
					+ "1) Checkings balance \n"
					+ "2) Savings balance \n"
					+ "3) Deposit in checkings \n"
					+ "4) Deposit in savings \n"
					+ "5) Withdraw from checkings \n"
					+ "6) Withdraw from savings \n"
					+ "7) General account info \n"
					+ "8) Edit user info \n"
					+ "9) close account \n"
					+ "Type 'sign out' to return to login. \n"
					+ "===========================================");
			userSelection = input.nextLine();
			switch (userSelection) {
				case "1": System.out.println("Your current balance is $" + a.getCheckingsBalance() + "\n"
						+ "Press any key to return to main menu");
						userSelection = input.nextLine();
						break;
				case "2": System.out.println("Your current balance is $" + a.getSavingsBalance() + "\n"
						+ "Press any key to return to main menu");
						userSelection = input.nextLine();
						break;
				case "3": deposit(a);
						break;
				case "4": deposit(a);
						break;
				case "5": withdraw(a);
						break;
				case "6": withdraw(a);
						break;
				case "7": as.displayInfoForUser(a);
						break;
				case "8": editAccountInfo(a);
						break;
				case "9": if (terminateAccountUser(a)) signout = true;
						  break;
				case "sign out": signout = true;
						break;
				default: System.out.println("Invalid entry.");
						break;
			}
			if (signout) break;
		}
	}
	
//============ deposit method ============//
	public void deposit(Account a) {
		input = new Scanner(System.in);
		System.out.println("How much would you like to deposit?");
		amount = Double.parseDouble(input.nextLine());
		while (true) {
			if (userSelection.equals("3") && amount > 0.0) {
				as.depositCheckings(a, amount);
				break;
			} else if (userSelection.equals("4") && amount > 0.0) {
				as.depositSavings(a, amount);
				break;
			} else {
				System.out.println("Invalid Entry.");
				amount = Double.parseDouble(input.nextLine());
			}
		}
		System.out.println("Transaction complete!");
	}
	
//============ withdraw method ============//
	public void withdraw(Account a) {
		input = new Scanner(System.in);
		System.out.println("How much would you like to withdraw?");
		amount = Double.parseDouble(input.nextLine());
		while (true) {
			if (userSelection.equals("5") && amount < a.getCheckingsBalance()) {
				as.withdrawCheckings(a, amount);
				System.out.println("Transaction complete!");
				break;
			} else if (userSelection.equals("6") && amount < a.getSavingsBalance()) {
				as.withdrawSavings(a, amount);
				System.out.println("Transaction complete!");
				break;
			} else if (amount > a.getCheckingsBalance() || amount > a.getSavingsBalance()){
				System.out.println("Sorry. You do not have enough funds.");
				break;
			} else {
				System.out.println("Invalid Entry. Enter amount you would like to withraw: ");
				amount = Double.parseDouble(input.nextLine());
			}
		}
	}

//============ Administrative controls ============//
	public void administrative(Account acc) throws IOException {
		logger.info("administrator signin");
		input = new Scanner(System.in);
		boolean signout;
		while (true) {
			signout = false;
			System.out.println("=========================================== \n"
					+ "Hello, Mr. Rosario. How can I help you today? \n"
					+ "1) View user accounts \n"
					+ "2) Activate/deactivate account \n"
					+ "3) Deposit checkings \n"
					+ "4) Deposit savings \n"
					+ "5) withdraw checkings \n"
					+ "6) withdraw savings \n"
					+ "7) Checkings balance \n"
					+ "8) Savings balance \n"
					+ "9) Terminate/reopen account \n"
					+ "Type 'sign out' to return to login. \n"
					+ "===========================================");
			userSelection = input.nextLine();
			switch (userSelection) {
				case "1": for (Account a : accounts) as.displayInfoForAdmin(a);
						  break;
				case "2": activateAccount();
						  break;
				case "3": deposit(acc);
						  break;
				case "4": deposit(acc);
						  break;
				case "5": withdraw(acc);
						  break;
				case "6": withdraw(acc);
						  break;
				case "7": System.out.println("Your current balance is $" + acc.getCheckingsBalance() + "\n"
						+ "Press any key to return to main menu");
						userSelection = input.nextLine();
						break;
				case "8": System.out.println("Your current balance is $" + acc.getSavingsBalance() + "\n"
						+ "Press any key to return to main menu");
						userSelection = input.nextLine();
						break;
				case "9": terminateAccountAdmin();
						break;
				case "sign out": signout = true;
						break;
				default: System.out.println("Invalid entry.");
			}
			if (signout) break;
		}
	}
	
//============ Account activator/deactivator ============//
	public void activateAccount() throws IOException {
		boolean accExists = false;
		input = new Scanner(System.in);
		System.out.println("Which account would you like to activate/deactivate?. Enter email: ");
		email = input.nextLine();
		for (Account a : accounts) {
			if (email.equals(a.getEmail()) && a.getIsActive() == 0) {
				as.updateAccountStatus(email, 1, 'a');
				logger.info("Account has been activated by admin.");
				System.out.println("Account has been activated. \n");
				accExists = true;
			} else if (email.equals(a.getEmail()) && a.getIsActive() == 1) {
				as.updateAccountStatus(email, 0, 'a');
				logger.info("Account has been deactivated by admin.");
				System.out.println("Account has been deactivated. \n");
				accExists = true;
			} else continue;
		  }
		if (!accExists) System.out.println("Account does not exist.");
	}
	
	public void terminateAccountAdmin() throws IOException {
		boolean accExists = false;
		input = new Scanner(System.in);
		System.out.println("Which account would you like to terminate/reopen?");
		email = input.nextLine();
		for (Account a : accounts) {
			if (email.equals(a.getEmail()) && a.getIsActive() == 1) {
				as.updateAccountStatus(email, 2, 'c');
				logger.info("Account has been terminated by admin.");
				System.out.println("Account has been terminated. \n");
				accExists = true;
			} else if (email.equals(a.getEmail()) && a.getIsActive() == 2) {
				as.updateAccountStatus(email, 1, 'c');
				logger.info("Account has been reopened by admin.");
				System.out.println("Account has been reopened. \n");
				accExists = true;
			} else continue;
		  }
		if (!accExists) System.out.println("Account does not exist.");
	}
	
	public boolean terminateAccountUser(Account acc) throws IOException {
		input = new Scanner(System.in);
		String choice;
		System.out.println("Are you sure you want to terminate your account? \n"
				+ "Once terminated, you will need to contact the administrator to reopen. \n"
				+ "Type 'yes' or 'no'");
		choice = input.nextLine();
		while (true) {
			if (choice.equals("yes")) {
				as.updateAccountStatus(acc.getEmail(), 2, 'c');
				logger.info("Account has been terminated by user.");
				System.out.println("Your account has been terminated. \n"
						+ "Funds will be mailed to you in the form of checks. \n"
						+ "Contact administrator if you wish to reopen. \n");
				as.clearCheckings(acc);
				as.clearSavings(acc);
				return true;
				} else if (choice.equals("no")) {
					System.out.println("Glad you are staying with us!");
					return false;
				} else {
					System.out.println("Invalid entry.");
					choice = input.nextLine();
					continue;
				}
			}
		}
	
	public void editAccountInfo(Account acc) {
		String first, last;
		input = new Scanner(System.in);
		System.out.println("=========================================== \n"
				+ "What would you like to edit? \n"
				+ "1) Name \n"
				+ "2) Change password \n"
				+ "3) Return to main menu \n"
				+ "=========================================== \n");
		userSelection = input.nextLine();
		while (true) {
			switch (userSelection) {
			case "1": System.out.println("Enter your first name: ");
					  first = input.nextLine();
					  System.out.println("Enter your last name: ");
					  last = input.nextLine();
					  as.updateName(first,  last, acc.getEmail());
					  break;
			case "2": System.out.println("What would you like to change your password to? ");
			  		  password = input.nextLine();
			case "3": return;
			default: System.out.println("Invalid entry.");
			}
		}
	}
}

