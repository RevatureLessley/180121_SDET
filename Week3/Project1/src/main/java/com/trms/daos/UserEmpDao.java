package com.trms.daos;

public interface UserEmpDao {
	//Insert
	public boolean insertUser(int empid, String email, String username, String password);
	
	//Get
	public int getUserEmpId(String username, String password);
	public String getEmail(int empid);
	public String checkUsername(String username);
	public int checkUserEmpId(int empId);
}
