package com.bank.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bank.beans.User;
import com.bank.dao.UserDao;
import com.bank.dao.UserDaoImp;
import com.bank.main.Driver;
import com.bank.page.Page;


public class UserService {
	
	//Logger
	final static Logger logger = Logger.getLogger(Driver.class);
	static UserDao dao = new UserDaoImp();
	
	public static void addNewUser(String userName, String password) {
		// Add new user to database
		User u = new User(userName, password, null);
		if(dao.insertUser(u)){
			System.out.println("Registration Successful.\n"+
					"Please wait until our Admin approves your account\n");
			logger.info("New User "+userName+" registration success");
		}else{
			System.out.println("Registation Unsuccessful.\n" + 
					"Please try again with different Username\n");
			logger.info("New User "+userName+" registration failed");
		}
	}

	
	public static int getUserStatus(String userName, String password) throws IOException {
		// return user status value
		List<User> users = dao.getAllUser();
		for(User u: users){
			if(u.getUserName().equals(userName) && u.getPassword().equals(password)) {
				if(u.getStatus().equals("SuperUser")) {
					Page.adminPage(u);
					return 0;
				}else if (u.getStatus().equals("User")){
					Page.customerPage(u);
					return 1;
				}else if (u.getStatus().equals("NonUser")){
					Page.nonUser();
					return 2;
				}
			}
		}
		return -1;
	}

	
	public static List<User> get_NewUsers() {
		// return only new users
		List<User> newUser = new ArrayList<>();
		for(User u : dao.getAllUser()) {
			if(u.getStatus().equals("NonUser")) {
				newUser.add(u);
			}
		}
		return newUser;
	}

	
	public static void approved(User p) {
		// Update customer status
		if(dao.updateUser(p)){
			System.out.println(p.getUserName()+" account Approved");
			logger.info(p.getUserName()+" account Approved");
		}else{
			System.out.println(p.getUserName()+" account Approval failed");
			logger.info(p.getUserName()+" account Approval failed");
		}
		
	}

	public static void reject(User p) {
		// Delete user record
		if(dao.deleteUser(p)){
			System.out.println(p.getUserName()+" account deleted");
			logger.info(p.getUserName()+" account deleted");
		}else{
			System.out.println(p.getUserName()+" account termination failed");
			logger.info(p.getUserName()+" account termination failed");
		}
	}

	
	
}
