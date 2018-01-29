package piggy.bank;

import java.util.Scanner;

public class Menu {
	Scanner sc = new Scanner(System.in);

	Bank b = new Bank();
	boolean exit;
	

	public static void main(String[] args) {
		Menu menu = new Menu();
		
		
		BankAccount bc = new BankAccount();
		menu.runMenu(bc); 
		double balance;
		System.out.println("Please enter an input:");
		
		

	}

	public void runMenu(BankAccount atm) {
		printHeader();
		while (!exit) {
			printMenu();
			int choice = getInput();
			performAction(choice, atm);
		}

		
	}

	private void printHeader() {
		System.out.println("Hello! Thank you for using this bank!");
	}

	private void printMenu() {
		System.out.println("What would you like to do?");
		System.out.println("1) Login");
		System.out.println("2) Create an Account");
		System.out.println("3) Withdraw");
		System.out.println("4) Deposit");
		System.out.println("5) Check Current Balance");
		System.out.println("6) Exit");
	}

	private int getInput() {
		int choice = 0;
		System.out.println("Please enter an input;");
		do {
			try {
				choice = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid option please enter a number between '1 - 6'");
			}
			if (choice < 1 || choice > 6) {
				System.out.println("Invalid option please enter a number between '1 - 6'");
			}
		} while (choice < 1 || choice > 6);
		return choice;
	}

	private void performAction(int choice, BankAccount atm) {
		switch (choice) {
		case 1:
			login(atm);
			break;
		case 2:
			createAccount(atm);
			break;
		case 3:
			MakeWithdraw(atm);
			break;
		case 4:
			makeDeposit(atm);
			break;
		case 5:
			checkBalance(atm);
			break;
		default:
			System.out.println("An error has occured");
		}

	}

	private void checkBalance(BankAccount atm) {
		System.out.println("You current balance is "); //Ask user for input
		double balance = atm.getBalance(); //Save the balance user wants into balance
		System.out.println("Your balance is currently" + balance); //Save balance into BankAccount
	}
	
	private void makeDeposit(BankAccount atm) {
		System.out.println("How much would you like to deposit?"); //Ask user for input
		double extraMoney = Double.parseDouble(sc.nextLine()); //Set extraMoney to how much user wants to deposit
		atm.deposit(extraMoney); //Add the deposit amount to the BankAccount

	}

	private void MakeWithdraw(BankAccount atm) {
		System.out.println("How much would you like to withdraw?");
		double takingMoney = Double.parseDouble(sc.nextLine()); // Set takingMoney to how much user wants to withdraw
		atm.withdraw(takingMoney); //subtract the deposit money to the BankAccount

	}

	private void createAccount(BankAccount atm) {
		String fullName, passWord, accountType = ("");
		double initialDeposit = 0;
		
		boolean valid = false;
		while(!valid) {
			System.out.println("Please choose an account type (checkings / savings): ");
			accountType = sc.nextLine(); //string accountType= checking / saving 
			if (accountType.equalsIgnoreCase("checkings") || accountType.equalsIgnoreCase("savings")) {
				valid = true;
				atm.setBankType(accountType); //Set Banktype as account type / Set bank type to checking or saving
			}
			else {
				System.out.println("Please enter checkings or savings");
			}
		}
		System.out.print("Please enter your full name: ");
//		fullName = sc.nextLine();
		String loggingIn = sc.nextLine(); // must look back here and see if it works
		atm.username(loggingIn); // this too
		System.out.print("Please enter a new password: ");
//		passWord = sc.nextLine();
		String passwordPlz = sc.nextLine();
		atm.password(passwordPlz);
		
		
		
		
		
		
		
		valid = false;
		while(!valid) {
			System.out.println("Please make an initial deposit");
			try {
				initialDeposit = Double.parseDouble(sc.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("Deposit must be a number");
			}
			if (accountType.equalsIgnoreCase("checkings")) {
				if (initialDeposit < 100 ) {
					System.out.println("Checking account requires more than a $100 initial deposit");
				} else {
					valid = true;
				}
			} else if(accountType.equalsIgnoreCase("savings")) {
				if (initialDeposit < 200) {
					System.out.println("Saving account requires more than a $200 initial deposit");
				}else {
					valid = true;
				}
			}
			atm.setBalance(initialDeposit); //Save the initialDeposit into balance
			System.out.println(initialDeposit); //Save initialDeposit into BankAccount
	
		}
		BankAccount ba;
		if (accountType.equalsIgnoreCase ("checkings")){
			ba = new Checking(initialDeposit);
		}else {
			ba = new Savings(initialDeposit);
			
		}
		
	}

	private void login(BankAccount login) {
		
		String fullName, passWord;
		System.out.print("Please enter your full name: ");
		fullName = sc.nextLine();
		System.out.print("Please enter a new password: ");
		passWord = sc.nextLine();
		System.out.println("Hello, " + fullName + "!");
		sc.nextLine();
		
		login.username(fullName); //Save the initialDeposit into balance
		System.out.println(fullName); //Save initialDeposit into BankAccount
		
		login.password(passWord);
		System.out.println(passWord);
		
		//double balance = atm.getBalance(); //Save the balance user wants into balance
		
		

	}
	
	
}
