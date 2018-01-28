package com.HKBank.code;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Bank {
	
	// Runs Bank console application
	public void run() throws IOException {
		
		//Load User data
		Set<User> users = loadUserData();
		
		boolean session = true;
		while(session) {
			switch(homePage()) {
			case 1:
				//Login Page for Administrator and User
				loginPage(users);
				System.in.read();
				break;
			case 2:
				//New User Page
				users.add(newUser());		
				break;
			case 3:
				//Exit from bank
				session = exit();
				break;
			default:
				//Out of range in Menu
				System.out.println("Please select again\n");
			}
		
		//Save user data
		saveUserData(users);
		}
		
	}
	
	// Exit greet
	public boolean exit() {
		System.out.println("Thank you for Banking with us\n");
		return false;
	}

	//User Account Page
	public void userPage(User p) {
		System.out.println("Welcome "+p.getUserName()+"\n"+
							"Your current Balance is "+p.getAmount()+"\n"+
							"=========================================\n"+
							"1.Deposit\n"+
							"2.Withdraw\n"+
							"========================================+\n");
		Scanner i = new Scanner(System.in);
		int action = 0;
		while(true) {
			try {
				System.out.print("Enter your selection: ");
				action= i.nextInt();
				System.out.println("Your selection is: "+action+"\n");
			}catch(InputMismatchException e) {
				e.printStackTrace();
				System.out.println("\n Enter numbers only \n");
			}
		    if(action == 1) {
		    	try {
					System.out.print("Enter your Amount: ");
					double amount= i.nextDouble();
					p.setAmount(p.getAmount()+amount);
					System.out.println(amount+" is deposited to your account\n"+
										"Your new balance is "+p.getAmount());
				}catch(InputMismatchException e) {
					e.printStackTrace();
					System.out.println("\n Enter numbers only \n");
				}
		    	break;
		    }
		    if(action == 2) {
		    	try {
					System.out.print("Enter your Amount: ");
					double amount= i.nextDouble();
					p.setAmount(p.getAmount()-amount);
					System.out.println(amount+" is withdrawn from your account\n"+
										"Your new balance is "+p.getAmount());
				}catch(InputMismatchException e) {
					e.printStackTrace();
					System.out.println("\n Enter numbers only \n");
				}
		    	break;
		    }
		}
		
		
		
	}

	//Administrator Page to Approve or Reject new accounts
	public void AdminPage(Set<User> users) throws IOException {
		System.out.println("Welcome Administrator\n");
		Iterator<User> it = users.iterator();
		while (it.hasNext()) {
		     User p = it.next();
			 if(p.getStatus().equals("notApproved")) {
				System.out.println("==================================");
				System.out.println(p.getUserName());
				System.out.println(	"1.Approve\n"+
									"2.Reject\n");
				System.out.println("==================================");
				Scanner i = new Scanner(System.in);
				int action = 0;
				while(true) {
					try {
						System.out.print("Enter your selection: ");
						action= i.nextInt();
						System.out.println("Your selection is: "+action+"\n");
					}catch(InputMismatchException e) {
						e.printStackTrace();
						System.out.println("\n Enter numbers only \n");
					}
				    if(action == 1) {
				    	p.setStatus("Approved");
				    	break;
				    }
				    if(action == 2) {
				    	it.remove();
				    	break;
				    }
				}
			}
		}
		System.out.println("No more new account waiting for Approval\n");
		System.in.read();

	}

	//Load User data at start of application
	public Set<User> loadUserData() throws IOException{
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("src/com/HKBank/files/usersData.ser"));
			return (HashSet<User>)ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(ois!=null){
				ois.close();
			}
		}
		return null;
	}
	
	//Home Page of the Bank
	public int homePage() {
			//Welcome screen and Menu option
			int selection = 0;
			System.out.println("======= Welcome to HKBank ========");
			System.out.println(	"1.Login\n"+
								"2.New User\n"+
								"3.Exit");
			System.out.println("==================================");
			System.out.print("Enter your selection: ");
			Scanner i = new Scanner(System.in);
			try {
				selection = i.nextInt();
				System.out.println("Your selection is: "+selection+"\n");
			}catch(InputMismatchException e) {
				e.printStackTrace();
				System.out.println("\n Enter numbers only \n");
			}
			return selection;
		}
	
	//Login Page for Administrator and User
	int loginPage(Set<User> users) throws IOException {
		Scanner i = new Scanner(System.in);
		System.out.print("Enter User Name: ");
		String userName = i.nextLine();
		System.out.print("Enter password: ");
		String password = i.nextLine();
		
		if(userName.equals("Admin")&&password.equals("Admin")) {
			AdminPage(users);
			return 0;
		}
		for (User p: users) {
			if(userName.equals(p.getUserName())&&password.equals(p.getPassword())){
				if((p.getStatus().equals("Approved"))) {
					userPage(p);
					return 0;
				}else {
					System.out.println("Sorry your account is not Approved yet\n");
					return 0;
				}
			}
		}
		System.out.println("Invaid login. Try again or create new account\n");		
		return 0;
	}
	
	//New user Registration Page
	public User newUser() throws IOException {
		System.out.println("Welcome New User\n");
		Scanner i = new Scanner(System.in);
		System.out.print("Enter User Name: ");
		String userName = i.nextLine();
		System.out.print("Enter password: ");
		String password = i.nextLine();
		System.out.println("Thank you registering with us.\n"+
							"Please wait until our Admin approves your account\n");
		System.in.read();
		return new User(userName, password, 0.0, "notApproved");
		}	
	
	//Save user data before ending application
	public void saveUserData(Set<User> save) throws IOException {
			ObjectOutputStream oos = null;
			try{
				oos = new ObjectOutputStream(new FileOutputStream("src/com/HKBank/files/usersData.ser"));
				oos.writeObject(save);
			}finally{
				if(oos!=null){
						oos.close();
				}
			}	
		}

}
	

