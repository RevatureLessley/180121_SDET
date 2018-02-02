package controller;

import java.util.Scanner;

import org.apache.log4j.Logger;

import dao.UserDao;
import dao.UserDaoImpl;
import functions.AdminFunctions;
import functions.CreateUser;
import functions.FetchUsers;
import functions.UserLogIn;

/*
 * Key features:
 * Users can login with password and change bank balance only after being approved by administrator
 * Data is persisted in a file
 * Prevents duplicate accounts from being created
 * Ensures each id is unique on creation
 * Admin cannot have banking account
 * Admin can login, authorize or delete users
 * 
 */
public class Controller {
	
	final static Logger logger = Logger.getLogger(Controller.class);
	
	private static final String USERS = "users.txt";
	
	public static void main(String[] args) {
		logger.debug("Application started");
		UserDaoImpl.insertUser("User3", "new password", new Double(65));
				
	}
	
	public static void mainMenu() {
		Scanner scan = new Scanner(System.in);
		while(true) {
			scan = new Scanner(System.in);
			System.out.println("Main menu");
			System.out.println("Type\n\'login\' to change bank balance");
			System.out.println("\'admin\' for admin login");
			System.out.println("\'create\' to create a new user");
			System.out.println("\'show\' to print all users");
			System.out.println("or \'q\'");
			String s = scan.nextLine();
			
			if (s.equals("Login") || s.equals("login")) {
				logger.debug("Chose login");
				UserLogIn.logInUsername(scan,USERS);
			}
			if (s.equals("admin") || s.equals("Admin")) {
				logger.debug("Chose admin");
				AdminFunctions.adminLogin(scan);
			}
			if (s.equals("create") || s.equals("Create")) {
				logger.debug("Chose create");
				CreateUser.createUserDialog(scan, USERS);
			}
			if (s.equals("Show") || s.equals("show")) {
				logger.debug("Chose show");
				FetchUsers.showAll(USERS);
			}
			if (s.equals("quit") || s.equals("q")) {
				logger.debug("Application closed");
				break;
			}
		}
		scan.close();		
	}
	
	public static void adminMenu(Scanner scan) {

		logger.debug("Accessed adminMenu");
		
		String s = "";
		while(true) {
			System.out.println("Admin main menu:");
			System.out.println("Type \'auth\' to authenticate User");
			System.out.println("Type \'delete\' to delete a User");
			System.out.println("or type \'q\'");
			s = scan.nextLine();		
			
			if (s.equals("authenticate") || s.equals("auth")) {
				logger.debug("accessing authenticate");
				AdminFunctions.authenticateUser(scan, USERS, -1);
			}
			
			if (s.equals("delete") || s.equals("Delete")) {
				logger.debug("accessing delete");
				AdminFunctions.deleteUser(scan, USERS, null);

			}
			
			if (s.equals("quit") || s.equals("q")) {
				logger.debug("quit admin menu");
				break;
			}
		}		
	}
	
}
