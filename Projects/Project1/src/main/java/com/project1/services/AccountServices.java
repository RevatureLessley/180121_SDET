package com.project1.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.project1.dao.TRMSDaoImpl;

public class AccountServices {
	static TRMSDaoImpl dao;
	
	public static boolean uniqueEmail(String email) {
		dao = new TRMSDaoImpl();
		List<String> emails = dao.getAllEmails();
		
		for (String e : emails) {
			if (email.equals(e)) return false;
		}
		return true;
	}
	
	public static void insertNewAccount(String firstname, String lastname, String address, String accountType, 
			String email, String password, String confirm) {
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MMM.yyyy");
        String date = sdf.format(cal.getTime());
		dao = new TRMSDaoImpl();
		dao.insertIntoPersonal(email, firstname, lastname, address, date);
		if (accountType.equals("0")) dao.insertIntoAccounts(email, password, "0", "0", "0");
		else if (accountType.equals("1")) dao.insertIntoAccounts(email, password, "0", "1", "0");
		else dao.insertIntoAccounts(email, password, "0", "0", "1");
	}
	
	public static boolean validate(String email, String password){
		dao = new TRMSDaoImpl();
		String pw = dao.getStringValue(email, "account_info", "pw");
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
