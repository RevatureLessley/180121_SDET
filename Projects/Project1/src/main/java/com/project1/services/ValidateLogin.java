package com.project1.services;

import java.util.List;

import com.project1.dao.TRMSDaoImpl;

public class ValidateLogin {
	//get password from database using email to check (might need to create for dao methods).

	public static boolean validate(String email, String password){
		TRMSDaoImpl dao = new TRMSDaoImpl();
		List<String> emails = dao.getAllEmails();
		
		for (String e : emails) {
			if (email.equals(e)) break;
			else return false;
		}
		
		String pw = dao.getStringValue(email, "account_info", "pw");
		System.out.println(pw);
		if (pw == null) return false;
		
		if(pw.equals(password)){
			System.out.println("Successful login");
			return true;
		}else{
			System.out.println("Sorry dawgz");
			return false;
		}
		
	}
}
