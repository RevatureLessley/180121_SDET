package com.miniproject.users;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.miniproject.services.UserService;
import com.miniproject.util.InputReader;
import com.miniproject.util.Serializer;

/*
 * The view for an Admin user and the commands they can run
 */
public class AdminView {
	final static Logger logger = Logger.getLogger(AdminView.class);
	private static final int EXITNUM = 6;
	private Admin authorizer;
	UsersCollection allUsers;
	Map<String, Account> accountsMap;
	List<User> users;
	private String viewPrompt = "1) View All Users\n2) Approve User \n3) "
			+ "Ban\\Un-Ban User \n4) Delete User \n5) Delete My Account \n" + EXITNUM + ") LogOut";
	private String viewFormat = "|%-15s|%-10s|%-7s|\n";
	private String logout = "+++++ADMIN LOGOUT+++++";
	
	public AdminView(Admin inAuth, UsersCollection inUsers) {
		this.authorizer = inAuth;
		this.allUsers = inUsers;
		this.accountsMap = this.allUsers.getRegAccounts();
		
	}
	
	void adminSees() {
		System.out.println("+++++ADMINVIEW+++++\n" + viewPrompt);
		int response = InputReader.readInt(viewPrompt);
		while(response != EXITNUM && this.authorizer != null) {
			if(response == 1) {
				viewUsers();
			} else if(response == 2) {
				approveUser();
			} else if(response == 3) {
				banning();
			} else if(response == 4) {
				deleteUser();
			} else if(response == 5) {
				deleteAccount();
			}
			
			if(response != EXITNUM && this.authorizer != null) {
				System.out.println(viewPrompt);
				response = InputReader.readInt(viewPrompt);
			}	
		}
		logOut();
	}
	
	private void viewUsers() {
		users = UserService.getUsersForAdminForAdmin();
		System.out.printf(viewFormat, "Username", "Approved", "Banned");
		for(User u : users) {
			System.out.printf(viewFormat, u.getUsername(),
					u.isAccountApproved() ? "Yes" : "No",
					u.isBanned() ? "Yes" : "No");
		}
		System.out.println("\n");
	}
	
	private void approveUser() {
		viewUsers();
		System.out.print("Type name of user you want to approved: ");
		String appUser = InputReader.readString();
		String u = UserService.getUsername(appUser);
		if(u != null) {
			UserService.approveUser(u);
		} else {
			System.out.println("Username does not exist.");
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
	
	private void deleteUser() {
		viewUsers();
		System.out.println("Enter name of user to Delete");
		String name = InputReader.readString();
		if(accountsMap.get(name) != null && !accountsMap.get(name).getIsAdmin()) {
			accountsMap.remove(name);
			Serializer s = new Serializer();
			try {
				s.serialize(this.allUsers, "src/users.ser");
			} catch(IOException e) {
				e.printStackTrace();
			}	
		} else {
			System.out.println("Cannot delete admin or non-existent user.");
		}
	}
	
	private void deleteAccount() {
		System.out.print("Are you sure you want to delete your account? (Y/N): ");
		String response = InputReader.readString();
		if(response.equals("Y")) {
			accountsMap.remove(authorizer.getUsername());
			Serializer s = new Serializer();
			try {
				s.serialize(this.allUsers, "src/users.ser");
			} catch(IOException e) {
				e.printStackTrace();
			}
			logOut();
		}
		
	}
	
	private void logOut() {
		this.authorizer = null;
		System.out.println(logout);
	}
}
