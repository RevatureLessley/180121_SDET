package com.revature.backend;

import java.util.List;
import java.util.ListIterator;

import com.revature.Users.User;
import com.revature.dao.DataManagerDaoImpl;

public class DataManger {
	static DataManagerDaoImpl dataDao = new DataManagerDaoImpl();
	
	protected static boolean checkDuplicateUser(String s) {
		if(returnTotalUsers() == 0) {return false;} // Empty Table, so no users registered. Therefore no duplicates can occur
		if(checkAdmin(s)) {return true;}// If User atempts to create username with Admin username, returns true as "duplicate"
		if (confirmDuplUser(s)) {return true;}
		return false;
		}

	protected static void checkAllUsers() {
		dataDao.userList.clear();
		dataDao.getAllUsers();
		System.out.println("===============LIST OF ALL USERS ================");
		ListIterator<User> li = dataDao.userList.listIterator(0);
		while(li.hasNext()) {
			System.out.println(li.next().toString());
		}
		System.out.println("===============LIST OF ALL USERS ================");
	}
	
	protected static int returnTotalUsers () {return dataDao.totallUser();}
	
	protected static boolean checkAdmin(String username) {return dataDao.adminUser(username);}
	
	protected static boolean confirmDuplUser (String username) { return dataDao.checkUniqueUser(username);}
	
	protected static void addNewUser (String u,String p) {dataDao.addUser(u,p);};
	
	protected static int checkLogin(String u,String p) {return dataDao.checkCredentials(u, p);}
	
	protected static int validateUser(String user, String pass) {return dataDao.valUser(user,pass);};
	
	protected static String getLoggedInUsername() {return dataDao.currentUserName();}
	
	protected static int getLoggedInBits() {return dataDao.currentUserBits();}
	
	protected static void flush() {dataDao.flushLastIndex ();}
	
	protected static boolean transcationDeposit(int d) { return dataDao.userDeposit(d);};
	
	protected static boolean transcationWitdraw(int w) {return dataDao.userWithdrawl(w);};
	
	protected static void transactionHistory(String u) {dataDao.userHistory(u);};
}
