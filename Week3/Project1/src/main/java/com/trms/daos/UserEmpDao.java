package com.trms.daos;

public interface UserEmpDao {
	public boolean insertUser(int empid, String email, String username, String password);
	public int getUserEmpId(String username, String password);
	public String getEmail(int empid);
}
