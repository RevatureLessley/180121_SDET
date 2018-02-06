package com.revaturebank.ui;

import java.sql.SQLException;

import com.revaturebank.systemclass.Admin;
import com.revaturebank.systemclass.UserAccount;

/* user interface after the authentication is granted.
 * this class is final 
 * all its methods are statics and its constructor private preventing the instantiation the class.
 */
public final class UIMainApp {
	
	public static void userAccount(UserAccount userAccount) {
		
		System.out.println("---------------Welcome <<<<"+ userAccount.getFirsName()+">>> to your account----------");
		System.out.println("---            Please take an action:");
		String key;
		do {
		
		System.out.println("---------->>-------------->>-------------------------------\n"+
				"1. Check your balance\n" +
                "2. Make a deposit\n" +
                "3. Withdraw cash \n" +
                "4. pay your credit card\n " +
                "5. Review your transactions \n"+
                "6. Change username or password \n"+
                "0. logout   ->\n");
		key = UILogin.input.next();
		switch (key) {
		case "0":
			System.out.println("--------------------------Back to login interface---------------------");
			break;
		case "1":
			System.out.println("Your balance is:   $" + userAccount.getBalanceAccount());
			break;
		case "2":
			System.out.println("please enter your deposit");
			userAccount.deposit(UILogin.input.nextDouble());
			break;
		case "3":
			System.out.println("please enter amount to withdaw:");
			userAccount.withdraw(UILogin.input.nextDouble());
			break;
		case "4":
			System.out.println("Please enter the payment amount:");
			userAccount.payCreditCard(UILogin.input.nextDouble());
			break;
		case "5":
			System.out.println("______________________________________________________");
			UILogin.ual.transactions(userAccount.getAccountNumber());
			System.out.println("___________________________end___________________________");
			break;
		case "6":
			System.out.println("----------------");
			editUP(userAccount.getAccountNumber());
			System.out.println("-----------------");
		default:
			System.out.println(key +"  is not an option. Please select from the list: ");
			break;
		} 
		}while(!key.equals("0"));
		
	}

	private static void editUP(String u) {
		System.out.println("Enter username:");
		String username=UILogin.input.next();
		System.out.println("please enter your password");
		String password=UILogin.input.next();
		if (UILogin.ual.edit(u,username, password))
			System.out.println("---All set---");
		
	}

	public static void requestAccount() {
		
		System.out.println("<<<Welcome to ravetureBank to open an account please provide with the following information:>>\n");
		System.out.println("Enter username:");
		String username=UILogin.input.next();
		System.out.println("please enter your password");
		String password=UILogin.input.next();
		System.out.println("please enter your first name:");
		String fName=UILogin.input.next();
		System.out.println("please enter your last name:");
		String lName=UILogin.input.next();
		System.out.println("please enter your email:");
			String email=UILogin.input.next();
		System.out.println("please enter your phone number:");
			String phone=UILogin.input.next();
		System.out.println("please enter your ssn:");
		String ssn =UILogin.input.next();
		try {
		boolean bool;
		do { bool=true;
		if (!UILogin.ual.search(username)) {
			UILogin.ual.add(ssn, fName, lName, phone, email, username, password);
			System.out.println("Your request is taken in consideration Please wait for account approval");
		    System.out.println("-------------------------------Back to login interface---------------------");	
		     bool=false;
		    }
		else
			{System.out.println("  denied!! Username Exist!!. Enter a different USERNAME or enter -1 to dismiss");
			System.out.println("Enter username:");
			username=UILogin.input.next();
			}
			
			}while (bool && !username.equals("-1"));
		
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
//Admin account is limited to activate or remove an account or add another admin to the system
	public static void adminAccount(Admin ad) {
		System.out.println("----------Welcome<<<< "+ ad.getFirsName()+" >>>>>t your admin accoount----------");
		System.out.println("---Take an action:");
		String key;
		do { 
		System.out.println(                
                "     1. List  all account requests \n" +
                "     2. Grant user account \n" +                
                "     3. to remove an user account\n"+
                "     4. Add an admin to the system\n" +
                "     0. Exit & Save ");
		key = UILogin.input.next();
		switch (key) {
		case "0":
			System.out.println("--------------------------Back to login interface------------------------");
			break;
		case "1":
			System.out.println("-----------------Account requsets--------------:");
			UILogin.adl.AllAccountRequest();
			System.out.println("-----------------Account requsets--------------:");
			break;
		case "2":
			System.out.println("Enter username to activate an account:");
			String username=UILogin.input.next();
			UILogin.adl.activateAccount(username);
			break;
		case "3":
			System.out.println("enter username to remove from the account");
			username =UILogin.input.next();
			System.out.println("Enter account Numbe");
			String accountNumber=UILogin.input.next();
			UILogin.adl.remove(accountNumber,username);
				
			break;
		case "4":
			
			break;
		default:
			System.out.println(key +"  is not an option. Please select: ");
			break;
		} 
		}while(!key.equals("0"));
	}
   
    

}
