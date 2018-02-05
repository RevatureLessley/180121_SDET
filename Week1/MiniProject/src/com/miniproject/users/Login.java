package com.miniproject.users;

import com.miniproject.services.AdminService;
import com.miniproject.services.UserService;
import com.miniproject.util.InputReader;

/*
 * Handles logging in a user.  Checking for correct password and type of user
 */
public class Login {
	private UsersCollection uc;
	private String username;
	private String password;
	private boolean admin;
	
	public Login(UsersCollection inUc) {
		uc = inUc;
	}
	
	public void loginUser() {
		checkAdmin();
		enterUsername();
		enterPassword();
		Account u = checkLogin();
		while(u == null) {
			System.out.println("Username & Password pair does not exist. 'A' to try (A)gain."
					+ "\nOr type 'B' to go (B)ack to the previous menu");
			String response = InputReader.readString();
			if(response.equals("B")){
				break;
			}
			enterUsername();
			enterPassword();
			u = checkLogin();
		}
		if(u != null) {
			if(u.getIsAdmin()) {
				AdminView aV = new AdminView((Admin)u, uc);
				aV.adminSees();
			} else {
				User loggedUser = (User)u;
				UserView uV = new UserView(loggedUser);
				uV.userSees();
			}
			
		}
	}
	
	private void checkAdmin() {
		System.out.print("Login as (A)dmin or (U)ser?: ");
		String response = InputReader.readString();
		response = response.toUpperCase();
		while(!response.equals("A") && !response.equals("U")) {
			System.out.print("Please enter (A) or (U): ");
			response = InputReader.readString();
			response = response.toUpperCase();
		}
		
		if(response.equals("A")) {
			admin = true;
		} else if(response.equals("U")){
			admin = false;
		}	
	}
	
	private void enterUsername() {
		System.out.print("Enter your username: ");
		this.username = InputReader.readString().toLowerCase();
	}
	
	private void enterPassword() {
		System.out.print("Enter your password: ");
		this.password = InputReader.readString();
	}
	
	private Account checkLogin() {
		Account a = null;
		if(admin) {
			a = AdminService.getAdmin(this.username, this.password);
		} else {
			a = UserService.getUser(this.username, this.password);
		}
		return a;
	}
}
