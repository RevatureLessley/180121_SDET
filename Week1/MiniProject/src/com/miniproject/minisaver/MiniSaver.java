package com.miniproject.minisaver;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.miniproject.users.AccountCreate;
import com.miniproject.users.Login;
import com.miniproject.users.UsersCollection;
import com.miniproject.util.InputReader;
import com.miniproject.util.Serializer;

/*
 * MiniSaver
 * contains the logic for picking which option to go through next
 * Also serializes and de-serializes our data collection class
 */
public class MiniSaver {
	final static Logger logger = Logger.getLogger(MiniSaver.class);
	
	UsersCollection users = null;
	Serializer sr = new Serializer();
	String usersFile = "src/users.ser";
	
	void runProgram(){
		try {
			//users = (UsersCollection)sr.deserialize("src/users.ser");
			users = (UsersCollection)sr.deserialize(usersFile);
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
				serializeObject(users, users.getClass(), usersFile);
				
			} else {
				System.out.println("INVALID CHOICE PLEAE TRY AGAIN");
			}
			System.out.println("=====MINISAVER=====");
			System.out.println(options);
			choice = InputReader.readInt(options);
		}
		
		if(choice == 3) {
			InputReader.closeReader();
			
			serializeObject(users, users.getClass(), usersFile);
			
			System.out.println("GOOD BYE!");
		} else {
			System.out.println("ABRUPT EXIT");
		}
	}
	
	private void serializeObject(Object o, Class c, String fileName) {
		try {
			sr.serialize(c.cast(o), fileName);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
