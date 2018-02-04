package com.miniproj.main;

import com.miniproj.beans.Account;
import com.miniproj.dao.AccountDao;
import com.miniproj.dao.AccountDaoImpl;

public class Main {
	public static void main(String[] args) {
		AccountDao dao = new AccountDaoImpl();
	}
}
