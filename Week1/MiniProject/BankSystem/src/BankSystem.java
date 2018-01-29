import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class BankSystem {

	private static Collection<Account> accounts = new ArrayList<>();
	private static Account currentAccount;
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static Scanner sc;

	public static void main(String[] args) throws IOException {

		Account acc = new Account();

		// Read from data file
		String line = "";
		try {
			br = new BufferedReader(new FileReader("bankAccounts.dat"));
			while ((line = br.readLine()) != null) {
				acc = new Account();
				acc.setUsername(line);
				line = br.readLine();
				acc.setPassword(line);
				line = br.readLine();
				acc.setAdmin(Boolean.parseBoolean(line));
				line = br.readLine();
				acc.setApproved(Boolean.parseBoolean(line));
				line = br.readLine();
				acc.setBalance(Double.parseDouble(line));

				accounts.add(acc);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				br.close();
		}

//		for (Account a : accounts)
//			System.out.println(a);

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
				login();
				break;
			case "2":
				System.out.println("Registering a new account...");
				register();
				break;
			default:
				System.out.println("Invalid option.");
				break;
			}
		}

		// Write to data file
		bw = new BufferedWriter(new FileWriter("bankAccounts.dat"));
		for (Account a : accounts) {
			bw.write(a.getUsername() + "\r\n");
			bw.write(a.getPassword() + "\r\n");
			bw.write(a.isAdmin() + "\r\n");
			bw.write(a.isApproved() + "\r\n");
			bw.write(a.getBalance() + "\r\n");
		}

		bw.close();
		sc.close();
	}

	private static void register() {
		String username;
		String password;

		// User Input
		sc = new Scanner(System.in);
		System.out.print("Username:");
		username = sc.nextLine();
		System.out.print("Password:");
		password = sc.nextLine();

		// Create account and add to collection
		Account newAccount = new Account(username, password);
		accounts.add(newAccount);

		System.out.println("\nAccount registered.\n");
	}

	private static void login() {
		String username;
		String password;
		sc = new Scanner(System.in);
		System.out.print("Username:");
		username = sc.nextLine();
		System.out.print("Password:");
		password = sc.nextLine();

		for (Account a : accounts) {
			if (a.getUsername().equals(username)
					&& a.getPassword().equals(password)) {
				if (a.isApproved()) {
					currentAccount = a;
					mainMenu();
					return;
				} else {
					System.out.println("\nYour account need to be approved.\n");
					return;
				}
			}
		}
		System.out.println("\nInvalid credentials.\n");

	}

	private static void mainMenu() {

		sc = new Scanner(System.in);
		String option = "";

		while (true) {
			System.out.println("==========================================");
			System.out.println("\nWelcome, " + currentAccount.getUsername()
					+ ". Please select an operation:");
			System.out.println("1 - Deposit Money");
			System.out.println("2 - Withdraw Money");
			System.out.println("3 - Check Balance");
			if (currentAccount.isAdmin())
				System.out.println("4 - Approve Account");
			System.out.println("0 - Logout");

			option = sc.nextLine();

			switch (option) {
			case "0":
				currentAccount = null;
				System.out.println("\nYou have logged out.\n");
				return;
			case "1":
				System.out.println("Making a deposit...");
				deposit();
				break;
			case "2":
				System.out.println("Withdrawing from your account...");
				withdraw();
				break;
			case "3":
				System.out.println("Your balance is $" + currentAccount.getBalance());
				break;
			case "4":
				if(currentAccount.isAdmin()){
					approveAccount();
					break;
				}
			default:
				System.out.println("Invalid option.");
				break;
			}
		}

	}

	private static void approveAccount() {
		System.out.println("Accounts pending for approval:");
		for(Account a: accounts){
			if(!a.isApproved()){
				System.out.println(a.getUsername());
			}
		}
		String input = "";
		while(true){
			System.out.println("Approve an account by typing in its username (Or type in 'back' to go back):");
			sc = new Scanner(System.in);
			input = sc.nextLine();
			
			if(input.equals("back"))
				return;
			for(Account a: accounts){
				if(a.getUsername().equals(input)){
					a.setApproved(true);
					return;
				}
			}
			System.out.println("Invalid username.");
		}
		
		
	}

	private static void withdraw() {
		sc = new Scanner(System.in);
		double amount;
		System.out.println("Enter the amount:");
		try {
			amount = Double.parseDouble(sc.nextLine());
			if(amount < currentAccount.getBalance()){
				currentAccount.setBalance(currentAccount.getBalance() - amount);
				System.out.println("Successfully withdrew $" + amount +". Your balance now is $" + currentAccount.getBalance());
			}else{
				System.out.println("You have insuffcient balance.");
			}
			
		} catch (NumberFormatException e) {
			System.out.println("Invalid input.");
		}

	}

	private static void deposit() {
		sc = new Scanner(System.in);
		double amount;
		System.out.println("Enter the amount:");
		try {
			amount = Double.parseDouble(sc.nextLine());
			currentAccount.setBalance(currentAccount.getBalance() + amount);
			System.out.println("Successfully deposited $" + amount +". Your balance now is $" + currentAccount.getBalance());
		} catch (NumberFormatException e) {
			System.out.println("Invalid input.");
		}
	}
}
