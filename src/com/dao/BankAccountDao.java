package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.userend.BankAccount;

public interface BankAccountDao {

		public void insertBankAccount(BankAccount user);
		public BankAccount selectBankAccountByUN(String username, String password) throws SQLException;
		public int getAccountBalance (BankAccount user) throws SQLException;
		public void withdrawFromAccount (BankAccount user, int withdrawAmt);
		public void depositToAccount (BankAccount user, int depositAmt);
	
}
