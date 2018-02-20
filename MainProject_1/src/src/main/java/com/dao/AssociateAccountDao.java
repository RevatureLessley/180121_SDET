package com.dao;

import java.sql.SQLException;

import com.userend.AssociateAccount;


public interface AssociateAccountDao {

	public int insertAssociateAccount(AssociateAccount user) throws SQLException;
	//public void insertAssociateAccountTest(String Test);
	public AssociateAccount getUN_PW(AssociateAccount user) throws SQLException;
}
