package com.miniproj.main;

import com.miniproj.beans.Account;
import com.miniproj.services.AccountService;

public class Main {
	public static void main(String[] args) {
		Account a = new Account();
		AccountService as = new AccountService();
		as.displayInfoForUser(a);
	}
}
