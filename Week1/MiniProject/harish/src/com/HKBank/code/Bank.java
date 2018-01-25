package com.HKBank.code;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Bank {
	
	// runs Bank console application
	public void run() {
		
		Scanner i = new Scanner(System.in);
		boolean session = true;
		while(session) {
			
			System.out.println("======= Welcome to HKBank ========\n");
			System.out.println("1.Admin login\n"+
								"2.User login\n"+
								"3.New User\n");
			System.out.println("==================================\n");
			System.out.println("Enter Selection: ");
			
			try {
				int selection = i.nextInt();
				System.out.println("Your selection is: "+selection+"\n");
			}catch(InputMismatchException e) {
				e.printStackTrace();
				System.out.println("\n Enter numbers only \n");
			}
			
			
			
		}
		
	}

}
