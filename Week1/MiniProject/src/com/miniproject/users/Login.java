package com.miniproject.users;

import com.miniproject.util.InputReader;

public class Login {
	private UsersCollection uc;
	private String username;
	private String password;
	
	public Login(UsersCollection inUc) {
		uc = inUc;
	}
	
	public void loginUser() {
		enterUsername();
		enterPassword();
		Account u = checkLogin();
		while(u == null) {
			System.out.println("Username & Password pair does not exist. 'ENTER' to try Again."
					+ "\nOr type 'B' to go back to the previous menu");
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
	
	private void enterUsername() {
		System.out.print("Enter your username: ");
		this.username = InputReader.readString();
	}
	
	private void enterPassword() {
		System.out.print("Enter your password: ");
		this.password = InputReader.readString();
	}
	
	private Account checkLogin() {
		Account u = uc.getAccount(this.username);
		if(u != null) {
			if(!this.password.equals(u.getPassword())) {
				u = null;
			}
		}
		return u;
	}
}
