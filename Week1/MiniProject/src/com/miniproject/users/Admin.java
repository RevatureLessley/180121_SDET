package com.miniproject.users;

import java.io.Serializable;

/*
 * Admin class defines methods to use on an admin user.
 */
public class Admin extends Account implements Serializable{
	
	private static final long serialVersionUID = -181823672366575954L;
	private String username;
	private String password;
	private boolean isAdmin = true;
	
	public Admin(String inUn, String inPw) {
		this.username = inUn;
		this.password = inPw;
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
	
	public boolean getIsAdmin() {
		return this.isAdmin;
	}
	
	@Override
	public boolean isAccountApproved() {
		return true;
	}
	
	@Override
	public void setAccountApproved(boolean accountApproved) {
	}
	
	@Override
	public boolean isBanned() {
		return false;
	}
	
	@Override
	public void setBanned(boolean banned) {
	}
	
	@Override
	public String toString() {
		return this.username;
	}
	
}
