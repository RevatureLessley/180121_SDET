package com.trms.daos;

public interface UserEmpDao {
	public boolean insertUser(int empid, String email, String username, String password);
}
