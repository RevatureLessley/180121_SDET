import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import com.banksystem.beans.Account;

public class BankSystem {

	private static Collection<Account> accounts = new ArrayList<>();
	private static Account currentAccount;
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static Scanner sc;
	private static ObjectInputStream ois;
	private static ObjectOutputStream oos;

	public static void main(String[] args) throws IOException {

		// Read accounts from file, created an admin account if file does not
		// exist.
		if (!new File("accounts.ser").exists()) {
			Account newAccount = new Account("admin", "12345", true, true);
			accounts.add(newAccount);
		} else {
			try {
				ois = new ObjectInputStream(new FileInputStream("accounts.ser"));
				accounts = (ArrayList<Account>) ois.readObject();

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (ois != null)
					ois.close();
			}

		}

		// for (Account a : accounts)
		// System.out.println(a);

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
		try {
			oos = new ObjectOutputStream(new FileOutputStream("accounts.ser"));
			oos.writeObject(accounts);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (oos != null)
				oos.close();
		}

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
		Account newAccount = new Account(username, password, false, false);
		accounts.add(newAccount);

		BankLogger.infoMsg("Account '" + newAccount.getUsername()
				+ "' registered.");
		// System.out.println("\nAccount registered.\n");
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
				System.out.println("Your balance is $"
						+ currentAccount.getBalance());
				break;
			case "4":
				if (currentAccount.isAdmin()) {
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
		for (Account a : accounts) {
			if (!a.isApproved()) {
				System.out.println(a.getUsername());
			}
		}
		String input = "";
		while (true) {
			System.out
					.println("Approve an account by typing in its username (Or type in 'back' to go back):");
			sc = new Scanner(System.in);
			input = sc.nextLine();

			if (input.equals("back"))
				return;
			for (Account a : accounts) {
				if (a.getUsername().equals(input)) {
					a.setApproved(true);
					System.out.println();
					BankLogger.infoMsg("Account '" + a.getUsername()
							+ "' is Approved.");
					System.out.println();
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
			if (amount < currentAccount.getBalance()) {
				currentAccount.setBalance(currentAccount.getBalance() - amount);
				System.out.println();
				BankLogger.infoMsg("$" + amount + " is withdrew from account '"
						+ currentAccount.getUsername()
						+ "'. Your balance now is $"
						+ currentAccount.getBalance());
				System.out.println();
			} else {
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
			System.out.println();
			BankLogger.infoMsg("$" + amount + " is deposited into account '"
					+ currentAccount.getUsername() + "'. Your balance now is $"
					+ currentAccount.getBalance());
			System.out.println();
		} catch (NumberFormatException e) {
			System.out.println("Invalid input.");
		}
	}
}
