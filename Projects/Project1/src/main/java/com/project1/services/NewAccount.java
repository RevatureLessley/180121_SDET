package com.project1.services;

import com.project1.dao.TRMSDaoImpl;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewAccount {
	
	public static void insertNewAccount(String firstname, String lastname, String address, int accountType, 
			String email, String password, String confirm) {
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MMM.yyyy");
        String date = sdf.format(cal.getTime());
		TRMSDaoImpl dao = new TRMSDaoImpl();
		dao.insertIntoPersonal(email, firstname, lastname, address, "whatever", date);
	}
}
