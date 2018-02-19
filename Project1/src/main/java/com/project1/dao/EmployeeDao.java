package com.project1.dao;

import java.util.List;
import com.project1.users.Employee;

public interface EmployeeDao {
//	public void insertIntoUserInfo(String name, String fname, String lname);
//	public void insertIntoAccountInfo(String email, String uname, String pw, int isAd, int isAc, int isCl);
//	public void insertIntoBalanceInfo(String email, double ch, double sa);
//	public void updateFirstName(String email, String first);
//	public void updateLastName(String email, String last);
//	public void updatePassword(String email, String pass);
//	public void updateBalanceInfo(String email, String col, double newVal);
//	public void updateAccountStatusInfo(String email, String col, int newVal);
//	public Account selectAccountByEmail(String email);	//retrieves entire account using email (PK)
	public void /*List<Employee>*/ getAllAccounts();	//admin only (Use Join statement)
	//public void deleteAccountByEmail(String email);	//admin only
}
