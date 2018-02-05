package com.miniproject.services;

import com.miniproject.dao.AdminDao;
import com.miniproject.dao.AdminDaoImpl;
import com.miniproject.users.Admin;

public class AdminService {
	public static void addAdmin(String in_Username, String in_Password) {
		AdminDao dao = new AdminDaoImpl();
		dao.insertAdmin(new Admin(in_Username, in_Password));
	}
	
	public static Admin getAdmin(String in_Username, String in_Password) {
		AdminDao dao = new AdminDaoImpl();
		return dao.getAdmin(in_Username, in_Password);
	}
	
	public static String getUsername(String in_Username) {
		AdminDao dao = new AdminDaoImpl();
		return dao.getUsername(in_Username);
	}
}
