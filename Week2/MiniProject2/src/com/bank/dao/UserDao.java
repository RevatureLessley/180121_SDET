package com.bank.dao;

import java.util.List;

import com.bank.beans.User;

public interface UserDao {
	
	public boolean insertUser(User u);
	public List<User> getAllUser();
	public boolean deleteUser(User u);
	public boolean updateUser(User u);
	
}
