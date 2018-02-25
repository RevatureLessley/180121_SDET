package com.trms.beans;

/**
 * Beans class for the table that handles the user's login information
 * @author Lynda
 *
 */
public class UserEmp {
	private int userId;
	private int empId;
	private String username;
	private String password;
	private String email;
	
	
	public UserEmp(int userId, int empId, String username, String password, String email) {
		this.userId = userId;
		this.empId = empId;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
