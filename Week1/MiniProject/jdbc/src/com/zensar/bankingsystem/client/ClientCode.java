package com.zensar.bankingsystem.client;

import java.util.ArrayList;
import java.util.Scanner;

import javax.security.auth.login.AccountNotFoundException;

import com.zensar.bankingsystem.beans.Account;
import com.zensar.bankingsystem.beans.Transaction;
import com.zensar.bankingsystem.exceptions.CustomerNotFoundException;
import com.zensar.bankingsystem.exceptions.InsufficientBalanceException;
import com.zensar.bankingsystem.exceptions.InvalidAccountNoException;
import com.zensar.bankingsystem.exceptions.InvalidAccountTypeException;
import com.zensar.bankingsystem.exceptions.InvalidAmountException;
import com.zensar.bankingsystem.exceptions.InvalidCustomerIdException;
import com.zensar.bankingsystem.exceptions.InvalidPincodeException;
import com.zensar.bankingsystem.serviceprovider.ServiceProvider;
import com.zensar.bankingsystem.services.BankServices;

public class ClientCode {

	public static void main(String[] args) {
		String name = null;
		String localAdrressCity = null;
		String localAdrressState = null;
		int localAdrressPinCode = 0;
		String homeAdrressCity = null;
		String homeAdrressState = null;
		int homeAdrressPinCode = 0;
		int customerId = 0;
		String accountType = null;
		int initialBalance = 0;
		char decision;
		int choice = 0;
		int amount = 0, balance = 0;

		Scanner scanner = new Scanner(System.in);

		BankServices zensarBankingService = ServiceProvider.getProvider();

		System.out.println("Welcome to Zensar Banking Service");

		do {

			System.out.println("1. Customer Registration");
			System.out.println("2. Open Account");
			System.out.println("3. Withdraw");
			System.out.println("4. Deposit");
			System.out.println("5. Transfer");
			System.out.println("6. Check balance");
			System.out.println("8. Get Account details");
			System.out.println("9. Get all account details");
			System.out.println("10. Delete customer");
			System.out.println("Enter your option");

			try {
				choice = scanner.nextInt();

				switch (choice) {
				case 1:
					// Registration
					System.out.println("Enter your name");
					name = scanner.next();

					System.out.println("Enter your local address");
					System.out.println("Enter your city");
					localAdrressCity = scanner.next();
					System.out.println("Enter your state");
					localAdrressState = scanner.next();
					System.out.println("Enter your pin code");
					localAdrressPinCode = scanner.nextInt();

					System.out.println("Enter your home address");
					System.out.println("Enter your city");
					homeAdrressCity = scanner.next();
					System.out.println("Enter your state");
					homeAdrressState = scanner.next();
					System.out.println("Enter your pin code");
					homeAdrressPinCode = scanner.nextInt();

					int id;

					id = zensarBankingService.acceptCustomerDetails(name,
							localAdrressCity, localAdrressState,
							localAdrressPinCode, homeAdrressCity,
							homeAdrressState, homeAdrressPinCode);
					System.out
							.println("You were successfully registered with customer ID "
									+ id);

					break;

				case 2:
					// open an account
					System.out
							.println("Enter your customer Id to open an account");
					customerId = scanner.nextInt();

					System.out.println("Enter your account type ");
					accountType = scanner.next();

					System.out.println("Enter your initial balance ");
					initialBalance = scanner.nextInt();
					int accountId;
					accountId = zensarBankingService.openAccount(customerId,
							initialBalance, accountType);

					System.out.println("Account created with account Id "
							+ accountId);

					break;
				case 3:
					// withdraw
					System.out.println("Enter your customer Id");
					customerId = scanner.nextInt();
					System.out.println("Enter your account Id");
					accountId = scanner.nextInt();
					System.out.println("Enter the amount to withdraw");
					amount = scanner.nextInt();

					balance = zensarBankingService.withdraw(customerId,
							accountId, amount);
					System.out
							.println("Amount withdrawn successfully with remaining balance "
									+ balance);

					break;
				case 4:
					// deposit
					System.out.println("Enter your customer Id");
					customerId = scanner.nextInt();
					System.out.println("Enter your account Id");
					accountId = scanner.nextInt();
					System.out.println("Enter the amount to deposit");
					amount = scanner.nextInt();

					balance = zensarBankingService.deposit(customerId,
							accountId, amount);
					System.out
							.println("Amount successfully deposited, balance is  "
									+ balance);

					break;
				case 5:
					System.out.println("Enter your customer Id");
					int custIdFrom = scanner.nextInt();
					System.out.println("Enter your account Id");
					int accNoFrom = scanner.nextInt();
					System.out.println("Enter the customer Id to transfer to");
					int custIdTo = scanner.nextInt();
					System.out.println("Enter the account Id to transfer to");
					int accNoTo = scanner.nextInt();
					System.out.println("Enter the amount to tansfer");
					int amt = scanner.nextInt();

					boolean result = zensarBankingService.fundTransfer(
							custIdFrom, accNoFrom, custIdTo, accNoTo, amt);
					if (result == true) {
						System.out.println("Fund transfer successful");
					} else {
						System.out.println("Fund transfer not successful");
					}
					break;
				case 6:
					System.out.println("Enter your customer Id");
					customerId = scanner.nextInt();
					System.out.println("Enter your account Id");
					accountId = scanner.nextInt();
					int b = zensarBankingService.getAccountBalance(customerId,
							accountId);
					System.out.println(b);

					break;
				case 8:
					// get account details
					System.out.println("Enter your customer Id");
					customerId = scanner.nextInt();
					System.out.println("Enter your account Id");
					accountId = scanner.nextInt();
					Account acc;
					acc = zensarBankingService.getAccountDetails(customerId,
							accountId);

					System.out.println("Account No " + acc.getAccountId());
					System.out.println("Account Type " + acc.getAccountType());
					System.out.println("Account Balance " + acc.getBalance());

					System.out.println("Transactions:");
					ArrayList<Transaction> transactions = zensarBankingService
							.getAllTransactionDetails(customerId, accountId);
					if (transactions == null) {
						System.out.println("No transactions yet");
					} else {
						for (Transaction transaction : transactions) {
							System.out.println("\nID: "
									+ transaction.getTransactionID());
							System.out.println("Type: "
									+ transaction.getTransactionType());
							System.out
									.println("Amount: "
											+ transaction
													.getTransactionAmount()
											+ "\n");
						}
					}

					break;
				case 9:
					// get all account details
					System.out.println("Enter your customer Id");
					customerId = scanner.nextInt();
					ArrayList<Account> a;
					a = zensarBankingService.getAllAccountsDetails(customerId);
					System.out.println("Account deatils are:");
					for (Account account : a) {
						if (account != null) {
							System.out.println("Account No: "
									+ account.getAccountId());
							System.out.println("Account Type: "
									+ account.getAccountType());
							System.out.println("Account Balance: "
									+ account.getBalance());

							System.out.println("Transactions:");

							ArrayList<Transaction> transactions1 = zensarBankingService
									.getAllTransactionDetails(customerId,
											account.getAccountId());
							if (transactions1 == null) {
								System.out.println("No transactions yet");
							} else {
								for (Transaction transaction : transactions1) {
									System.out.println("\nID: "
											+ transaction.getTransactionID());
									System.out.println("Type: "
											+ transaction.getTransactionType());
									System.out.println("Amount: "
											+ transaction
													.getTransactionAmount()
											+ "\n");
								}

								System.out.println("****************");
							}

						}
					}

					break;
				case 10:
					System.out.println("Enter your customer Id");
					customerId = scanner.nextInt();
					break;
				default:
					System.out.println("Invalid choice");
					break;
				}
			} catch (AccountNotFoundException e) {
				// e.printStackTrace();
				System.out.println(e.getMessage());
			} catch (InvalidPincodeException e) {
				// e.printStackTrace();
				System.out.println(e.getMessage());
			} catch (InvalidCustomerIdException e) {
				// e.printStackTrace();
				System.out.println(e.getMessage());
			} catch (CustomerNotFoundException e) {
				// e.printStackTrace();
				System.out.println(e.getMessage());
			} catch (InvalidAmountException e) {
				// e.printStackTrace();
				System.out.println(e.getMessage());
			} catch (InvalidAccountTypeException e) {
				// e.printStackTrace();
				System.out.println(e.getMessage());
			} catch (InvalidAccountNoException e) {
				// e.printStackTrace();
				System.out.println(e.getMessage());
			} catch (InsufficientBalanceException e) {
				// e.printStackTrace();
				System.out.println(e.getMessage());
			}

			System.out.println("Do you wish to continue?(Y/N)");
			decision = scanner.next().charAt(0);
			if (decision == 'n' || decision == 'N') {
				System.out.println("Thank you for using the banking system");
				System.exit(0);
			}
		} while (decision == 'y' || decision == 'Y');

		scanner.close();
	}
}