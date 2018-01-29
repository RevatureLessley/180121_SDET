package com.miniproject.users;
import java.io.Serializable;

import org.apache.log4j.Logger;

import com.miniproject.payment.*;

/*
 * User
 */
public class User extends Account implements Serializable {
	
	final static Logger logger = Logger.getLogger(User.class);
	private static final long serialVersionUID = -3094224656319215261L;
	private String username;
	private String password;
	private AbstractCurrency currency;
	//Was going to be used for a feature that increased amount of money in account
	public int daysLoggedIn;
	private boolean accountApproved;
	private boolean banned;
	private boolean isAdmin = false;
	
	public User() {
		this.accountApproved = false;
		this.banned = true;
		this.currency = new Dollars();
	}
	
	public User(String inUn, String inPw) {
		this.username = inUn;
		this.password = inPw;
		this.accountApproved = false;
		this.banned = false;
		this.isAdmin = false;
		this.currency = new Dollars();
	}
	
	public void setUsername(String inUn) {
		this.username = inUn;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setPassword(String inPw) {
		this.password = inPw;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public boolean isAccountApproved() {
		return accountApproved;
	}

	public void setAccountApproved(boolean accountApproved) {
		logger.info(this.username + " approved=" + accountApproved);
		this.accountApproved = accountApproved;
	}

	public boolean isBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		logger.info(this.username + " banned=" + banned);
		this.banned = banned;
	}

	public void depositCurrency(double inCurr) {
		logger.info(inCurr + "deposited");
		this.currency.increaseCurrency(inCurr);
	}
	
	public void withdrawCurrency(double inCurr) {
		this.currency.decreaseCurrency(inCurr);
	}
	
	public AbstractCurrency getCurrency() {
		return this.currency;
	}

	@Override
	public boolean getIsAdmin() {
		return this.isAdmin;
	}
	@Override
	public String toString() {
		return this.username;
	}
	
}