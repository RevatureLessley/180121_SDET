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
	
	public static String getUsername(String in_username) {
		UserDao dao = new UserDaoImpl();
		return dao.getUsername(in_username);
	}
	
	public static void approveUser(String in_username) {
		UserDao dao = new UserDaoImpl();
		dao.approveUser(in_username);
	}
	
	public static void unBanUser(String in_username, int in_option) {
		UserDao dao = new UserDaoImpl();
		dao.uBanUser(in_username, in_option);
	}
	
	public static int loginStreak(String in_username) {
		UserDao dao = new UserDaoImpl();
		return dao.loginStreak(in_username);
	}
}
