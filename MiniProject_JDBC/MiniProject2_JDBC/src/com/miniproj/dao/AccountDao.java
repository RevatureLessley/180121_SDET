package com.miniproj.dao;

import java.util.List;
import com.miniproj.beans.Account;

/*
 * Typically, you will start your dao with a skeleton to enforce
 * that all required methods are implemented for any situations that
 * involve interacting with employees in the database.
 */
public interface AccountDao {
	public void insertIntoUserInfo(String name, String fname, String lname);
	public void insertIntoAccountInfo(String email, String uname, String pw, int isAd, int isAc, int isCl);
	public void insertIntoBalanceInfo(String email, double ch, double sa);
	public Account selectAccountByEmail(String email);	//retrieves entire account using email (PK)
	public List<Account> getAllAccounts();	//admin only (Use Join statement)
	public void deleteAccountByEmail(String email);	//admin only
	public void updateAccountByEmail(String email);
}
