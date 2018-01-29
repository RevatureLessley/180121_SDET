package com.miniproject.users;

import com.miniproject.util.InputReader;

public class UserView {
	private User loggedUser;
	private final int EXITNUM = 4;
	private String notAppr = "Your account has either been banned or has not been approved yet.\n1) Log Out";
	private String title = "-----USERVIEW-----";
	private String logout = "-----USER LOGOUT-----";
	private String viewPrompt = "1) Current Balance \n2) Deposit Money \n3) Withdraw Money\n" + EXITNUM + ") Exit";
	
	public UserView(User inLU) {
		this.loggedUser = inLU;
	}
	
	public void userSees() {
		if(loggedUser.isAccountApproved() && !loggedUser.isBanned()) {
			System.out.println(title + '\n' + viewPrompt);
			int response = InputReader.readInt(viewPrompt);
			while(response != EXITNUM) {
				if(response == 1) {
					currentBalance();
				} else if(response == 2) {
					deposit();
				} else if(response == 3) {
					withdraw();
				}
				System.out.println(viewPrompt);
				response = InputReader.readInt(viewPrompt);
			}
			logOut();
		} else {
			System.out.println(notAppr);
			int response = InputReader.readInt(notAppr);
			while(response != 1) {
				System.out.println(notAppr);
				response = InputReader.readInt(notAppr);
			}
			logOut();
		}
	}
	
	private void deposit() {
		String prompt = "How much would you like to deposit?: ";
		float amount = InputReader.readFloat(prompt);
		loggedUser.depositCurrency(amount);
		currentBalance();
	}
	
	private void withdraw() {
		currentBalance();
		String prompt = "How much would you like to withdraw?: ";
		float amount = InputReader.readFloat(prompt);
		loggedUser.withdrawCurrency(amount);
		currentBalance();
	}
	
	private void currentBalance() {
		System.out.printf("|BALANCE:  $%.2f|\n", loggedUser.getCurrency().getCurrency());
	}
	
	private void logOut() {
		this.loggedUser = null;
		System.out.println(logout);
	}
}
