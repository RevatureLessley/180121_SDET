package com.revature.miniproject;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class Driver {
	public static void main(String[] args) 
	{
		
		BankMenu m = new BankMenu();
		Scanner input = new Scanner(System.in);
		int choice;
		boolean logout;
		
		m.printMenu();
		do	{		
				System.out.print("Enter Choice: ");
				choice = input.nextInt();
				m.loginMenu(choice);

				logout = m.isLogout();
			}
		while(logout != true); 
		System.out.println("You have logged out");

	}
}




