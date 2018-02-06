package com.revature.backend;

import java.io.IOException;
import java.sql.Connection;
import java.util.ListIterator;
import java.util.Scanner;
//import org.apache.log4j.Logger;

import com.revature.backend.*;
import com.revature.communication.Bridge;

public class MenuActions {

	public static void SleepyPupperBankingSystem2 () {
		
		try {} 
		catch (Exception e) {} 
		finally {}
		
		Scanner s = singleScanner.getScanner();
		startMenu(s);
	}
	
	//This function is used to create a singleton scanner object to be used throughout the whole of the application.
	public static class singleScanner {
		private static Scanner scanner;
		
		public static Scanner getScanner(){
			if(scanner == null){scanner = new Scanner(System.in);}
			return scanner;
		}
		public static void closeScanner() {scanner.close();}
	}

	//This function is used to start the SleppyPupper Banking systems menu.
	public static void startMenu(Scanner s) {
		textIntro();
		textOption1();
		menuScreen1(s);
	}

	//This function will display a good bye message to the user, serialize userLister back into a file to save it and also close the scanner.
	public static void exitApplication() {
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("=====================================================");
		System.out.println("Thank  you for using the SleepyPupper Banking System!"
							+ "\nGoodbye!");
		System.out.println("=====================================================");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println();
		DataManger.flush();
		singleScanner.closeScanner();
	}


	//This is one of the many console menu methods used that only display text to the user.
	public static void textIntro(){		
		System.out.println("=====================================================");
		System.out.println("Hello and welcome to the SleepyPupper Banking System.");
		System.out.println("=====================================================");
		System.out.println("Please use your keyboard to nagivate through our menu.");
		System.out.println("Your current options are as follows:");
		System.out.println("=====================================================");
	}
	//This is one of the many console menu methods used that only display text to the user.
	public static void textOption1() {
		System.out.println("=====================================================");
		System.out.println("Please type in the following options EXACTLY how you see them");
		System.out.println("=====================================================");
		System.out.println("Please press |'1'| to login to you account.");
		System.out.println("Please press |'2'| to register for an account if you do not have one.");
		System.out.println("Please press |'exit'| to close the application.");
		System.out.println("=====================================================");
		System.out.println("You MUST exit The SleeperPupper Banking System sucessfully if you want to save ANY changes.");
		System.out.println("=====================================================");
	}
	//This is one of the many console menu methods used that only display text to the user.
	public static void textClearSpacing() {
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println();
	}
	/* This is the first menu screen. This screen will be what the any user first encounters. This screen will handle the 
	 * users option of "login", "register" and "exit" by bringing the user to the corresponding screen or exiting the application respectively.*/
	public static void menuScreen1(Scanner s) {
		
		String option = s.next();
		
		switch(option){
		
		case "1":
			System.out.println("=====================================================");
			System.out.println("You have selected 'LOGIN'");
			System.out.println("=====================================================");
			textClearSpacing();
			menuScreen2(s);
			break; 
		case "2":
			System.out.println("=====================================================");
			System.out.println("You have selected 'REGISTER'");
			System.out.println("=====================================================");
			textClearSpacing();
			menuScreen3(s);
			break;
		case "exit":
			exitApplication();
			break;
		case " ":
			System.out.println("=====================================================");
			System.out.println("Invalid option, please input either '1' or '2' or 'exit' for this menu:");
			System.out.println("=====================================================");
			textClearSpacing();
			startMenu(s);
			break;
		case "debugmenu":
			try (Connection conn = Bridge.connect()){System.out.println("SUCESS");}
			catch (Exception e) {e.printStackTrace();System.out.println("FAILURE?");}
			DataManger.checkAllUsers();
			System.out.println("===============Total number of users=================");
			System.out.println(DataManger.returnTotalUsers());
			System.out.println("===============Total number of users=================");
			
			
			break;
		default:
			System.out.println("=====================================================");
			System.out.println("Invalid option, please input either '1' or '2' or 'exit' for this menu:");
			System.out.println("=====================================================");
			textClearSpacing();
			startMenu(s);
			break;
		}
	}
	/*This is the another menu screen, which is called when a user attempts to login.
	 *The users credentials will be asked for and passed to menuLogin() for validation. */
	public static void menuScreen2(Scanner s) {
		String inputUsername= menuInputUsername(s);
		String inputPassword = menuInputPassword(s);
		menuLogin(inputUsername, inputPassword,s);
	}
	/*This is the another menu screen, which is called when a user attempts to register.
	 *The users will be asked for a username and password and passed to menuRegister() for validation.
	 *However the attempted username is first checked against userList for any possible duplicates.
	 *In which case the user will be looped back to this same screen and asked to try again.*/
	public static void menuScreen3(Scanner s) {
		textIntro();
		String inputUsername= menuInputUsername(s);
		
		if(DataManger.checkDuplicateUser(inputUsername) == false) {	
			String inputPassword = menuInputPassword(s);
			menuRegister(inputUsername, inputPassword);
			textClearSpacing();
			System.out.println("=====================================================");
			System.out.println("=====================================================");
			System.out.println("--------------------------------------------------------------");
			System.out.println("Thank you very much for Registering an account with The SleepyPupper Banking System!\n"
					+ "Please keep in mind that your account needs to FIRST be VALIDATED by an admin BEFORE you may login"
					+ "successfully.\nYou will now be return to the main menu.");
			System.out.println("----------------------------------------------------------------");
			System.out.println("=====================================================");
			System.out.println("=====================================================");
			startMenu(s);
			}else {	
				textClearSpacing();
				System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
				System.out.println("Username is already taken! Sorry! Please select a different one.");
				System.out.println("Returning username input");
				System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
				menuScreen3(s);
				}
	}
	
	

