package com.project1.services;

public class ValidateLogin {
	//get password from database using email to check (might need to create for dao methods).

	public static boolean validate(String usernamein, String passwordin){
		
		final String username = "bob@bob";
		final String password = "bob";
		//UserDao ud = new UserDao()
		//ud.getUsername.equals.etcetcetc
		if(username.equals(usernamein) && password.equals(passwordin)){
			return true;
		}else{
			return false;
		}
		
	}
}
