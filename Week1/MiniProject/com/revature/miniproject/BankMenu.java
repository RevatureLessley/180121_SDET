package com.revature.miniproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BankMenu {
	private Scanner scan = new Scanner(System.in);
	private boolean logout = false;
	private double amount;
	private String user, pw;
	private AdminAccount admin = new AdminAccount();
	private AccountFile f = new AccountFile();
	private boolean loggedIn = false;
	private BankAccount d;

	public BankMenu()
	{
	}

	public void loginMenu(int input){
		f.lookAtAccounts();
		System.out.println(f.getAccounts().size());
		f.printAccounts();
		if(isLoggedIn() == false)
		{
			switch(input){
			case 0:
				System.out.println("Quit");
				this.logout = true;
				f.saveAccount();
				setLoggedIn(false);
				break;
			case 1: 	
				System.out.println("Register Account\n");
				System.out.println("Create username:");
				this.user = scan.nextLine();
				while (!f.duplicateCheck(user))
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
				System.out.println("Account created. Please wait for admin to verify account");
				break;
			case 2:	
				System.out.println("Login Page\n" + "Enter username: ");
				this.user = scan.nextLine();
				System.out.println("Enter pw:");
				this.pw = this.scan.nextLine();
				if(f.checkLogin(user,pw) == true)
				{
					d = f.checkAccounts(user);
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
					System.out.println("Account " + d.getUser() + " successfully verified." );
				}
				else
					System.out.println("Error incorrect login.");
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
				System.out.println("Logging out....");
				this.logout = true;
				f.saveAccount();
				setLoggedIn(false);
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
