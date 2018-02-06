package com.miniproject.users;

import java.util.Map;

import com.miniproject.services.AdminService;
import com.miniproject.services.UserService;
import com.miniproject.util.FileParser;
import com.miniproject.util.InputReader;

/*
 * Handles the creation of an account.  Making sure that the username and password are
 * valid.
 */
public class AccountCreate {
	private String username;
	private String password01;
	private String password02;
	//Code used to create a new admin account
	private String secretCode = FileParser.parseFile("src/configs", "SECRETCODE"); //get from file
	private Account newAccount;
	private boolean createAdmin = false;
	private Map<String, Account> accounts;
	
	public AccountCreate() {
		
	}
	
	public AccountCreate(Map<String, Account> accounts) {
		this.accounts = accounts;
	}
	
	public void setFirstPassword() {
		System.out.print("Please enter password at least 8 characters long: ");
		String userPass = InputReader.readString();
		while(userPass.length() < 8) {
			System.out.print("Please enter password at least 8 characters long: ");
			userPass = InputReader.readString();
		}
		this.password01 = userPass;
	}
	
	public void setSecondPassword() {
		System.out.println("Please enter the password again.");
		String userPass = InputReader.readString();
		this.password02 = userPass;
	}
	
	public String getPassword() {
		return this.password01;
	}
	
	public boolean passwordsMatch() {
		return this.password01.equals(this.password02);
	}
	
	public boolean secretPassword() {
		if(this.password01.equals(secretCode) && !createAdmin) {
			createAdmin = true;
			return true;
		} else {
			return false;
		}
	}
	
	public void setUsername() {
		System.out.print("Enter desired username: ");
		String userName = InputReader.readString();
		this.username = userName.toLowerCase();
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public Account getNewAccount() {
		return this.newAccount;
	}
	
	public boolean uniqueUsername() {
		return UserService.getUsername(this.username) == null && 
				AdminService.getUsername(this.username) == null;
	}
	
	public void accountSetUp() {
		setUsername();
		while(!uniqueUsername()) {
			System.out.println(this.username + " already exists, please try another.");
			setUsername();
		}
		setFirstPassword();
		setSecondPassword();
		while(!passwordsMatch()) {
			System.out.println("Passwords don't match please try again.");
			setFirstPassword();
			setSecondPassword();
		}
		
	}
	
	public void createAccount() {
		accountSetUp();
		if(secretPassword()) {
			System.out.println("Create an Admin Account");
			accountSetUp();
			//newAccount = new Admin(this.username, this.password01);
			AdminService.addAdmin(this.username, this.password01);
		} else {
			//newAccount = new User(this.username, this.password01);
			UserService.addUser(this.username, this.password01);
		}
	}
	
}
