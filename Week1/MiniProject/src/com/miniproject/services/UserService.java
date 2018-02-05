package com.miniproject.services;

import java.util.List;

import com.miniproject.dao.UserDao;
import com.miniproject.dao.UserDaoImpl;
import com.miniproject.users.User;

public class UserService {
	public static void addUser(String in_username, String in_password) {
		UserDao dao = new UserDaoImpl();
		dao.insertUser(new User(in_username, in_password));
	}
	
	public static User getUser(String in_username, String in_password) {
		UserDao dao = new UserDaoImpl();
		return dao.getUser(in_username, in_password);
	}
	public static List<User> getUsersForAdminForAdmin() {
		UserDao dao = new UserDaoImpl();
		return dao.getAllUsersForAdmin();
	}
}
