package com.trms.services;

import com.trms.daos.UserEmpDao;
import com.trms.daos.UserEmpDaoImpl;

public class UserRegisterService {
	public static boolean insertUser(int empid, String email, String username, String password) {
		UserEmpDao dao = new UserEmpDaoImpl();
		//check if id exists
		//make sure username doesn't already exists
		return dao.insertUser(empid, email, username, password);
	}
}
