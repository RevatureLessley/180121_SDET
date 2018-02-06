package com.adminend;



import java.io.IOException;
import java.util.Scanner;
import org.apache.log4j.Logger;


import com.mainmenu.MainMenu;
import com.userend.BankAccount;

/**
 * 
 * Class cotains all options for the single Admin that already exist to validate new 
 * Registers accounts
 */
public class AdminMenu {

	final static Logger logger = Logger.getLogger(AdminMenu.class);

	public void showMenu(AdminAccount admin) {

		Scanner input = new Scanner(System.in);
		int selection = 0;
		while (selection != 4) {
			System.out.println("===================================================");
			System.out.println("\n------- Enter one of the following options ------- \n");
			System.out.println("1:  Approve User Account");
			System.out.println("2:  View All Transactions");
			System.out.println("3:  View All User Accounts");
			System.out.println("4:  Exit" + "\n");
			System.out.println("===================================================");
			
			System.out.print("Enter Option Here: ");
			selection = input.nextInt();

			switch (selection) {
			case 1:
				System.out.println("===================================================");
				System.out.println("\n-------------- Approve User Account ---------------\n");
				System.out.print("Enter Account Username: ");
				Scanner uName = new Scanner(System.in);
				String userName = uName.next();

				AdminAccountService.AproveAccount(userName);
				logger.info(userName +" HAS BEEN APPROVED BY ADMIN"); // logger here

				break;

			case 2: 
				System.out.println("================================================================================");
				AdminAccountService.displayAllTransactions();
				System.out.println("\n================================================================================ \n");
				
			case 3: 
				System.out.println("================================================================================");
				AdminAccountService.displayAllAccounts();
				
				System.out.println("\n================================================================================ \n");

				
			case 4:
				if (input != null) {
					MainMenu menu = new MainMenu();
					menu.start();
					input.close();

				}
			}
		}

	}
}
