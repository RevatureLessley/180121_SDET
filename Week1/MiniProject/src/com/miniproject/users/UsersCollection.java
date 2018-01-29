package com.miniproject.users;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UsersCollection implements Serializable {
	
	private static final long serialVersionUID = 40L;
	private Map<String, Account> regAccounts = new HashMap<>();
	
	public UsersCollection() {
		
	}
	
	public Map<String, Account> getRegAccounts(){
		return regAccounts;
	}
	
	public void createUser(Account inAcc) {
		Account aUser = inAcc;
		regAccounts.put(inAcc.getUsername(),aUser);
	}
	
	public Account getAccount(String inUn) {
		return regAccounts.get(inUn);
	}
	
}
