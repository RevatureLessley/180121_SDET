package com.revature.service;

public class ValidateLogin {

	public static boolean validate(String usernamein, String passwordin){
		final String username = "bobbert";
		final String password = "bobbert";
		//UserDao ud = new UserDao()
		//ud.getUsername.equals.etcetcetc
		if(username.equals(usernamein) && password.equals(passwordin)){
			return true;
		}else{
			return false;
		}
		
	}
}
