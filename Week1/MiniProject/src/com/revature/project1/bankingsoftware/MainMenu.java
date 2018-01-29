package com.revature.project1.bankingsoftware;

import java.io.IOException;
import java.io.Serializable;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.logging.Logger;

public class MainMenu extends Backend{
/*This is the function that will be called by the driver to start the SleepyPupper Banking system.
 * I have restricted the Driver to 2 lines of code for the purpose of abstraction and encapsulation.
 * This function first deserializes the file so that the arraylist userList may be populated with all registered users.
 * It will also catch two possible exceptions; both IOExceptions and ClassNotFoundExceptions, where in both cases an error message is sent out.
 * this function will also creat the singleton scanner object that will be used throughout the the application. Finally start menu will be called 
 * to officially start the SleepyPupper Banking System.*/
	public static void SleepyPupperBankingSystem() {
		try {deserializeFile();} 
		catch (IOException e) {simpleLogging("File 'registry' is missing!");} 
		catch(ClassNotFoundException e) { simpleLogging("Arrary of Objects for 'userList' to read File 'registry' is missing!");}
		
		Scanner s = singleScanner.getScanner();
		startMenu(s);
	}
	
	private final static Logger LOGGER = Logger.getLogger(MainMenu.class.getName());
	public static void simpleLogging(String msg){LOGGER.info(msg);}
	
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
		
		try {serializeFile();} 
		catch (IOException e) {simpleLogging("File 'registry' is missing!");;}
		singleScanner.closeScanner();
	}
	
	//This is one ofthe many console menu methods used that only display text to the user.
	public static void textClearSpacing() {
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println();
	}
	//This is one ofthe many console menu methods used that only display text to the user.
	public static void textIntro(){		
		System.out.println("=====================================================");
		System.out.println("Hello and welcome to the SleepyPupper Banking System.");
		System.out.println("=====================================================");
		System.out.println("Please use your keyboard to nagivate through our menu.");
		System.out.println("Your current options are as follows:");
		System.out.println("=====================================================");
	}
	//This is one ofthe many console menu methods used that only display text to the user.
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
		default:
			System.out.println("=====================================================");
			System.out.println("Invalid numbered option, please input either '1' or '2' for this menu:");
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
	/*This is the another menu screen, which is caleld when a user attempts to register.
	 *The users will be asked for a username and password and passed to menuRegister() for validation.
	 *However the attempted username is first checked against userList for any possible duplicates.
	 *In which case the user will be looped back to this same screen and asked to try again.*/
	public static void menuScreen3(Scanner s) {
		textIntro();
		String inputUsername= menuInputUsername(s);
		
		if(checkDuplicateUser(inputUsername) == false) {	
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
	/*Originally, I had created 7 other menuScreen functions to be used. However I soon released that these
	 * screens were only used once; unlike the screens above which often make references to themselves or other screens.
	 * This menu Screen handles the admin page of validating a users account. Using a switch case to handle all the different options
	 * an attempt to validate a user returns. */
	public static void menuScreen10(Scanner s) {
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
		
		int key = validateUser(valUsername, security);
		
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
		System.out.println("Please type |'val'| validate a user account");
		System.out.println("Please type |'exit'| to close the application.");
		System.out.println("=====================================================");
		System.out.println("You MUST exit The SleeperPupper Banking System sucessfully if you want to save ANY changes.");
		System.out.println("=====================================================");
		
		String key = s.next();
		
		switch (key) {
		case"val":
			menuScreen10(s);
			break;
		case "exit":
			exitApplication();
			break;
		default:
			System.err.println("Something must have gone really wrong...");
			break;
		}
	}
	//This is the page users see if they are able to log in successfully. They have the option to withdraw/deposit bits or exit.
	public static void menuScreenUser(String name, int bal, Scanner s) {
		
		System.out.println("=====================================================");
		System.out.println("Hello and welcome to the SleepyPupper Banking System.");
		System.out.println("=====================================================");
		System.out.println();
		System.out.println("=====================|" + name + "| LOGIN=====================");
		System.out.println("==============|Your balance is: " + bal + "|=====================");
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
			
			
			if (depositUserBits(deposit)) {
				bal = bal + deposit;
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
			
			
			if (withdrawUserBits(withdraw)) {
				bal = bal - withdraw;
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
			}else {System.err.println("Something must have gone really wrong...");}
			break;
		case "exit":
			exitApplication();
			break;

		default:
			System.err.println("Something must have gone really wrong...");
			break;
		}
	
	}
	
	//This function registers a new user. The user's object will be instanciated and added to userList.
	public static void menuRegister(String u, String p) {
		User U = new User(u, p, false, 0);
		addUserToList(U);
		
	}
	//This function uses a switch block to handle all the possible outcomes returned when a user's login attempt is being validated.
	public static void menuLogin(String u, String p, Scanner s){
		int key = checkLogin(u,p);
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
			menuScreenUser(u,getUserBits(), s);
			break;
		case 5:
			menuScreenAdmin(s);
			break;
		default:
			System.err.println("Something must have gone really wrong...");
			break;
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











}
