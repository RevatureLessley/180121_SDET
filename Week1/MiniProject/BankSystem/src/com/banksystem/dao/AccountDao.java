package com.banksystem.dao;

import java.util.List;

import com.banksystem.beans.Account;

public interface AccountDao {
	public void insertAccount(Account acc);
	public Account selectAccountById(int id);
	public List<Account> getAllAccounts();
}
