package com.project1.services;

import com.project1.dao.TRMSDaoImpl;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class NewAccount {
	static TRMSDaoImpl dao;
	public static void insertNewAccount(String firstname, String lastname, String address, int accountType, 
			String email, String password, String confirm) {
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MMM.yyyy");
        String date = sdf.format(cal.getTime());
		dao = new TRMSDaoImpl();
		dao.insertIntoPersonal(email, firstname, lastname, address, date);
	}
	
	public static boolean uniqueEmail(String email) {
		dao = new TRMSDaoImpl();
		List<String> emails = dao.getAllEmails();
		
		for (String e : emails) {
			if (email.equals(e)) return false;
		}
		return true;
	}
}
