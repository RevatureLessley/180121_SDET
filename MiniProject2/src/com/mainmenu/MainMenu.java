package com.mainmenu;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Scanner;

import com.adminend.AdminAccount;
import com.adminend.AdminAccountService;
import com.adminend.AdminMenu;
import com.userend.BankAccountService;
import com.userend.UserMenu;

import org.apache.log4j.Logger;


public class MainMenu {

	final static Logger logger = Logger.getLogger(MainMenu.class);

	public void start() {

		Scanner input = new Scanner(System.in);
		int selection = 0;
		while (selection != 4) {

			System.out.println("===================================================");
			System.out.println("\n------- Enter one of the following options ------- \n");
			System.out.println("1:  User Login");
			System.out.println("2:  Admin Login");
			System.out.println("3:  Register");
			System.out.println("4:  Exit \n");
			System.out.println("===================================================");
			System.out.print("Enter Option Here: ");
			selection = input.nextInt();

			switch (selection) {
			case 1:
				System.out.println("===================================================");
				System.out.println("\n----------------- User Login ------------------\n");
				System.out.println("Enter your UserName and Password");
				System.out.print("UserName: ");
				Scanner uName = new Scanner(System.in);
				String userName = uName.next();
				System.out.print("Password: ");
				Scanner pWord = new Scanner(System.in);
				String password = pWord.next();
				System.out.println("\n===================================================\n");
				
				BankAccountService.Login(userName, password);
				
				break;

			case 2:
				System.out.println("===================================================");
				System.out.println("\n----------------- Admin Login ------------------\n");
				System.out.println("[--- Hint: it is ADMIN12345 ---] \n");
				System.out.print("Enter ADMIN's Password: ");
				
				Scanner pWordA = new Scanner(System.in);
				String passwordA = pWordA.next();

				System.out.print("\n");

				AdminAccountService.Login(passwordA);

				System.out.println("===================================================");

				break;

			case 3:
				System.out.println("===================================================");
				System.out.println("\n----------------- Register User ------------------\n");
				System.out.print("First Name: ");
				Scanner fName = new Scanner(System.in);
				String firstName = fName.next();
				System.out.print("Last Name: ");
				Scanner lName = new Scanner(System.in);
				String lastName = lName.next();
				System.out.print("UserName: ");
				Scanner uNameR = new Scanner(System.in);
				String userNameR = uNameR.next();
				if(userNameR.equals("Admin") || userNameR.equals("admin")) {
					System.out.println("Sorry, Admins Cannot create user accounts \n");
					break;
				}
				System.out.print("Password: ");
				Scanner pWordR = new Scanner(System.in);
				String passwordR = pWordR.next();
				System.out.print("\n");

				BankAccountService.addAccount(firstName, lastName, userNameR, passwordR);
				
				System.out.println("     ACCOUNT CREATED - WAIT FOR ADMIN APPROVAL \n");
				System.out.println("===================================================\n");

				break;

			case 4:
				if (input != null)
					input.close();
				System.out.println("Goodbye");
				System.exit(0);
			}
		}
	}
}
