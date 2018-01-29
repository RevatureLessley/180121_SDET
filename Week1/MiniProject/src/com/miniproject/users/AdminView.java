package com.miniproject.users;

import java.util.Map;

import javax.print.DocFlavor.INPUT_STREAM;

import org.apache.log4j.Logger;
import com.miniproject.util.InputReader;

public class AdminView {
	final static Logger logger = Logger.getLogger(AdminView.class);
	private static final int EXITNUM = 5;
	private Admin authorizer;
	UsersCollection allUsers;
	Map<String, Account> accountsMap;
	private String viewPrompt = "1) View All Users\n2) Approve User \n3) Ban\\Un-Ban User \n" + EXITNUM + ") Exit";
	private String viewFormat = "|%-15s|%-7s|%-10s|%-7s|\n";
	private String logout = "+++++ADMIN LOGOUT+++++";
	public AdminView(Admin inAuth, UsersCollection inUsers) {
		this.authorizer = inAuth;
		this.allUsers = inUsers;
		this.accountsMap = this.allUsers.getRegAccounts();
		
	}
	
	void adminSees() {
		System.out.println("+++++ADMINVIEW+++++\n" + viewPrompt);
		int response = InputReader.readInt(viewPrompt);
		while(response != EXITNUM) {
			if(response == 1) {
				viewUsers();
			} else if(response == 2) {
				approveUser();
			} else if(response == 3) {
				banning();
			}
			System.out.println(viewPrompt);
			response = InputReader.readInt(viewPrompt);
		}
		logOut();
	}
	
	private void viewUsers() {
		System.out.printf(viewFormat, "Username", "Type", "Approved", "Banned");
		for(String s : accountsMap.keySet()) {
			System.out.printf(viewFormat, accountsMap.get(s), accountsMap.get(s).getIsAdmin() ? "Admin" : "User", 
					accountsMap.get(s).isAccountApproved() ? "Yes" : "No",
					accountsMap.get(s).isBanned() ? "Yes" : "No");
		}
		System.out.println("\n");
	}
	
	private void approveUser() {
		viewUsers();
		System.out.print("Type name of user you want to approved: ");
		String appUser = InputReader.readString();
		if(accountsMap.get(appUser) != null) {
			if(!accountsMap.get(appUser).getIsAdmin()) {
				accountsMap.get(appUser).setAccountApproved(true);
			} else {
				System.out.println("You can't approve an Admin");
			}
			
		}
		viewUsers();
	}
	
	private void banning() {
		viewUsers();
		System.out.print("Enter the name of the user to ban/un-ban: ");
		String bUser = InputReader.readString();
		if(accountsMap.get(bUser) != null) {
			if(!accountsMap.get(bUser).getIsAdmin()) {
				String prompt = "Would you like to 1) Ban or 2) Un-Ban: ";
				System.out.println(prompt);
				int response = InputReader.readInt(prompt);
				switch(response) {
				case 1:
					accountsMap.get(bUser).setBanned(true);
					break;
				case 2:
					accountsMap.get(bUser).setBanned(false);
					break;
				default:
					System.out.println("Option does not exist.");
				}
			} else {
				System.out.println("Cannot ban/un-ban an Admin");
			}
		} else {
			System.out.println("Account does not exist");
		}
		
	}
	
	private void logOut() {
		authorizer = null;
		System.out.println(logout);
	}
}
