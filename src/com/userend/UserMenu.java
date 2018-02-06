package com.userend;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.Scanner;
import org.apache.log4j.Logger;

import com.mainmenu.MainMenu;



/**
 * 
 * @author Amr Hosny
 * menu for already pre-existing account holders who accounts have been validated 
 * by the Admin. also calls on the withdraw and deposit methods from the BankAccount 
 * class as well as showing user balance
 */
public class UserMenu {
	final static Logger logger = Logger.getLogger(UserMenu.class);

	public void showMenu(BankAccount user) {

		Scanner input = new Scanner(System.in);
		int selection = 0;
		while (selection != 4) {
			System.out.println("===================================================");
			System.out.println("\n------- Enter one of the following options ------- \n");
			System.out.println("1:  Withdraw");
			System.out.println("2:  Deposit");
			System.out.println("3:  Show Balance");
			System.out.println("4:  Exit \n");
			System.out.println("===================================================");
			System.out.print("Enter Option Here: ");
			selection = input.nextInt();

			switch (selection) {
			case 1:
				// Call the method for withdrawing money from the account
				System.out.println("===================================================");
				System.out.println("\n----------------- Withdraw ------------------\n");
				System.out.print("Enter Amount: $ ");
				Scanner withdraw = new Scanner(System.in);
				int withdrawAmt = withdraw.nextInt();
				BankAccountService.Withdraw(user, withdrawAmt);
				logger.info("Account" + user.getUserName() +"has withdrawn" + withdrawAmt); // logger here
				System.out.println("Your Balance is Now $" + user.getBalance());
				
				System.out.println("\n===================================================\n");

				break;

			case 2:
				System.out.println("===================================================");
				System.out.println("\n----------------- Deposit ------------------\n");
				System.out.print("Enter Amount: $ ");
				// Call the method for depositing money to the account
				Scanner deposit = new Scanner(System.in);
				int depositAmt = deposit.nextInt();
				BankAccountService.Deposit(user, depositAmt);
				System.out.println("Your Balance is Now $" + user.getBalance());
				logger.info("Account" + user.getUserName() +"has deposited" + depositAmt); // logger here
				System.out.println("\n===================================================\n");

				break;

			case 3:
				System.out.println("===================================================");
				System.out.println("\n----------------- Balance ------------------\n");
				
				try {
					System.out.println("Your Balance is $" + BankAccountService.getBalance(user) + "\n");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("\n===================================================\n");

				break;

			case 4:
				
				if (input != null) {
					MainMenu menu = new MainMenu();
					menu.start();
					input.close();

				}

				
			}
		}
	}

	/**
	 * 
	 * Reserializing the object to update the user account after a deposit or withdraw from the account
	 * is made.
	 */
	public void reSerializeObject(BankAccount user) {

		ObjectOutputStream os = null;
		try {
			os = new ObjectOutputStream(new FileOutputStream(user.getUserName() + ".ser"));

			os.writeObject(user);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
