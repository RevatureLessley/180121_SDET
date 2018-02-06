package com.banksystem.dao;

import java.util.List;

import com.banksystem.beans.Account;

public interface AccountDao {
	public void addAccount(Account acc);
	public Account getAccountById(int id);
	public List<Account> getAllAccounts();
}
