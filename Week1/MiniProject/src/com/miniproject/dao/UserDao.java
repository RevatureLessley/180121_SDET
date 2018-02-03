package com.miniproject.dao;

import java.util.List;

import com.miniproject.users.User;

public interface UserDao {
	public void insertUser(User u);
	public List<User> getAllUsersForAdmin();
}
