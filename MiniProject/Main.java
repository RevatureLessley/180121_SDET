import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
	List<Account> accounts = new ArrayList<Account>(); //database will be stored here
	private BufferedWriter bw;
	private BufferedReader br;
	private StringTokenizer st;
	private String username;
	private String password;
	private String userSelection;
	private Account newUser;
	private Account currentUser;
	private double amount;
	private int accountIndex;
	Scanner input;
	
	public static void main(String[] args) throws IOException {
		Main m = new Main();
		m.retrieveAccounts();
		m.selectionMenu();
		m.refreshDatabase();
		m.input.close();
	}
	
	public void selectionMenu() throws IOException {
		userSelection = "";	
		input = new Scanner(System.in);
		
		while (true) {
			System.out.println("Welcome to the Bank! \n"
					+ "1) Create new account \n"
					+ "2) Sign in \n"
					+ "Type 'exit' to quit");
		
			userSelection = input.nextLine();
			if (userSelection.equals("1")) {
				newUser = new Account();
				newUser.setUsername(createUsername());
				newUser.setPassword(createPassword());
				System.out.println("Success!");
					
				//stores new user info in arraylist and updates database
				accounts.add(newUser);
				refreshDatabase();
			} else if (userSelection.equals("2")) {
				signIn();
			} else if (userSelection.equals("exit")){
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
				for (Account a : accounts) {
					if (username.equals(a.getUsername())) {
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
			//input.close();
			return password;
	}
	
//============ Retrieves accounts from database ============//
	public void retrieveAccounts() throws IOException{
		try {
			//Will read in from BankDatabase.txt.
			br = new BufferedReader(new FileReader("BankDatabase"));
			String line;
			
			while((line = br.readLine()) != null) {
				st = new StringTokenizer(line);
				currentUser = new Account();
				currentUser.setUsername(st.nextToken());
				currentUser.setPassword(st.nextToken());
				currentUser.setCheckingsBalance(Double.parseDouble(st.nextToken()));
				currentUser.setSavingsBalance(Double.parseDouble(st.nextToken()));
				accounts.add(currentUser);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//Closes file input stream
			if(br != null) {
				br.close();
			}
		}
	}
	
	public void refreshDatabase() throws IOException{
		try {
			bw = new BufferedWriter(new FileWriter("BankDatabase"));
			for (Account a : accounts)
				bw.write(a.toString() + "\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(bw != null){
				bw.close();
			}
		}
	}			
	
	public void signIn() {
		input = new Scanner(System.in);
		System.out.println("Enter your username: ");
		username = input.nextLine();
		while (true) {
			boolean validUser = false;
			for (Account a : accounts) {
				if (username.equals(a.getUsername())) {
					accountIndex = accounts.indexOf(a);
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
		userMenu(currentUser);
	}


	public void userMenu(Account a) {
		input = new Scanner(System.in);
		boolean signout;
		while (true) {
			signout = false;
			System.out.println("Welcome! What would you like to do? \n"
					+ "1) Checkings balance \n"
					+ "2) Savings balance \n"
					+ "3) Deposit in checkings \n"
					+ "4) Deposit in savings \n"
					+ "5) Withdraw from checkings \n"
					+ "6) Withdraw from savings \n"
					+ "Type 'sign out' to return to login.");
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
				case "4":deposit(a);
						break;
				case "5": withdraw(a);
						break;
				case "6": withdraw(a);
						break;
				case "sign out": signout = true;
						break;
				default: System.out.println("Invalid entry.");
			}
			if (signout) break;
		}
	}
	
	public void deposit(Account a) {
		input = new Scanner(System.in);
		System.out.println("How much would you like to deposit?");
		amount = Double.parseDouble(input.nextLine());
		while (true) {
			if (userSelection.equals("3") && amount > 0.0) {
				a.setCheckingsBalance(a.getCheckingsBalance() + amount);
				accounts.set(accountIndex, a);	//updates user info in arraylist
				break;
			} else if (userSelection.equals("4") && amount > 0.0) {
				a.setSavingsBalance(a.getSavingsBalance() + amount);
				accounts.set(accountIndex, a);	//updates user info in arraylist
				break;
			} else {
				System.out.println("Invalid Entry.");
				amount = Double.parseDouble(input.nextLine());
			}
		}
		System.out.println("Transaction complete!");
	}
	
	public void withdraw(Account a) {
		input = new Scanner(System.in);
		System.out.println("How much would you like to withdraw?");
		amount = Double.parseDouble(input.nextLine());
		while (true) {
			if (userSelection.equals("5") && amount > 0.0) {
				a.setCheckingsBalance(a.getCheckingsBalance() - amount);
				if (a.getCheckingsBalance() < 0) 
					System.out.println("You have overdrafted. Current balance: " + a.getCheckingsBalance()); 
				accounts.set(accountIndex, a);	//updates user info in arraylist
				break;
			} else if (userSelection.equals("6") && amount > 0.0) {
				if (a.getSavingsBalance() < 0) 
					System.out.println("You have overdrafted. Current balance: " + a.getSavingsBalance());
				accounts.set(accountIndex, a);	//updates user info in arraylist
				break;
			} else {
				System.out.println("Invalid Entry.");
				amount = Double.parseDouble(input.nextLine());
			}
		}
		System.out.println("Transaction complete!");
	}
}