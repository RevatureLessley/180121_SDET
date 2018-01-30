package com.revaturebank.ui;

import java.util.Scanner;

import com.revaturebank.systemclass.AdminList;
import com.revaturebank.systemclass.UserAccountList;
//this class Authentication user interface to access the main application
public class UILogin {
	public static UserAccountList objUserAccountList=UserAccountList.getUserAccountList(); 
    public static AdminList objAdminList = new AdminList();
	public static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		    String key;
		 // main user interface to login in the application     
		    //to test the application just enter username password to get access to the main menu users account and admins
		    String username,password;
		    System.out.println("######### Revature Bank Application ###########");
		    System.out.println("Please Select from the menu: ");
		    do {
		    
		    System.out.println("     -Enter 1 for user loggin");
		    System.out.println("     -Enter 2 to request an account ");
		    System.out.println("     -Enter 3 for Admin loggin.");
		    System.out.println("     -Or press 0 to exit");
		    
		    
		    	key=input.next();
		    switch (key) {
			case "1":
				System.out.println("Please Enter Your Username:");
			    username=input.next();
			    System.out.println("Please Enter Your Password:");
			    password=input.next();
			    try {
					userAuthenticate(username,password);
				} catch (Exception e) {
					e.printStackTrace();
			
				}
				break;
			case "2":
				userAccountRequest();
			case "0":
				try {
					objAdminList.clear();
					objUserAccountList.clear();
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
				System.out.println("___THANK YOU FOR VISITNG REVATURE BANK___");
				break;
			case "3":
				System.out.println("Please Enter Your Username:");
			    username=input.next();
			    System.out.println("Please Enter Your Password:");
			    password=input.next();
				try {
					adminAuthenticate(username,password);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				break;
			default:
				System.out.println(key +"this not an option. Please select: ");
				break;
			}
		    
		    }while (!key.equals("0"));
		    
	}
// admin authentication to enter the admin user interface
	private static void adminAuthenticate(String username,String password) throws Exception {
		
	    if (objAdminList.authenticate(username,password)!=null) {
            System.out.println("Access is Granted");
	        UIMainApp.adminAccount();
	           
	    }
        else
            System.out.println("Access Denied!!");
        
      }
		
	
//to request a new account for new users
	private static void userAccountRequest() {
		System.out.println("Account Request:");
		UIMainApp.requestAccount();
		
	}
  // to enter user account interface
	private static void userAuthenticate(String username, String password) throws Exception {
		if (objUserAccountList.authenticate(username,password)!=null) 
		{   if (objUserAccountList.authenticate(username,password).getAccountNumber()!="") {
			   System.out.println("Access is Granted");
		    UIMainApp.userAccount(objUserAccountList.authenticate(username,password));
		}else System.out.println("Sorry!! your account still not approved");
		System.out.println("Thank you for your visit");
		}  
            
        else {
            System.out.println("Assess Denied!!");
            System.out.println("username or password does not exist");
        }
	}

}