package com.bank.page;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bank.beans.Customer;
import com.bank.beans.User;
import com.bank.main.Driver;
import com.bank.services.CustomerService;
import com.bank.services.UserService;

public class Page {
	
	//logger
	final static Logger logger = Logger.getLogger(Driver.class);
		
	public static int homePage() {
		//Welcome screen and Menu option
		int selection = 0;
		Scanner i = new Scanner(System.in);
		System.out.println("======= Welcome to Bank ========");
		System.out.println(	"1.Login\n"+
							"2.New User\n"+
							"3.Exit");
		System.out.println("==================================");
		System.out.print("Enter your selection: ");
		try {
			selection = i.nextInt();
			System.out.println("Your selection is: "+selection+"\n");
		}catch(InputMismatchException e) {
			e.printStackTrace();
			logger.error("Wrong user input value"+e);
			System.out.println("\nEnter numbers only \n");
		}
		return selection;
	}


	
	public static void newUserPage() throws IOException {
		//New user Registration Page
		Scanner i = new Scanner(System.in);
		System.out.println("Welcome New User");
		System.out.println("Please fill the below information for Registration\n");
		System.out.print("Enter User Name: ");
		String userName = i.nextLine();
		System.out.print("Enter password: ");
		String password = i.nextLine();
		UserService.addNewUser(userName, password);
		System.in.read();
		logger.info("New User "+userName+" registration start");
	}

	public static void loginPage() throws IOException {
		// Administrator and User login
		Scanner i = new Scanner(System.in);
		System.out.print("Enter User Name: ");
		String userName = i.nextLine();
		System.out.print("Enter password: ");
		String password = i.nextLine();
		if(UserService.getUserStatus(userName, password)==-1) {
			invalidLogin();
		};
	}

	public static void invalidLogin() throws IOException {
		// login failed
		System.out.println("Invalid Username or password");
		System.in.read();
	}

	public static boolean exit() {
		// Exit Greet
		System.out.println("Thank you for Banking with us\n");
		logger.debug("User exit application");
		return false;
	}

	public static void outOfScope() {
		// Select again
		System.out.println("Please select again\n");
	}

	public static void nonUser() {
		// Non user login
		System.out.println("Our Administrator has not Approved your Account Yet\n"+
							"Please wait and try again later.\n");
	}

	public static void customerPage(User u) {
		// Customer account page
		Customer p = CustomerService.getCustomerInfo(u);
		
		logger.info(p.getUserName()+" sign in");
		System.out.println("Welcome "+p.getUserName()+"\n"+
							"Your current Balance is "+p.getAmount()+"\n"+
							"=========================================\n"+
							"1.Deposit\n"+
							"2.Withdraw\n"+
							"========================================+\n");
		Scanner i = new Scanner(System.in);
		int action = 0;
		while(true) {
			try {
				System.out.print("Enter your selection: ");
				action= i.nextInt();
				System.out.println("Your selection is: "+action+"\n");
			}catch(InputMismatchException e) {
				e.printStackTrace();
				System.out.println("\n Enter numbers only \n");
				logger.error("Wrong user input value");
			}
		    if(action == 1) {
		    	try {
					System.out.print("Enter your Amount: ");
					double amount= i.nextDouble();
					p.setAmount(p.getAmount()+amount);
					CustomerService.updateAmount(p);
				}catch(InputMismatchException e) {
					e.printStackTrace();
					System.out.println("\n Enter numbers only \n");
					logger.error("Wrong user input value");
				}
		    	break;
		    }
		    if(action == 2) {
		    	try {
					System.out.print("Enter your Amount: ");
					double amount= i.nextDouble();
					p.setAmount(p.getAmount()-amount);
					CustomerService.updateAmount(p);
				}catch(InputMismatchException e) {
					e.printStackTrace();
					System.out.println("\n Enter numbers only \n");
					logger.error("Wrong user input value");
				}
		    	break;
		    }
		}

	}

	public static void adminPage(User u) throws IOException {
		logger.debug("Admin sign in");
		System.out.println("Welcome Administrator\n");
		System.out.println("==================================");
		System.out.println(	"1.View Customers\n"+
							"2.View New Users\n");
		System.out.println("==================================");
		System.out.print("Enter your selection: ");
		Scanner i = new Scanner(System.in);
		int selection = 0;
		try {
			selection = i.nextInt();
			System.out.println("Your selection is: "+selection+"\n");
		}catch(InputMismatchException e) {
			e.printStackTrace();
			logger.error("Wrong user input value"+e);
			System.out.println("\nEnter numbers only \n");
		}
		if(selection == 1) {
			CustomerService.displayCustomer();
		}
		if(selection == 2) {
			List<User>users = UserService.get_NewUsers();
			Iterator<User> it = users.iterator();
			while (it.hasNext()) {
				User p = it.next();
				System.out.println("==================================");
				System.out.println(p.getUserName());
				System.out.println(	"1.Approve\n"+
									"2.Reject\n");
				System.out.println("==================================");
				int action = 0;
				while(true) {
					try {
						System.out.print("Enter your selection: ");
						action= i.nextInt();
						System.out.println("Your selection is: "+action+"\n");
					}catch(InputMismatchException e) {
						e.printStackTrace();
						System.out.println("\n Enter numbers only \n");
						logger.error("Wrong user input value");
					}
					if(action == 1) {
					    UserService.approved(p);
					    logger.debug(p.getUserName()+" account Approved");
					    break;
					}
					if(action == 2) {
						UserService.reject(p);
					    logger.debug(p.getUserName()+" account Rejected");
					    break;
					}
				}		
			}
			System.out.println("No more new account waiting for Approval\n");
			System.in.read();
		}
	}
}
