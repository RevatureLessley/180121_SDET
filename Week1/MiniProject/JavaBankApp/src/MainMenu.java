import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {

	Scanner input = new Scanner(System.in);
	Bank bank = new Bank();
	boolean exit;


	public static void main(String[] agrs) {
		//creating an object of this class
		MainMenu menu = new MainMenu();
		menu.runMainMenu();
	}

	public void runMainMenu() {
		printHead();
		//to execute the program while not exit
		while(!exit){
			getMainMenu();
			int choice = getInput();
			execute(choice);
		}
	}


	private void printHead() {
		System.out.println("----------------------------------");
		System.out.println("   Welcome to Personal Banking    ");
		System.out.println("      Where your money grows      ");
		System.out.println("----------------------------------");

	}
	private void getMainMenu() {
		System.out.println("Hello, how can we help you?");
		System.out.println("1) Admin Login");
		System.out.println("2) Returning User Login");
		System.out.println("3) Create a new Account");
		System.out.println("4) Make a Deposit");
		System.out.println("5) Make a Withdrawal");
		System.out.println("6) List Account Balance");
		System.out.println("0) Exit");

	}

	private int getInput() {
		int choice = -1;
		//if I don't add a do while loop condition only runs once
		do {
			System.out.println("Enter your Choice:");
			try {
				//Attempt to convert userinput to a integer from scanner
				choice = Integer.parseInt(input.nextLine());
			}
			catch (NumberFormatException e) {
				System.out.println("Invalid choice try again");
			}
			//or statement
			if (choice < 0 || choice > 7) {
				System.out.println("Invalid Choice Try Again");
			}
		}while(choice < 0 || choice > 7);
		return choice;
	}

	private void execute(int choice) {
		//to create execute based on choice
		switch(choice) {
		case 0:
			System.out.println("Thank you, GoodBye");
			System.exit(0);
			//if you don't break the program falls through
			//these options execute based on choice
			break;
		case 1:
			AdminLogin();

			break;
		case 2:
			ReturningUserLogin();
		case 3:
			CreateNewAccount();

			break;
		case 4:
			MakeaDeposit();

			break;

		default:
			System.out.println("Oh no error");

		}

	}

	private void AdminLogin() {
		// TODO Auto-generated method stub

	}

	private void ReturningUserLogin() {
		// TODO Auto-generated method stub

	}

	private void CreateNewAccount() {
		String firstname, lastname, SSN, DOB, Email, AccountType = null;
		double DirectDeposit = 0;
		boolean verify = false;
		//while not verify is not false
		while(!verify) {
			System.out.print("Please Enter Your Account Type:    ");
			AccountType = input.nextLine();
			//this is to compare user input to string value
			if (AccountType.equals("checking") || AccountType.equals("savings")){
				//this will exit loop
				verify = true;
			}
			else {
				System.out.println("Invalid Choice, Please Enter Checking or Savings");
			}
		}
		System.out.print("Please Enter your First name:       ");
		firstname = input.nextLine();
		System.out.print("Please Enter your Last name:        ");
		lastname = input.nextLine();
		System.out.print("Please Enter your SSN:              ");
		SSN = input.nextLine();
		System.out.print("Please Enter your DOB:              ");
		DOB = input.nextLine();
		System.out.print("Please Enter your Email:            ");
		Email = input.nextLine();

		verify = false;
		while(!verify) {
			System.out.println("Please Enter your Deposit:   ");

			try {
				//this is to catch the error that would stop the program when user input is not a number
				DirectDeposit = Double.parseDouble(input.nextLine());
			}
			catch (NumberFormatException e) {
				System.out.println("Your Deposit must be a number");
			}
			if(AccountType.equalsIgnoreCase("checking")) {
				if(DirectDeposit < 100) {
					System.out.println("Your Deposit must be at least 100");
				}
				else {
					verify = true;
				}
				if(AccountType.equalsIgnoreCase("savings")) {
					if(DirectDeposit < 100) {
						System.out.println("Your Deposit must be at least 100");
					}
					else {
						verify = true;
					}
				}
			}
		}
		//create the account from UserAccount class
		UserAccount account;
		if(AccountType.equalsIgnoreCase("checking")) {
			//passing direct deposit requirement
			account = new checking(DirectDeposit);
		}
		else {
			account = new savings(DirectDeposit);
		}
		User customer = new User(firstname, lastname, SSN, DOB, Email, account);
		Bank.addUser(customer); //revisit
	}
	

	private void MakeaDeposit() {
		int account = selectAccount();
		System.out.print("How much do you want to deposit?     ");
		double amount = 0;
		try {
			amount = Double.parseDouble(input.nextLine());
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid, Try Again");
		}
		((Bank) bank.getCustomer(account)).getUserAccount().deposit(amount); //revisit
	}

	private int selectAccount() {
		ArrayList<User> customers = Bank.getUser();
		System.out.println("Select Account:    ");
		for (int i = 0; i < customers.size(); i++) {
			System.out.println((i+1) + "|" + customers.get(i).basicinformation());
		}
		int account = 0;
		System.out.print("Make your selection");
		try {
			account = Integer.parseInt(input.nextLine()) - 1;
		}
		catch (NumberFormatException e) {
			account = -1;
		}
		return account;
	}
	

	}

