package com.revature.miniproject;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class Driver {
	final static Logger logger = Logger.getLogger(Driver.class);
	public static void main(String[] args) 
	{
		
		BankMenu m = new BankMenu();
		Scanner input = new Scanner(System.in);
		int choice;
		boolean logout;
		Driver d = new Driver();
		d.logStuff("Test log");
		m.printMenu();
		do	{		
				System.out.print("Enter Choice: ");
				choice = input.nextInt();
				m.loginMenu(choice);

				logout = m.isLogout();
			}
		while(logout != true); 
		System.out.println("You have logged out");

	}
	public void logStuff(String message)
	{
		logger.trace(message);
		logger.debug(message);
		logger.info(message);
		logger.warn(message);
		logger.error(message);
		logger.fatal(message);
	}
}




