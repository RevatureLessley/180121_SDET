package com.revaturebank.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

import com.revaturebank.systemclass.Transaction;
import com.revaturebank.systemclass.UserAccount;
import com.revaturebank.systemclass.UserAccountList;

public interface UserAccountDAO {
	final static Logger logger=Logger.getLogger(UserAccountList.class);
	public boolean requestUserAccount(String username,String password,String ssn,String firtName,String lastName,String email,String phone);
	public boolean updateUserAccountPI(String username,String ssn,String firtName,String lastName,String email,String phone);
	public boolean updateUserAccuntUP(String u,String username,String password);
	public UserAccount authenticate(String username, String password) throws SQLException;
	public boolean balanceUpdate(String username, Double amount, String descr ,Double   balance);
	boolean search(String username) throws SQLException;
	public ArrayList<Transaction> getTransactionsAccount(String username) throws SQLException;
	

}
