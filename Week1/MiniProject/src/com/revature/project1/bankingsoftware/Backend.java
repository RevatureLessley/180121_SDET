package com.revature.project1.bankingsoftware;

import java.awt.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

/* * Backend is my main class for the SleepyPupper Banking system. This class handles validating user's password
 * as well as determining if a admin is trying to login. Backend also saves new users. Finally it handles a check
 * to eliminate users having the same username, handles the banking functions of withdrawing for users, and provides
 * the function for the admin to validate users from their menu. 
 * This class is abstract as their doesnt need to be an instance of this class, but MainMenu extends it,
 * to have all access to all its functions*/
public abstract class  Backend {
	/* * ADMIN_USERNAME and ADMIN_PASSWORD are two variables that are used to validate an Admin logging in.
	 * It is a private final static string as it should never be overwritten, accessed outside of this class,
	 * and their should only ever be one instance of either variable. */
	private static final String ADMIN_USERNAME = "Admin";
	private static final String ADMIN_PASSWORD = "SecurePassword1";
	 //perData is a variable that stores the name/location of the file that saves user data.
	private static String perData = "registry"; //File name where all user data will be stored
	//userList is used to say the list of users. It is loaded at start up, and laoded back into the file at close.
	
	private static ArrayList<User> userList = new ArrayList<>(); 
	//This is a simple function that adds a user to the array list when a new user registers for an account.
	protected static void addUserToList(User a) {userList.add(a);}
	/* *This variable is used to to store the last index of a search through the arraylist.
	 *When a user tries to log in, the array list is traversed, searching for a match for the username,
	 *followed by a check for the correct password, and if the user has been validated by an admin.
	 *If all these checks are true, then a user is allowed to login. However, rather than searching again for
	 *the user in the userList arraylist when the user commits a withdrawl and deposit, the index of the user's postion
	 *in the list is saved so that the it can be accessed in 0(1) time.*/
	private static int lastIndex = 0;
	//This function flushes lastIndex if any of the checks fail. becasue they will not be able to login at that point so their is no
	//need to save the index.
	protected static void flushLastIndex () {lastIndex = 0;}
	//This is used to set lastIndex when a user is found.
	protected static void setLastIndex (int a) {lastIndex = a;}
	//This function will return the lastIndex when it is needed to access the arraylist.
	protected static int getLastIndex () {return lastIndex;}
	/*This function serializing all users in userList so that they may be saved for the next time the application is run.
	 *This is done by using the ObjectOutputStream to transfer the object userList and its contents, the list of users, into a file
	 *using the FileOutputStream */
	protected static void serializeFile() throws IOException {
		ObjectOutputStream oos = null;
		try{oos = new ObjectOutputStream(new FileOutputStream(perData));
			oos.writeObject(userList);
		}catch(IOException e){e.printStackTrace();}
		finally{if(oos!=null){
				oos.close();
				}
		}
	}
	/*This function deserializing all users from registry into userList when the application is run.
	 *This is done by using the ObjectInputStream to transfer the file registry into the userList array list, 
	 *recreating the list of users, using the FileInputStream*/
	protected static void deserializeFile() throws IOException, ClassNotFoundException{
		ObjectInputStream ois = null;
		try{ois = new ObjectInputStream(new FileInputStream(perData));
			userList = (ArrayList<User>) ois.readObject();}
		catch(IOException e){e.printStackTrace();}
		finally{if(ois!=null){
				ois.close();
			}
		}
	}

	//This function returns the bits a user currently has in their account, using lastIndex.
	protected static int getUserBits() {return userList.get(getLastIndex()).getBits();}
	//Function for accessing a user's bits and depositing an extra bits. Returns true if successfully, returns false if not (Which isn't really possible.)
	protected static boolean depositUserBits(int a) {
		if(userList.get(getLastIndex()).makeDeposit(a)) {return true;}
		else { return false;}
	}
	//Function for accessing a user's bits and withdrawing  bits. Returns true if user has enough of a balance, returns false if user does not.
	protected static boolean withdrawUserBits(int a) {
		if(userList.get(getLastIndex()).makeWithdrawl(a)) {return true;
		}else { return false;}
	}
	
	/* * This function checks what a user inputs as their username and password when attempting to login.
	 * This function returns may different values to determine how the validation process went
	 * 0 = There is no users registered at all, list is empty
	 * 1 = Username provided does NOT match what was found in the records.
	 * 2 = User is using the WRONG password, but the username was found in userList.
	 * 3 = User is NOT validate, but is using the correct password, and username was found in userList.
	 * 4 = User is validated, using the correct password, and username was found in userList.
	 * 5 = If the user is an admin, as they have used the admin password and user name,  5 will be return to indicate a admin is loggin in.
	 * -1 is return if there is an error.*/
	protected static int checkLogin(String username, String password) {
		if(ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {return 5;}
		
		if(userList.size() ==0) {return 0;}
		
		ListIterator<User> li = userList.listIterator(0);
		
		while(li.hasNext()) {
			if (li.next().getUsername().equals(username)) {setLastIndex(li.previousIndex());
				if(li.previous().getPassword().equals(password)) {
					if(li.next().getVal()) {return 4;}
						else {flushLastIndex();return 3;}}
					else {flushLastIndex();return 2;}}
				else {flushLastIndex();return 1;}
					}
		flushLastIndex();return -1;
		}
	/* *Similar to validating user login, this function traverses to userList to find a user provided by the admin. (returns 2 if the user is not found)
	 *However it first checks that the admin is using the correct password for security. (returns 1 otherwise)
	 *Otherwise the user's validation is set to true. (returns 3 for success)
	 *as above, -1 is return if there is an error.*/
	protected static int validateUser(String username,String adminPassword) {
		
		if(userList.size() ==0) {return 0;} // New list, so their are not users registered.
		
		ListIterator<User> li = userList.listIterator(0);
		
		while(li.hasNext()) {
			if(ADMIN_PASSWORD.equals(adminPassword)) {
				if(li.next().getUsername().equals(username)) {
					li.previous().setVal(true);
					return 3;
				}else {return 2;}
			}else {return 1;}
		}
		return -1;}
	/* * When a user attempts to register an account, this function will be called to check if the attempted username has already been used, in which case
	 * true is returned (true because it is true that there is a duplicate). False is returned if the list is empty (there can be no duplicates)
	 * or if no duplicates are found.
	 * If the user tries to uses the same username as the admin, it is also rejected.*/
	protected static boolean checkDuplicateUser(String s) {
		if(userList.size() ==0) {return false;} // New list, so their are not users registered. Therefore no duplicates can occur
		
		if(s.equals(ADMIN_USERNAME)) {return true;}
		
		ListIterator<User> li = userList.listIterator(0);
		
		while(li.hasNext()) {if (li.next().getUsername().equals(s)) {return true;}}
			return false;
		}
}
