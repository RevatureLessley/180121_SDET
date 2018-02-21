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
//	public void updateLastName(String email, String last);
//	public void updatePassword(String email, String pass);
//	public void updateBalanceInfo(String email, String col, double newVal);
//	public void updateAccountStatusInfo(String email, String col, int newVal);
//	public Account selectAccountByEmail(String email);	//retrieves entire account using email (PK)
//	public List<Employee> getAllEmployees();	//admin only (Use Join statement)
	//public void deleteAccountByEmail(String email);	//admin only
}
