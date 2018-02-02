package functions;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import beans.User;
import controller.Controller;

public class AdminFunctions {
	
	final static Logger logger = Logger.getLogger(AdminFunctions.class);
	
	/**
	 * Screen for admin to login before allowing access
	 * to admin functions
	 * @param scan
	 */
	public static void adminLogin(Scanner scan) {
		//String s;
		System.out.println("What is the admin username?");
		if (scan.nextLine().equals("admin")) {
			logger.debug("admin entered username successfully");
			System.out.println("What is the password?");
			if(scan.nextLine().equals("password")) {
				logger.debug("admin entered password successfully");
				Controller.adminMenu(scan);
			}
			else {
				logger.debug("admin password was unsuccessful");
				Controller.mainMenu();
			}
		}
		else {
			logger.debug("admin username was incorrect");
			Controller.mainMenu();
		}
	}
	
	public static void authenticateUser(Scanner scan, String textfile, int index) {
		
		List<User> users = SerializeAndDeserialize.deserializeUsers(textfile);
		for(int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i).getUserName());
		}
		System.out.println("What user would you like to authenticate?");
		
		String input;
		if(index == -1) {
			input = scan.nextLine();
			logger.debug("admin authenticating " + input);			
		}
		else {
			input = users.get(index).getUserName();
		}

		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUserName().equals(input)) {
				logger.debug("match found for " + input + " and authenticated");
				users.get(i).setApproved(true);
				SerializeAndDeserialize.serializeUsers(users,textfile);
				System.out.println(users.get(i).getUserName() + " is now authorized");
				break;
			}
		}
	}
	
	public static void deleteUser(Scanner scan, String textfile, String username) {
		List<User> users = SerializeAndDeserialize.deserializeUsers(textfile);
		System.out.println("Here all the users");
		for (int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i).getUserName());
		}
		System.out.println("What user would you like to delete?");
		String input;
		if(username == null) {
			input = scan.nextLine();
			logger.debug("admin attempting to delete " + input);			
		}
		else {
			input = username;
		}

		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUserName().equals(input)) {
				users.remove(i);
				SerializeAndDeserialize.serializeUsers(users,textfile);
				logger.debug("admin deleted " + input);
				break;
			}
		
		}

	}
}
