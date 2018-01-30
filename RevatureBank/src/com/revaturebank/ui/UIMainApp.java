package com.revaturebank.ui;

import java.util.HashMap;
import java.util.Random;

import com.revaturebank.systemclass.Admin;
import com.revaturebank.systemclass.UserAccount;

/* user interface after the authentication is granted.
 * this class is final 
 * all its methods are statics and its constructor private preventing the instantiation the class.
 */
public final class UIMainApp {
	
	public static void userAccount(UserAccount userAccount) {
		
		System.out.println("----------Welcome to your account----------");
		System.out.println("---Take an action:");
		String key;
		do {
		
		System.out.println("1. Check your balance\n" +
                "2. Make a deposit\n" +
                "3. Withdraw cash \n" +
                "0. Save & logout");
		key = UILogin.input.next();
		switch (key) {
		case "0":
			System.out.println("Back to login interface");
			break;
		case "1":
			System.out.println("Your balance is: " + userAccount.getBalanceAccount());
			break;
		case "2":
			System.out.println("please enter your deposit");
			userAccount.deposit(UILogin.input.nextDouble());
			break;
		case "3":
			System.out.println("please enter amount to withdaw:");
			userAccount.withdraw(UILogin.input.nextDouble());
			break;
		default:
			System.out.println(key +"this is not an option. Please select: ");
			break;
		} 
		}while(!key.equals("0"));
		
	}

	public static void requestAccount() {
		
		System.out.println("Welcome to ravetureBank to open an account please provide with the following information:");
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
		if (UILogin.objUserAccountList.add(ssn, fName, lName, phone, email, username, password, 0.0)) {
			System.out.println("Your request is taken in consideration Please wait for account approval");
		    System.out.println("Back to login interface");	}
		else
			do {
			System.out.println("denied!!!!. Enter a different USERNAME or enter -1 to dismiss");
			System.out.println("Enter username:");
			username=UILogin.input.next();
			
			}while((UILogin.objUserAccountList.add(ssn, fName, lName, phone, email, username, password, 0.0))||username.equals("-1"));
		
		try {
			UILogin.objUserAccountList.dataSave();
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}
//admin account is limited to activate or remove an account or add another admin to the system
	public static void adminAccount() {
		System.out.println("----------Welcome to ----------");
		System.out.println("---Take an action:");
		String key;
		do { 
		System.out.println(                
                "1. List  all the accounts\n" +
                "2. activate or disactivate an account \n" +
                "3. to remove an account\n"+
                "4. Add an admin to the system\n" +
                "0. Exit & Save ");
		key = UILogin.input.next();
		switch (key) {
		case "0":
			System.out.println("Back to login interface");
			break;
		case "1":
		    allAccounts();
			break;
		case "2":
			System.out.println("Enter username to activate an account:");
			String username=UILogin.input.next();
			activateAccount(username,true);
			break;
		case "3":
			System.out.println("enter username to remove from the account");
			username =UILogin.input.next();
			try {
				UILogin.objUserAccountList.remove(username);
			} catch (Exception e) {
				
				e.printStackTrace();
			}	
			break;
		case "4":
			addAdmin();
			break;
		default:
			System.out.println(key +" that is not an option. Please select: ");
			break;
		} 
		}while(!key.equals("0"));
	}
    //to add an Admin
	private static void addAdmin() {
		System.out.println("To add an admin to the system please enter information:");
		System.out.println("Enter Admin ID");
		String admin =UILogin.input.next();
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
		Admin ad=new Admin(ssn, fName, lName, phone, email,admin, username, password);
		if (UILogin.objAdminList.add(ad))
			System.out.println("Adnim is added");
		else
			do {
			System.out.println("denied!!!!. Enter a different USERNAME or enter -1 to dismiss");
			System.out.println("Enter username:");
			username=UILogin.input.next();
			ad.setUsername(username);
			}while((UILogin.objAdminList.add(ad))||username.equals("-1"));

		
	}
    //to view all the users account
	private static void allAccounts() {
		HashMap<String,UserAccount> m=UILogin.objUserAccountList.getUsers();
				m.entrySet().stream().forEach((user) -> {
                  System.out.println((m.get(user.getKey())).getSsNumber()+"	| " +m.get(user.getKey()).getFirsName()+
                		  "	| " +  m.get(user.getKey()).getLastName()+"	| "+m.get(user.getKey()).getEmail()+
                		  "	| "+  m.get(user.getKey()).getPhone()   +"	| "+ m.get(user.getKey()).getAccountNumber() +
                		  "	| " +  m.get(user.getKey()).getUsername()+"	| " + m.get(user.getKey()).getPassword() +
                		  "	| " +  m.get(user.getKey()).getBalanceAccount()+" | "+m.get(user.getKey()).isActrive());
        });
	
	}
    //to approve an account by making it active and associated with account number
	private static void activateAccount(String username,boolean bool) {
		Random rand=new Random();
        int ua=rand.nextInt(10000)+10000;
        String accountNumber="12"+ua;
	    if (UILogin.objUserAccountList.edit(username,accountNumber, bool))
	       	System.out.println("account activated");
	    else 
	    	System.out.println("account still inactive");
		
	}

}
