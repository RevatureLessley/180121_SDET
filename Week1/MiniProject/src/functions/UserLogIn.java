package functions;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import model.User;

public class UserLogIn {
	
	final static Logger logger = Logger.getLogger(UserLogIn.class);
	
	//takes username and verifies that user has been approved by administrator before allowing login
	public static void logInUsername(Scanner scan, String textfile) {
		
		List<User> users = SerializeAndDeserialize.deserializeUsers(textfile);
		List<String> usernames = FetchUsers.usernameList(textfile);
		User u = new User();
		
		String s = "";
		logger.debug("Size of users array: " + users.size());
		for(int i = 0; i < users.size();i++) {
			logger.debug("Array access count: " + i);
			System.out.println(users.get(i).getUserName());
		}
		
		while(true) {
			System.out.println("You are logging in. Enter your username or type \'q\'");
			s = scan.nextLine();
			logger.debug(s + " entered in login screen");
			if (s.equals("q")) {
				break;
			}
			if (usernames.contains(s)) {
				logger.debug("found match for " + s);
				for (int i = 0; i < users.size(); i++) {
					if (s.equals(users.get(i).getUserName())) {
						u =  users.get(i);
						if(!u.isApproved()) {
							logger.debug("User blocked at approval screen");
							System.out.println("Your account must be approved first!");
							break;
						}
						System.out.println("Your account has already been approved by adminstrator and you may change your bank balance");
						logInPassword(users, u, scan,textfile);
					}
				}
				break;
			}				
		}
	}
	
	//accepts username and verifies password
	public static void logInPassword(List<User> users, User u, Scanner scan, String textfile) {
		
		String password = u.getPassword();

		System.out.println("You are logging in. What is the password for " + u.getUserName() + "?");
		String s = scan.nextLine();
		while(true) {
			if (s.equals(password)) {
				System.out.println("You successfully logged in.");
				u.setBank_balance(changeAmount(users, u, scan, textfile));
				logger.debug("bank balance after update: " + u.getBank_balance());
				break;
			}
		
			if (s.equals("quit")) {
				break;
			}
			
			System.out.println("Try again or type \'quit\'");
			s = scan.nextLine();
		}			
		
	}
	
	//changes bank balance
	public static double changeAmount(List<User> users, User u, Scanner scan, String textfile) {
		logger.debug("bank balance before update: " + u.getBank_balance());
		System.out.print("Your current bank balance is: " + u.getBank_balance() + "\nSet a new amount: ");
		while(!scan.hasNextDouble()) {
			System.out.println("Enter a number!");
			scan.nextLine();
		}
		double d= scan.nextDouble();
		System.out.println("Your new bank balance is: " + d);
		u.setBank_balance(d);
		SerializeAndDeserialize.serializeUsers(users,textfile);
		return d;
				
	}
}
