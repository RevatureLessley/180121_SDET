package com.miniproject.dao;

import java.util.List;

import com.miniproject.users.User;

public interface UserDao {
	public void insertUser(User u);
	public User getUser(String inUsername, String inPassword);
	public List<User> getAllUsersForAdmin();
	public String getUsername(String inUsername);
	public void approveUser(String inUsername);
	public void uBanUser(String inUsername, int inOption);
	public int loginStreak(String inUsername);
}
