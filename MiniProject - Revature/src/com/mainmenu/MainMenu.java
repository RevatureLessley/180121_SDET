package com.mainmenu;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Scanner;

import com.adminend.AdminAccount;
import com.adminend.AdminMenu;
import com.adminend.RegisterAdmin;
import com.userend.Login;
import com.userend.RegisterUser;
import com.userend.UserMenu;

import org.apache.log4j.Logger;


public class MainMenu implements Serializable {

	private static final long serialVersionUID = 23874685733619556L;
	final static Logger logger = Logger.getLogger(MainMenu.class);


	RegisterUser registerU = new RegisterUser();
	Login login = new Login();
	RegisterAdmin registerA = new RegisterAdmin();

	public void start() {

		try {
			registerA.registeration();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
				
				try {
					login.confirmLogin(userName, password);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;

			case 2:
				System.out.println("===================================================");
				System.out.println("\n----------------- Admin Login ------------------\n");
				ObjectInputStream ois = null;
				System.out.print("Password: ");
				Scanner pWordA = new Scanner(System.in);
				String passwordA = pWordA.next();
				System.out.print("\n");

				try {
					ois = new ObjectInputStream(new FileInputStream("Admin.ser"));
					AdminAccount admin = (AdminAccount) ois.readObject();

					if (passwordA.equals(admin.getPassword())) {

						AdminMenu menu = new AdminMenu();
						menu.showMenu(admin);
					}

					else {
						System.out.println("Wrong password \n");
					}

				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				} finally {
					if (ois != null) {
						try {
							ois.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
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

				try {
					registerU.registeration(firstName, lastName, userNameR, passwordR, 0, false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("     ACCOUNT CREATED - WAIT FOR ADMIN APPROVAL \n");
				System.out.println("===================================================\n");

				break;

			case 4:
				if (input != null)
					input.close();

				System.exit(0);
			}
		}
	}
}
