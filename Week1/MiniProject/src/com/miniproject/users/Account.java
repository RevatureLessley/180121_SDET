package com.miniproject.users;

import java.io.Serializable;

/*
 * Account base class for the other two types of accounts to extend.  Sets up methods
 * and interface methods for the classes that extend this class to use.
 */
public abstract class Account implements Comparable<Account>, Serializable {

	private static final long serialVersionUID = 1657445948440785920L;
	private String username;
	private String password;
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public abstract boolean getIsAdmin();
	
	public abstract boolean isAccountApproved();
	
	public abstract void setAccountApproved(boolean accountApproved);
	
	public abstract boolean isBanned();
	
	public abstract void setBanned(boolean banned);
	
	@Override
	public int compareTo(Account o) {
		return o.getUsername().compareTo(this.username);
	}
}
