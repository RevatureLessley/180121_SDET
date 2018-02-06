package com.miniproject.users;

import org.apache.log4j.Logger;

import com.miniproject.beverages.BeverageBrand;
import com.miniproject.services.BeverageBrandService;
import com.miniproject.services.UserService;
import com.miniproject.util.InputReader;

/*
 * UserView
 * Contains the commands that the regular User can see when logged in
 */
public class UserView {
	final static Logger logger = Logger.getLogger(UserView.class);
	private User loggedUser;
	private final int EXITNUM = 4;
	private final int LOGINCOMBONUM = 3;
	private String notAppr = "Your account has either been banned or has not been approved yet.\n1) Log Out";
	private String title = "-----USERVIEW-----";
	private String logout = "-----USER LOGOUT-----";
	private String viewPrompt = "1) Current Balance \n2) Deposit Money \n3) Withdraw Money \n" + EXITNUM + ") LogOut";
	
	public UserView(User inLU) {
		this.loggedUser = inLU;
	}
	
	public void userSees() {
		if(loggedUser.isAccountApproved() && !loggedUser.isBanned()) {
			lastLogin();
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
		UserService.setCurrency(this.loggedUser.getUsername(), this.loggedUser.getCurrency().getCurrency());
		currentBalance();
	}
	
	private void withdraw() {
		currentBalance();
		String prompt = "How much would you like to withdraw?: ";
		float amount = InputReader.readFloat(prompt);
		loggedUser.withdrawCurrency(amount);
		UserService.setCurrency(this.loggedUser.getUsername(), this.loggedUser.getCurrency().getCurrency());
		currentBalance();
	}
	
	private void currentBalance() {
		System.out.printf("|BALANCE:  $%.2f|\n", loggedUser.getCurrency().getCurrency());
	}
	
	private void lastLogin() {
		this.loggedUser.setDaysLoggedIn(UserService.loginStreak(this.loggedUser.getUsername()));
		if(this.loggedUser.getDaysLoggedIn() != 0 && this.loggedUser.getDaysLoggedIn() % LOGINCOMBONUM == 0) {
			logger.info("Get Coupon"); //BUG can get mulitple coupons in the same day
			if(this.loggedUser.getBeverageId() == 0) {
				System.out.println("You get one coupon for one Starbucks coffee for use today");
			} else {
				BeverageBrand bb = BeverageBrandService.getBevBrand(this.loggedUser.getBeverageId());
				System.out.println("You get one coupon for one " + bb.getBrandName() + " " + bb.getBevType() + " drink!\n"
						+ "For use today!");
			}
		}
	}
	
	private void logOut() {
		this.loggedUser = null;
		System.out.println(logout);
	}
}
