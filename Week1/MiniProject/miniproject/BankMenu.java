package com.revature.miniproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BankMenu {
	private BankAccount a;
	private Scanner scan = new Scanner(System.in);
	private boolean check = false, logout = false;
	private double amount;
	private String user, pw;
	private AdminAccount admin = new AdminAccount();
	private AccountFile f = new AccountFile();
	private static ArrayList<BankAccount> bankAccounts;
	private boolean loggedIn = false;
	private BankAccount d;

	public BankMenu(){
		//f.lookAtAccounts();
	}


	public void loginMenu(int input){
		
		System.out.println(f.getAccounts().size());
		f.printAccounts();
		if(isLoggedIn() == false)
		{
			switch(input){
			case 0:
				System.out.println("CASE 0 QUIT");
				this.logout = true;
				f.saveAccount();
				setLoggedIn(false);
				break;
			case 1: 	
				System.out.println("Register Account\n");
				System.out.println("Create username:");
				this.user = scan.nextLine();
				while (f.duplicateCheck(user))
				{
					System.out.println("Create username:");
					this.user = scan.nextLine();
				}
				System.out.println("Create pw");
				this.pw = scan.nextLine();
				d = new BankAccount(user,pw);
				d.setAmount(0);
				f.addAccounts(d);
				d.setApproval(false);
				System.out.println("Account create. Please wait for admin to verify account");
				break;
			case 2:	
				System.out.println("Login Page\n" + "Enter username: ");
				this.user = scan.nextLine();
				System.out.println("Enter pw:");
				this.pw = this.scan.nextLine();
			
				if(f.checkLogin(user,pw) == true)
				{
					d = f.checkAccounts(user);
					//System.out.println(d.isApproval());
					if(d.isApproved())
					{
					setLoggedIn(true);
					System.out.println("Login successful");
					}
					else
						System.out.println("ERROR. Account not approved");	
				}
				else
					System.out.println("ERROR. Account does not exist or password is wrong");
				break;
			case 6:
				System.out.println("Admin Login Page\n" + "Enter username: ");
				this.user = scan.nextLine();
				System.out.println("Enter password: ");
				this.pw = scan.nextLine();
				if (admin.checkAdmin(user,pw))
				{
					System.out.println("Admin Login Successful.\n" + "Enter username to verify: ");
					this.user = scan.nextLine();
					d = f.checkAccounts(user);
					admin.verifyAccount(d);
					
			
				}
				else
					System.out.println("ERORR INCORRECT ADMIN LOGIN");
				break;
				
			default:
				System.out.println("Login first or register account");
			}	

		}	
		else	
		{
			switch(input)
			{
		   case 3:
			   System.out.println("Withdrawing.....\n" + "Current Balance is"+ 
		   d.getAmount() + " dollars\n" + "Enter amount to withdraw: ");
				amount = this.scan.nextDouble();
				d.withdraw(amount);
				break;
			case 4:
				System.out.println("Depositing.....\n" + "Current Balance is"+ 
			 d.getAmount() + " dollars\n");
				System.out.println("Enter amount to deposit: ");
				amount = this.scan.nextDouble();
				d.deposit(amount);
				break;
			case 0:
				System.out.println("CASE 0 QUIT");
				this.logout = true;
				f.saveAccount();
				setLoggedIn(false);
				break;
			case 5:
				System.out.println("Enter username:");
				//user = scan.nextLine();
				System.out.println("Are you sure?");
				System.out.println(d.getUser());
				//a = checkAccounts(user);
				f.deleteAccount(d);
				//f.rewriteFile(d.getUser());
				break;
			default:
				System.out.println("INVALID INPUT");
			}
			}

	}
	
	public boolean isLogout() 
	{
		return logout;
	}
	public void setLogout(boolean logout) {
		this.logout = logout;
	}
	public void printMenu()
	{
		System.out.println("================");
		System.out.println("0: Logout/Exit\n" +"1: Register Account\n"+ "2: Login Account\n" + 
							"3: Withdrawl\n" + "4: Deposit\n" + "5: Delete Account\n" + "6: Admin Login");
		System.out.println("================");
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

}
