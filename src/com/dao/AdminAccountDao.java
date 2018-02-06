package com.dao;

import java.sql.SQLException;

import com.adminend.AdminAccount;

public interface AdminAccountDao {
	
	//public void insertBankAccount(BankAccount user);
		public AdminAccount selectAdminAccountByUN(String password) throws SQLException;
		public void ApproveUserAccount (String username);
//		public int getAccountBalance (BankAccount user) throws SQLException;
//		public void withdrawFromAccount (BankAccount user, int withdrawAmt);
//		public void depositToAccount (BankAccount user, int depositAmt);

}
