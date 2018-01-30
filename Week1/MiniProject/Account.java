package com.JawaunBank;
import java.util.*;
import java.io.*;

public class Account extends Users {
	
	protected double balance; //the money in the account
	protected boolean approved = false; 	//is account funded and approved?
	protected static int numOfAccounts;

	protected void deposit(double amount){
		this.balance+=amount; // adds the deposit amount to your balance
		double result = this.getBalance();
		System.out.println("your balance is" + result);
   }
	
    protected void withdraw(double amount){
    		double b = this.getBalance();
	    	if(sufficientFunds(b, amount) == true){
	    		this.balance-=amount; //takes out the withdrawal amount
	    		double result = this.getBalance();
	    		System.out.println("your balance is" + result); 
	    	 }else {
	    		 System.out.println("deposit more to take out that much!");
	    	 }
   }
    protected boolean sufficientFunds(double balance, double amount) {
	    	boolean cando = false;
	    	if ((balance-amount)<0.0){
	    		 System.out.println("your balance is insufficient for this transaction");
	    	} else {
	    		cando = true;
	    	}
	    	return cando;
    }
    protected double getBalance(){
     System.out.println("your balance is" + balance);
     return balance; //displays user balance
   }
	protected boolean isApproved() {
		return approved; //lets you know if a user is approved
	}
	protected void menu() {
		System.out.println("Welcome to the main menu!");
		System.out.println("If you are not yet approved, please add funds now");
		System.out.println("Is your account approved?" + isApproved());//show whether approved
		System.out.println("Please enter 1 to view your balance, 2 to make a deposit or 3 to withdraw money");
		int decision = ino.nextInt(); 
		if ((decision>0) && (decision<4)) { //check if the next value is within range
			doStuff(decision); //show the options
		} else {
			menu(); //restart menu if there isn't an acceptable value
		}
		System.out.println("Would you like to do more?");
		System.out.println("Press 1 to do more or 2 to sign out");
		int nextval = ino.nextInt(); //the next operation you want to do
		doMore(nextval); //pass the most recent int to doMore
		ino.close();
	}
	
	protected void doStuff(int i) {
		switch(i) {
			case 1:
				this.getBalance();
				break;
			case 2:
				System.out.println("Please enter an amount to deposit");
				double amt = ino.nextDouble();
				this.deposit(amt);
				break;
			case 3:
				System.out.println("Please enter an amount to withdraw");
				double amont = ino.nextDouble();
				this.withdraw(amont);
				break;	
		}
		System.out.println("Thanks for banking with J&B Bank!!");
	}
		
	protected void doMore(int nextop) {
			System.out.println("Would you like to do more?"); 
			System.out.println("Press 1 to do more or 2 to sign out");
			if ((nextop != 1) && (nextop!=2)) { //if the number is not one of the two values
				int nexty = ino.nextInt(); //the next operation you want to do
				//take back to menu
				doMore(nexty);//go back to the start of doMore
			} else { //if it is the right size value
				if(nextop == 1) {
					System.out.println("Please enter 1 to view your balance, 2 to make a deposit or 3 to withdraw money");
					int dec = ino.nextInt();
					doStuff(dec); //do more stuff if value is one
				} else if(nextop == 2) {
					ino.close();
					//exit if value is two
					//save changes
				}
			}
		}
	
	
}

