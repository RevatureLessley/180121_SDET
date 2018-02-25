package com.trms.services;

import com.trms.daos.UserEmpDao;
import com.trms.daos.UserEmpDaoImpl;

public class UsersEmpService {
	/**
	 * Inserts a user into the usersemp table.  Checks to make sure all data is valid before inserting data.
	 * @param empid
	 * @param email
	 * @param username
	 * @param password00 First password to check against second
	 * @param password01 Second password to check against first
	 * @return A boolean that indicates whether or not the inserting succeded
	 */
	public static boolean insertUser(int empid, String email, String username, String password00, String password01) {
		UserEmpDao dao = new UserEmpDaoImpl();
		boolean result = true;
		if(dao.checkUsername(username) != null) {
			result = false;
		}
		if(result && password00.equals(password01)) {
			if(dao.checkUserEmpId(empid) == -1) {
				result = dao.insertUser(empid, email, username, password00);
			} else {
				result = false;
			}
		} else {
			result = false;
		}
		
		return result;
	}
	
	public static int getUserEmpId(String username, String password) {
		UserEmpDao dao = new UserEmpDaoImpl();
		//validation
		return dao.getUserEmpId(username, password);
	}
	
	public static String getUserEmail(int empid) {
		UserEmpDao dao = new UserEmpDaoImpl();
		
		return dao.getEmail(empid);
	}
}
