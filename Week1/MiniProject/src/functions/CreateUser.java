package functions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import model.User;

//Contains all methods needed 
public class CreateUser {
	
	final static Logger logger = Logger.getLogger(CreateUser.class);
	
	/**
	 * Loads users from memory
	 * Called by controller to start creating a user
	 * Calls others methods to ensure unique id and username
	 * Saves all users to memory after operation
	 * @param scan
	 * @param textfile
	 */
	public static void createUserDialog(Scanner scan, String textfile) {
		
		List<User> users = SerializeAndDeserialize.deserializeUsers(textfile);
		//Initialize variables
		String input = "";
		double d;
		User u = new User();
		u.setId(uniqueId(u.getId(),textfile));
		
		u.setUserName(uniqueUsername(scan,textfile));
		
		System.out.print("Enter your password: \n");
		input = scan.nextLine();
		u.setPassword(input);
		logger.debug(input + " set as password for " + u.getUserName());

		System.out.println("How much money did you transfer from your old account? \n");
		while(!scan.hasNextDouble()) {
			System.out.println("Enter a number!");
			input = scan.nextLine();
			logger.debug(input + " rejected as bank balance");
		}
		d = scan.nextInt();
		u.setBank_balance(d);
		
		users.add(u);	
		System.out.println("Your account is pending administrator approval");
		SerializeAndDeserialize.serializeUsers(users,textfile);
	}
	
	/**
	 * Checks that int id generated is unique, 
	 * and if not increments by one until unique id is found
	 * @param id
	 * @param textfile
	 * @return
	 */
	public static int uniqueId(int id, String textfile) {
		List<Integer> ids = new ArrayList<Integer>();
		List<User> users = SerializeAndDeserialize.deserializeUsers(textfile);
		for (User u : users) {
			ids.add(u.getId());
		}
		if (ids.contains(id)) {
			logger.debug(id + " detected as duplicate");			
			boolean isUnique = false;
			while (isUnique == false) {
				id++;
				if (ids.contains(id) == false) {
					isUnique = true;
				}
			}			
		}		
		logger.debug("ID set: " + id);
		return id;					
	}
	
	/**
	 * Called by createUserDialog to ensure username created is unique
	 * and not admin or Admin
	 * @param scan
	 * @param textfile
	 * @return
	 */
	public static String uniqueUsername(Scanner scan, String textfile) {	
		List<String> usernames = FetchUsers.usernameList(textfile);
		String input = "";
		Boolean isUniqueUsername = false;
		System.out.print("You are creating a user. ");
		while(isUniqueUsername == false) {
			isUniqueUsername = true;
			System.out.print("Enter a new username: " + input);
			input = scan.nextLine();
			for (String username : usernames) {
				if (input.equals(username)) {
					logger.debug(input + " rejected as a duplicate");	
					isUniqueUsername = false;
					System.out.println("The username \'" + input + "\' is already taken");
				}
			}
			if (input.equals("admin") || input.equals("Admin")) {
				isUniqueUsername = false;
				System.out.println("Can't be admin");					
			}
		}
		return input;
	}
}