	//This function is used to get a user's input for a username
	public static String menuInputUsername(Scanner s) {
		
		System.out.println("=====================================================");
		System.out.println("Please Type in your Username");
		System.out.println("=====================================================");
		return s.next(); 
	}
	//This function is used to get a user's input for a password
	public static String menuInputPassword(Scanner s) {
		System.out.println("=====================================================");
		System.out.println("Please Type in your password");
		System.out.println("=====================================================");
		return s.next(); 		
	}


	public static void menuRegister (String username, String password) {DataManger.addNewUser(username, password);}
	
	public static void menuLogin(String u, String p, Scanner s) {	
	int key = DataManger.checkLogin(u, p);
	switch (key) {
	case 0:
		textClearSpacing();
		System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
		System.out.println("Username NOT FOUND!!!");
		System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
		System.out.println("You are the first ever user of the SleepyPupperBankingSystem!"
						+ "\nPlease go back to the main menu and REGISTER for an account!"
						+ "\nReturning to main menu.");
		System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
		textClearSpacing();
		startMenu(s);
		break;
	case 1:
		textClearSpacing();
		System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
		System.out.println("Username NOT FOUND!!!");
		System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
		System.out.println("Please go back to the main menu and REGISTER for an account!"
							+ "\nReturning to main menu.");
		System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
		textClearSpacing();
		startMenu(s);
		break;
	case 2:
		textClearSpacing();
		System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
		System.out.println("WRONG USERNAME OR PASSWORD!");
		System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
		System.out.println("Please verify your Information!" + "\nReturning to main menu.");
		System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
		textClearSpacing();
		startMenu(s);
		break;
	case 3:
		textClearSpacing();
		System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
		System.out.println("USER NOT YET VALIDATED!!!");
		System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
		System.out.println("Please contact an administrator to have your account validated!" 
							+ "\nReturning to main menu.");
		System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
		textClearSpacing();
		startMenu(s);
		break;
	case 4:
		menuScreenUser(s);
		break;
	case 5:
		menuScreenAdmin(s);
		break;
	default:
		System.err.println("Something must have gone really wrong...");
		break;
		}
	}
	//This is the page an admin sees after successfully logging in. An admin can either exit or validate a user.
	public static void menuScreenAdmin(Scanner s) {
		System.out.println("=====================================================");
		System.out.println("Hello and welcome to the SleepyPupper Banking System.");
		System.out.println("=====================================================");
		System.out.println();
		System.out.println("=====================ADMIN LOGIN=====================");
		System.out.println();
		System.out.println("=====================================================");
		System.out.println("Please use your keyboard to nagivate through our menu.");
		System.out.println("Your current options are as follows:");
		System.out.println("=====================================================");
		System.out.println("=====================================================");
		System.out.println("Please type in the following options EXACTLY how you see them");
		System.out.println("=====================================================");
		System.out.println("Please type | 'all'| To see list of all users");
		System.out.println("Please type |'val'| validate a user account");
		System.out.println("Please type |'exit'| to close the application.");
		System.out.println("=====================================================");
		System.out.println("You MUST exit The SleeperPupper Banking System sucessfully if you want to save ANY changes.");
		System.out.println("=====================================================");
		
		String key = s.next();
		
		switch (key) {
		case "all":
			DataManger.checkAllUsers();
			menuScreenAdmin(s);
			break;
		case"val":
			
			
			textClearSpacing();
			
			System.out.println("=====================================================");
			System.out.println("Hello and welcome to the SleepyPupper Banking System.");
			System.out.println("=====================================================");
			System.out.println();
			System.out.println("=====================ADMIN LOGIN=====================");
			System.out.println();
			System.out.println("=====================================================");
			System.out.println("Please use your keyboard to nagivate through our menu.");
			System.out.println("Your current options are as follows:");
			System.out.println("=====================================================");
			System.out.println("=====================================================");
			System.out.println("Please Type in The Username You wish to VALIDATE");
			System.out.println("=====================================================");
			
			String valUsername = s.next(); 
			
			System.out.println("=====================================================");
			System.out.println("Please Type in Your Admin Password for security");
			System.out.println("=====================================================");
			
			String security = s.next();
			
			int key1 = DataManger.validateUser(valUsername, security);
			
			menuAdminValOption(key1,s);
			
			
			break;
		case "exit":
			exitApplication();
			break;
		default:
			System.out.println("=====================================================");
			System.out.println("Invalid option, please input either 'all' or 'val' or 'exit' for this menu:");
			System.out.println("=====================================================");
			textClearSpacing();
			menuScreenAdmin(s);
			break;
		}
	}
	//This is the page users see if they are able to log in successfully. They have the option to withdraw/deposit bits or exit.
		public static void menuScreenUser(Scanner s) {
			
			System.out.println("=====================================================");
			System.out.println("Hello and welcome to the SleepyPupper Banking System.");
			System.out.println("=====================================================");
			System.out.println();
			System.out.println("=====================|" + DataManger.getLoggedInUsername() + "| LOGIN=====================");
			System.out.println("==============|Your balance is: " + DataManger.getLoggedInBits() + "|=====================");
			System.out.println();
			System.out.println("=====================================================");
			System.out.println("Please use your keyboard to nagivate through our menu.");
			System.out.println("Your current options are as follows:");
			System.out.println("=====================================================");
			System.out.println("=====================================================");
			System.out.println("Please type in the following options EXACTLY how you see them");
			System.out.println("=====================================================");
			System.out.println("Please type |'add'| To deposit bits to your account");
			System.out.println("Please type |'sub'| To withdraw bits to your account");
			System.out.println("Please type |'hist'| To see your Transcation History");
			System.out.println("Please type |'exit'| to close the application.");
			System.out.println("=====================================================");
			System.out.println("You MUST exit The SleeperPupper Banking System sucessfully if you want to save ANY changes.");
			System.out.println("=====================================================");
			
			String key = s.next();
			
			switch (key) {
			case"add":
				System.out.println("=====================================================");
				System.out.println("You have selected 'ADD'");
				System.out.println("=====================================================");
				textClearSpacing();
				System.out.println("Please type in the exact amount you would like to deposit:");
				System.out.println("=====================================================");
				
				int deposit = s.nextInt();
				
				
				if (DataManger.transcationDeposit(deposit)) {
					int bal = DataManger.getLoggedInBits();
					textClearSpacing();
					System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
					System.out.println("BITS DEPOSITED SUCESSFULLY!");
					System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
					System.out.println("Your balance as now increased to: |" + bal 
										+ "|\nThe SleepyPupper Banking System will now close for security purposes."
										+ "\nNow Exiting.");
					System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
					textClearSpacing();
					exitApplication();
				}else {System.err.println("Something must have gone really wrong...");}
				break;
			case "sub":
				System.out.println("=====================================================");
				System.out.println("You have selected 'SUB'");
				System.out.println("=====================================================");
				textClearSpacing();
				System.out.println("Please type in the exact amount you would like to withdraw:");
				System.out.println("=====================================================");
				
				int withdraw = s.nextInt();
				
				
				if (DataManger.transcationWitdraw(withdraw)) {
					int bal = DataManger.getLoggedInBits();
					textClearSpacing();
					System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
					System.out.println("BITS WITHDRAWN SUCESSFULLY!");
					System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
					System.out.println("Your balance as now decreased to: |" + bal 
										+ "|\nThe SleepyPupper Banking System will now close for security purposes."
										+ "\nNow Exiting.");
					System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
					textClearSpacing();
					exitApplication();
				}else {	
				System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
				System.out.println("=!=!=!=!=!=!=!=!=!=!INSUFFICENT FUNDS!!=!=!=!=!=!=!=!=!=!");
				System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
				System.out.println("NOT enough BITs in your account to withdraw!"
									+ "\nReturning to User menu.");
				System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
				textClearSpacing();
				menuScreenUser(s);
				}
				break;
				
				
				
			case "hist":
				System.out.println("=====================================================");
				System.out.println("You have selected 'HIST'");
				System.out.println("=====================================================");
				DataManger.transactionHistory( DataManger.getLoggedInUsername());
				textClearSpacing();
				menuScreenUser(s);
				
				break;
			case "exit":
				exitApplication();
				break;

			default:
				System.err.println("Something must have gone really wrong...");
				break;
			}
		
		}

		
		
