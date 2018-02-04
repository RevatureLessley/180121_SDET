package com.miniproj.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import org.apache.log4j.Logger;

import com.miniproj.beans.Account;
import com.miniproj.services.AccountService;

public class Main {
	public static void main(String[] args) {
		Main m = new Main();
		m.blop();
	}
	
	public void blop() {
		AccountService as = new AccountService();
		Account a = new Account();
		a.setEmail("Goop@r.com");
		//as.insertNewUser(a);
		as.depositSavings(a, 2345.56);
		System.out.println(as.getAccount("Goop@r.com"));
		
	}
//	List<Account> accounts;
//	private String username;
//	private String password;
//	private String userSelection;
//	private Account newUser;
//	private Account currentUser;
//	private double amount;
//	private int accountIndex;
//	Scanner input;
//	AccountService as;
//	final static Logger logger = Logger.getLogger(Main.class);
//	
//	public static void main(String[] args) throws IOException {
//		Main m = new Main();
//		m.retrieveAccounts();
//		m.selectionMenu();
//		m.refreshDatabase();
//		m.input.close();
//	}
//	
//	public void selectionMenu() throws IOException {
//		userSelection = "";	
//		input = new Scanner(System.in);
//		while (true) {
//			System.out.println("Welcome to the Bank! \n"
//					+ "1) Create new account \n"
//					+ "2) Sign in \n"
//					+ "Type 'exit' to quit");
//		
//			userSelection = input.nextLine();
//			if (userSelection.equals("1")) {
//				logger.info("user has signed in");
//				newUser = new Account();
//				newUser.setUsername(createUsername());
//				newUser.setPassword(createPassword());
//				newUser.setIsAdmin(false);
//				newUser.setCanAccess(false);
//				logger.info("New account created.");
//				System.out.println("Success!");
//					
//				//stores new user info in arraylist and updates database
//				accounts.add(newUser);
//				refreshDatabase();
//			} else if (userSelection.equals("2")) {
//				signIn();
//			} else if (userSelection.equals("exit")){
//				logger.info("user has quit the program.");
//				System.out.println("Goodbye!");
//				break;
//			} else {
//				System.out.println("Invalid entry.");
//			}
//		}
//	}
//	
////============ Creates new username ============//
//	public String createUsername() {
//			System.out.println("Create new username: \n");
//			
//			//username will hold user input, and input will read in from the user.
//			input = new Scanner(System.in);
//			username = input.nextLine();
//			
//			//If there are spaces, user will be asked to try again (no spaces allowed)
//			while(username.contains(" ")) {
//				System.out.println("No spaces allowed. Try again: \n");
//				username = input.nextLine();
//			}
//			
//			//Now we check to see if the name already exists in the database
//			while (true) {
//				boolean duplicate = false;
//				for (Account a : accounts) {
//					if (username.equals(a.getUsername())) {
//						duplicate = true;
//						System.out.println("This username already exists. Try again: ");
//						break;
//					}
//				}
//				if (duplicate) {
//					username = input.nextLine();
//					continue;
//				}else {
//					return username;
//				}
//			}
//	}
//	
////============ Creates new password ============//
//	public String createPassword() {
//			System.out.println("Create new password: ");
//			
//			//password will hold user input, and input will read in from the user.
//			input = new Scanner(System.in);
//			
//			//If there are spaces, user will be asked to try again (no spaces allowed)
//			password = input.nextLine();
//			while(password.contains(" ")) {
//				System.out.println("No spaces allowed. Try again: ");
//				password = input.nextLine();
//			}
//			return password;
//	}
//	
////============ Retrieves accounts from database ============//
//	public void retrieveAccounts() throws IOException{
//		try {
//			//Will read in from BankDatabase.txt.
//			br = new BufferedReader(new FileReader("BankDatabase"));
//			String line;
//			
//			while((line = br.readLine()) != null) {
//				st = new StringTokenizer(line);
//				currentUser = new Account();
//				currentUser.setUsername(st.nextToken());
//				currentUser.setPassword(st.nextToken());
//				currentUser.setCheckingsBalance(Double.parseDouble(st.nextToken()));
//				currentUser.setSavingsBalance(Double.parseDouble(st.nextToken()));
//				currentUser.setIsAdmin(Boolean.parseBoolean(st.nextToken()));
//				currentUser.setCanAccess(Boolean.parseBoolean(st.nextToken()));
//				accounts.add(currentUser);
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			//Closes file input stream
//			if(br != null) {
//				br.close();
//			}
//		}
//	}
//	
////============ Updates bank database ============//
//	public void refreshDatabase() throws IOException{
//		try {
//			bw = new BufferedWriter(new FileWriter("BankDatabase"));
//			for (Account a : accounts)
//				bw.write(a.toString() + "\n");
//		} catch (FileNotFoundException e) {
//			logger.info("file not found exception.");
//			e.printStackTrace();
//		} finally {
//			if(bw != null){
//				bw.close();
//			}
//		}
//		logger.info("Bank database has been updated.");
//	}			
//	
//	public void signIn() throws IOException {
//		input = new Scanner(System.in);
//		System.out.println("Enter your username: ");
//		username = input.nextLine();
//		while (true) {
//			boolean validUser = false;
//			for (Account a : accounts) {
//				if (username.equals(a.getUsername())) {
//					accountIndex = accounts.indexOf(a);
//					currentUser = a;
//					validUser = true;
//				}	
//			}
//			if (validUser) {
//				break;
//			} else {
//				System.out.println("We do not recognize your username. Try again: ");
//				username = input.nextLine();
//			}
//		}
//		System.out.println("Hello " + currentUser.getUsername() + ", enter your password: ");
//		password = input.nextLine();
//		while (!password.equals(currentUser.getPassword())) {
//			System.out.println("That is incorrect. Try again: ");
//			password = input.nextLine();
//		}
//		if (currentUser.getIsAdmin() && currentUser.getCanAccess()) administrative(currentUser);
//		else if (currentUser.getCanAccess()) userMenu(currentUser);
//		else System.out.println("Your account has not been activated. You cannot sign in. \n\n");
//	}
//
////============ User main menu ============//
//	public void userMenu(Account a) {
//		logger.info("User has successsfully signed in.");
//		input = new Scanner(System.in);
//		boolean signout;
//		while (true) {
//			signout = false;
//			System.out.println("Welcome! What would you like to do? \n"
//					+ "1) Checkings balance \n"
//					+ "2) Savings balance \n"
//					+ "3) Deposit in checkings \n"
//					+ "4) Deposit in savings \n"
//					+ "5) Withdraw from checkings \n"
//					+ "6) Withdraw from savings \n"
//					+ "Type 'sign out' to return to login.");
//			userSelection = input.nextLine();
//			switch (userSelection) {
//				case "1": System.out.println("Your current balance is $" + a.getCheckingsBalance() + "\n"
//						+ "Press any key to return to main menu");
//						userSelection = input.nextLine();
//						break;
//				case "2": System.out.println("Your current balance is $" + a.getSavingsBalance() + "\n"
//						+ "Press any key to return to main menu");
//						userSelection = input.nextLine();
//						break;
//				case "3": deposit(a);
//						break;
//				case "4": deposit(a);
//						break;
//				case "5": withdraw(a);
//						break;
//				case "6": withdraw(a);
//						break;
//				case "sign out": signout = true;
//						break;
//				default: System.out.println("Invalid entry.");
//			}
//			if (signout) break;
//		}
//	}
//	
////============ deposit method ============//
//	public void deposit(Account a) {
//		input = new Scanner(System.in);
//		System.out.println("How much would you like to deposit?");
//		amount = Double.parseDouble(input.nextLine());
//		while (true) {
//			if (userSelection.equals("3") && amount > 0.0) {
//				a.setCheckingsBalance(a.getCheckingsBalance() + amount);
//				accounts.set(accountIndex, a);	//updates user info in arraylist
//				break;
//			} else if (userSelection.equals("4") && amount > 0.0) {
//				a.setSavingsBalance(a.getSavingsBalance() + amount);
//				accounts.set(accountIndex, a);	//updates user info in arraylist
//				break;
//			} else {
//				System.out.println("Invalid Entry.");
//				amount = Double.parseDouble(input.nextLine());
//			}
//		}
//		System.out.println("Transaction complete!");
//	}
//	
////============ withdraw method ============//
//	public void withdraw(Account a) {
//		input = new Scanner(System.in);
//		System.out.println("How much would you like to withdraw?");
//		amount = Double.parseDouble(input.nextLine());
//		while (true) {
//			if (userSelection.equals("5") && amount > 0.0) {
//				a.setCheckingsBalance(a.getCheckingsBalance() - amount);
//				if (a.getCheckingsBalance() < 0) 
//					System.out.println("You have overdrafted. Current balance: " + a.getCheckingsBalance()); 
//				accounts.set(accountIndex, a);	//updates user info in arraylist
//				break;
//			} else if (userSelection.equals("6") && amount > 0.0) {
//				if (a.getSavingsBalance() < 0) 
//					System.out.println("You have overdrafted. Current balance: " + a.getSavingsBalance());
//				accounts.set(accountIndex, a);	//updates user info in arraylist
//				break;
//			} else {
//				System.out.println("Invalid Entry.");
//				amount = Double.parseDouble(input.nextLine());
//			}
//		}
//		System.out.println("Transaction complete!");
//	}
//	
////============ Administrative controls ============//
//	public void administrative(Account acc) throws IOException {
//		String accStatus;
//		logger.info("administrator signin");
//		input = new Scanner(System.in);
//		boolean signout;
//		while (true) {
//			signout = false;
//			System.out.println("Hello, Mr. Rosario. How can I help you today? \n"
//					+ "1) View user accounts \n"
//					+ "2) Activate/deactivate account \n"
//					+ "3) Deposit checkings \n"
//					+ "4) Deposit savings \n"
//					+ "5) withdraw checkings \n"
//					+ "6) withdraw savings \n"
//					+ "7) Checkings balance \n"
//					+ "8) Savings balance \n"
//					+ "Type 'sign out' to return to login.");
//			userSelection = input.nextLine();
//			switch (userSelection) {
//				case "1": for (Account a : accounts) {
//							if (a.getCanAccess()) accStatus = "Activated";
//							else accStatus = "Not activated";
//							System.out.println(a.getUsername() + "\n"
//									+ "Checkings: $" + a.getCheckingsBalance() + "\n"
//									+ "Savings: $" + a.getSavingsBalance() + "\n"
//									+ "Account status: " + accStatus + "\n");
//						  }
//						  break;
//				case "2": activateAccount();
//						  break;
//				case "3": deposit(acc);
//						  break;
//				case "4": deposit(acc);
//						  break;
//				case "5": withdraw(acc);
//						  break;
//				case "6": withdraw(acc);
//						  break;
//				case "7": System.out.println("Your current balance is $" + acc.getCheckingsBalance() + "\n"
//						+ "Press any key to return to main menu");
//						userSelection = input.nextLine();
//						break;
//				case "8": System.out.println("Your current balance is $" + acc.getSavingsBalance() + "\n"
//						+ "Press any key to return to main menu");
//						userSelection = input.nextLine();
//						break;
//				case "sign out": signout = true;
//						break;
//				default: System.out.println("Invalid entry.");
//			}
//			if (signout) break;
//		}
//	}
//	
////============ Account activator/deactivator ============//
//	public void activateAccount() throws IOException {
//		boolean accExists = false;
//		input = new Scanner(System.in);
//		System.out.println("Which account would you like to activate/deactivate?");
//		username = input.nextLine();
//		for (Account a : accounts) {
//			if (username.equals(a.getUsername()) && !a.getCanAccess()) {
//				accountIndex = accounts.indexOf(a);
//				accExists = true;
//				a.setCanAccess(true);
//				accounts.set(accountIndex, a);
//				refreshDatabase();
//				logger.info("Account has been activated by admin.");
//				System.out.println("Account has been activated. \n");
//			}
//			else if (username.equals(a.getUsername()) && a.getCanAccess()) {
//				accountIndex = accounts.indexOf(a);
//				accExists = true;
//				a.setCanAccess(false);
//				accountIndex = accounts.indexOf(a);
//				refreshDatabase();
//				logger.info("Account has been deactivated by admin.");
//				System.out.println("Account has been deactivated. \n");
//			} else continue;
//		  }
//		if (!accExists) System.out.println("Account does not exist.");
//	}
}

