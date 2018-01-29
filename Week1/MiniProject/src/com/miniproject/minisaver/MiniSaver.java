package com.miniproject.minisaver;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.miniproject.users.AccountCreate;
import com.miniproject.users.Login;
import com.miniproject.users.UsersCollection;
import com.miniproject.util.InputReader;
import com.miniproject.util.Serializer;

public class MiniSaver {
	final static Logger logger = Logger.getLogger(MiniSaver.class);
	
	UsersCollection users = null;
	Serializer sr = new Serializer();
	
	void runProgram(){
		try {
			users = (UsersCollection)sr.deserialize("src/users.ser");
			logger.debug("deserialized");
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(users == null) {
				users = new UsersCollection();
			}
		}
		
		System.out.println("=====Hello! Welcome to MazeSaver!=====");
		String options = "1) Login\n2) Create Account\n3) Exit";
		System.out.println(options);
		
		int choice = InputReader.readInt(options);
		
		while(choice != 3) {
			if(choice == 1) {
				System.out.println("LOGIN!");
				Login login = new Login(users);
				login.loginUser();
			} else if(choice == 2) {
			
				System.out.println("CREATE!");
				AccountCreate createAccount = new AccountCreate(users.getRegAccounts());
				createAccount.createAccount();
				users.createUser(createAccount.getNewAccount());
				try {
					sr.serialize(users, "src/users.ser");
				} catch(IOException e) {
					e.printStackTrace();
					break;
				}
				
			} else {
				System.out.println("INVALID CHOICE PLEAE TRY AGAIN");
			}
			System.out.println(options);
			choice = InputReader.readInt(options);
		}
		
		if(choice == 3) {
			InputReader.closeReader();
			
			try {
				sr.serialize(users, "src/users.ser");
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			System.out.println("GOOD BYE!");
		} else {
			System.out.println("ABRUPT EXIT");
		}
	}
}
