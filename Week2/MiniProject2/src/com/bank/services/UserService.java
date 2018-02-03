package com.bank.services;

import java.util.List;

import com.bank.beans.User;
import com.bank.dao.UserDao;
import com.bank.dao.UserDaoImp;


public class UserService {
	
	public static void displayAllUsers(){
		UserDao dao = new UserDaoImp();
		List<User> users = dao.getAllUser();
		
		System.out.println("====LIST OF USERS====");
		for(User u: users){
			System.out.println(u.getUserName()+" "+u.getStatus());
		}
	}

}
