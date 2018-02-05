package com.miniproject.dao;

import com.miniproject.users.Admin;

public interface AdminDao {
	public void insertAdmin(Admin a);
	public Admin getAdmin(String inUsername, String inPassword);
}