	public static void menuAdminValOption(int key, Scanner s) {
		
		switch (key) {
		case 0:
			textClearSpacing();
			System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
			System.out.println("Username NOT FOUND!!!");
			System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
			System.out.println("There are currently no users in list.\nReturning to Admin Menu Screen.");
			System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
			textClearSpacing();
			menuScreenAdmin(s);
			break;
		case 1: 
			textClearSpacing();
			System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
			System.out.println("WRONG ADMIN PASSWORD!");
			System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
			System.out.println("Please verify your Information!" + "\nNow Exiting.");
			System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
			textClearSpacing();
			exitApplication();
			break;
		case 2: 
			textClearSpacing();
			System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
			System.out.println("Username NOT FOUND!!!");
			System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
			System.out.println("Please have User go back to the main menu and REGISTER for an account!"
								+ "\nReturning to Admin Menu Screen.");
			System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
			textClearSpacing();
			menuScreenAdmin(s);
			break;
		
		case 3: 
			textClearSpacing();
			System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
			System.out.println("USER VALIDATED SUCESSFULLY!");
			System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
			System.out.println("Please have user attempt to login!"
								+ "The SleepyPupper Banking System will now close for security purposes."
								+ "\nNow Exiting.");
			System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
			textClearSpacing();
			exitApplication();
			break;
			
		case 4: 
			textClearSpacing();
			System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
			System.out.println("Username ALREADY VALIDATED!!!");
			System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
			System.out.println("Please have User go back to the main menu and Login!"
								+ "\nReturning to Admin Menu Screen.");
			System.out.println("=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=!=");
			textClearSpacing();
			menuScreenAdmin(s);
			break;
		default:
			System.err.println("Something must have gone really wrong...");
			break;
		}
	}
		

}
