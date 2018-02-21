package com.project1.dao;

import java.util.List;

import com.project1.beans.Personal;
import com.project1.beans.Account;
import com.project1.beans.Event;
import com.project1.beans.Reimbursement;

public interface TRMSDao {
	public void insertIntoPersonal(String email, String firstName, String lastName, String address, String jobTitle, String date);
	public void insertIntoAccounts(String email, String uname, String pw, int isBenCo, int isDirSup, int isDepHead);
	public void insertIntoEvents(String email, String event, String depHeadEmail, String benCoEmail, String dirSupEmail, 
			String approvalEmail, String justification, String grade, String passFail, String startDate, String endDate);
	public void insertIntoReimbursements(String email, double available, double pending, double awarded, double total, String lastReimDate);
	public List<Personal>getAllPersonal();
	public List<Account>getAllAccounts();
	public List<Event>getAllEvents();
	public List<Reimbursement>getAllReimbursements();
	public Personal selectPersonalByEmail(String email);
	public Account selectAccountByEmail(String email);
	public Event selectEventByEmail(String email);
	public Reimbursement selectReimbursementByEmail(String email);
	public void updateStringColumn(String email, String table, String column, String value);
	public void updateDoubleColumn(String email, String table, String column, double value);
	public void updateIntColumn(String email, String table, String column, int value);
//	public List<Employee> getAllEmployees();	//admin only (Use Join statement)
	//public void deleteAccountByEmail(String email);	//admin only
}
