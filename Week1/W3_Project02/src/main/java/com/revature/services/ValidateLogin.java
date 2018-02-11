package com.revature.services;

public class ValidateLogin {
	

	public static boolean validate(String usernamein, String passwordin) {
		final String username = "Bobbert";
		final String password = "Bobbert";
		//UserDao ud = new UserDao()
		//ud.getUsername.equals. flsdjfslkfj
		if(usernamein.equals(username) && password.equals(passwordin)) {
			return true;
		} else {
			return false;
		}
	}
}
