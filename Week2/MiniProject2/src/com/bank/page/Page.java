package com.bank.page;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bank.main.Driver;

public class Page {
	
	//logger
	final static Logger logger = Logger.getLogger(Driver.class);
	
	//User Input resource
	static Scanner i = new Scanner(System.in);
	

	public static int homePage() {
		//Welcome screen and Menu option
		int selection = 0;
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
			System.out.println("\n Enter numbers only \n");
		}
		return selection;
	}


	
	public static boolean exit() {
		// Exit Greet
		System.out.println("Thank you for Banking with us\n");
		logger.debug("User exit application");
		return false;
	}
	
	

}
