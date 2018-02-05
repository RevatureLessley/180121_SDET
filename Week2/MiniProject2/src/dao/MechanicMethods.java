package dao;

import java.util.Scanner;

import org.apache.log4j.Logger;

import controller.Controller;

public class MechanicMethods {
	final static Logger logger = Logger.getLogger(MechanicMethods.class);
	
	public static void mechanicLogin(Scanner scan) {
		//String s;
		System.out.println("What is the mechanic username?");
		if (scan.nextLine().equals("mechanic")) {
			logger.debug("mechanic entered username successfully");
			System.out.println("What is the password?");
			if(scan.nextLine().equals("mechanic")) {
				logger.debug("mechanic entered password successfully");
				Controller.mechanicMenu(scan);
			}
			else {
				logger.debug("mechanic password was unsuccessful");
				Controller.mainMenu();
			}
		}
		else {
			logger.debug("mechanic username was incorrect");
			Controller.mainMenu();
		}
	}
}
