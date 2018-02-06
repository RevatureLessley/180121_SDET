package com.revaturebank.dao;

import java.sql.SQLException;
import java.util.ArrayList;


import com.revaturebank.systemclass.Admin;
import com.revaturebank.systemclass.UserAccount;

public interface AdminDAO {
	public boolean insertAdmin(String adminId,String username,String password,String ssn,String firstName,String lastName,String email,String phone);
	public boolean updateAdmin(String username, String password);
	public Admin authenticate(String username,String password) throws SQLException;
	public boolean deleteAdmin(String adminId, String username);
	public UserAccount search(String username) throws SQLException;
	public boolean deleteUserAccount(String accountNumber, String username);
	public ArrayList<UserAccount> allAccountRequest() throws SQLException;
	public boolean insertUserAccount(UserAccount ua);
	
	

}
