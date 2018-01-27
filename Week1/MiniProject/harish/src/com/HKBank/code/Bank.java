package com.HKBank.code;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class Bank {
	
	// Runs Bank console application
	public void run() throws IOException {
		Set<User> users = loadUserData();
		boolean session = true;
		while(session) {
			switch(homePage()) {
			case 1:
				//
				break;
			case 2:
				//
				for(User p: users) {
					System.out.println(p);
				}
				break;
			case 3:
				//New User Page
				users.add(newUser());		
				break;
			case 4:
				//Exit from bank
				System.out.println("Thank you for Banking with us");
				session = false;
				break;
			default:
				//Out of range in Menu
				System.out.println("Please select again\n");
			}
			saveUserData(users);
		}
		
	}
	
	//Load user data at start
	//Load User data from file
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
	
	
	//Home Page of Bank
	public int homePage() {
			//Welcome screen and Menu option
			int selection = 0;
			System.out.println("======= Welcome to HKBank ========");
			System.out.println(	"1.Admin login\n"+
								"2.User login\n"+
								"3.New User\n"+
								"4.Exit");
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
		
	//New user registration Page
	public User newUser() throws IOException {
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
	

