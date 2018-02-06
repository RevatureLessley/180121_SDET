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
			MakeaDeposit();
			MakeaWithrawl();
			selectAccount();
		}
	}


	private void printHead() {
		System.out.println("----------------------------------");
		System.out.println("   Welcome to Personal Banking    ");
		System.out.println("      Where your money grows      ");
		System.out.println("----------------------------------");

	}
	private void getMainMenu() {
		System.out.println("----------------------------------");
		System.out.println("Hello, how can we help you?");
		System.out.println("1) Admin Login");
		System.out.println("2) Create a new Account");
		System.out.println("3) Make a Deposit");
		System.out.println("4) Make a Withdrawal");
		System.out.println("5) List Account Balance");
		System.out.println("0) Exit");
		System.out.println("----------------------------------");
	}

	private int getInput() {
		int choice = -1;
		//if I don't add a do while loop condition only runs once
		do {
			System.out.print("Enter your Choice:");
			try {
				//Attempt to convert user input to a integer from scanner
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
			CreateNewAccount();

			break;
		case 3:
			MakeaDeposit();
			
			break;
		case 4:
			MakeaWithrawl();
			
			break;
		case 5:
			selectAccount();
			
			break;

		default:
			System.out.println("Oh no error");

		}
	}

	private void AdminLogin() {
		
	    Scanner keyb = new Scanner (System.in);
	    System.out.println("----------------------------------");
	    System.out.print("What is your Username?");
	    String username = keyb.nextLine();
	    System.out.print("What is your password?");
	    String password = keyb.nextLine();

	    System.out.println("");
		System.out.println("Firstname: Mat");
		System.out.println("Lastname : Lane");
		System.out.println("SSN      : 900-90-8098");
		System.out.println("AccountBalance: $1000");
	
	  
	}

	private void CreateNewAccount() {
		String firstname, lastname, SSN, DOB, Email, AccountType = null;
		double DirectDeposit = 0;
		boolean verify = false;
		//while not verify is not false
		while(!verify) {
			System.out.print("Please Enter Your Account Type(checking or savings):    ");
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
			System.out.print("Please Enter your Deposit:   ");

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
	}


	private void MakeaDeposit() {
		Scanner key = new Scanner (System.in);
		    
		System.out.print("What is your Username?");
		String user = key.nextLine();
		System.out.print("What is your password?");
		String pass = key.nextLine();
		System.out.print("Welcome, how can we help you   ");
		
		System.out.print("How much do you want to deposit?     ");
		String deposit = key.nextLine();
		
		System.out.print("You now have deposited $" + deposit + " into your account ");
		System.exit(0);
		
		double amount = 0;

		try {
			amount = Double.parseDouble(input.nextLine());
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid, Try Again");
			amount = 0;
		}
	}

	private void MakeaWithrawl() {
		Scanner keyboard = new Scanner (System.in);
	    
		System.out.print("What is your Username?");
		String user = keyboard.nextLine();
		System.out.print("What is your password?");
		String pass = keyboard.nextLine();
		System.out.print("Welcome, how can we help you");
		    
		System.out.print("How much do you want to withdrawal?     ");
		String withdrawan = keyboard.nextLine();
		
		System.out.print("You now have withdrawan $" + withdrawan + "into your account ");
		
		System.out.println("Thank you, GoodBye");
		System.exit(0);
		
		double amount = 0;
		try {
			amount = Double.parseDouble(input.nextLine());
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid, Try Again");
			amount = 0;
		}
	}

	private void selectAccount() {
		System.out.println("----------------------------------");
		System.out.println("Firstname: Mat");
		System.out.println("Lastname : Lane");
		System.out.println("SSN      : 900-90-8098");
		System.out.println("AccountBalance: $1000");
		System.out.println("----------------------------------");
	}


}

