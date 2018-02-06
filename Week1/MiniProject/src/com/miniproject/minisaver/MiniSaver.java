package com.miniproject.minisaver;


import org.apache.log4j.Logger;

import com.miniproject.users.AccountCreate;
import com.miniproject.users.Login;
import com.miniproject.util.InputReader;

/*
 * MiniSaver
 * contains the logic for picking which option to go through next
 * Also serializes and de-serializes our data collection class
 */
public class MiniSaver {
	final static Logger logger = Logger.getLogger(MiniSaver.class);
	
	void runProgram(){
		
		System.out.println("=====Hello! Welcome to MazeSaver!=====");
		String options = "1) Login\n2) Create Account\n3) Exit";
		System.out.println(options);
		
		int choice = InputReader.readInt(options);
		
		while(choice != 3) {
			if(choice == 1) {
				System.out.println("LOGIN!");
				Login login = new Login();
				login.loginUser();
			} else if(choice == 2) {
			
				System.out.println("CREATE!");
				AccountCreate createAccount = new AccountCreate();
				createAccount.createAccount();
				
			} else {
				System.out.println("INVALID CHOICE PLEAE TRY AGAIN");
			}
			System.out.println("=====MINISAVER=====");
			System.out.println(options);
			choice = InputReader.readInt(options);
		}
		
		if(choice == 3) {
			InputReader.closeReader();
			
			System.out.println("GOOD BYE!");
		} else {
			System.out.println("ABRUPT EXIT");
		}
	}
}
