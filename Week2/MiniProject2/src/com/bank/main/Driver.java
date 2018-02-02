package com.bank.main;

import java.util.List;

import com.bank.beans.User;
import com.bank.dao.UserDao;
import com.bank.dao.UserDaoImp;

public class Driver {

	public static void main(String[] args) {
		
		UserDao dao = new UserDaoImp();
		List<User> users = dao.getAllUser();
		
		System.out.println("====LIST OF User====");
		for(User u: users){
			System.out.println(u.getUserName());
		}
		
	}

}
