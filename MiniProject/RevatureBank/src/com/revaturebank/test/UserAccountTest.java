package com.revaturebank.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

 import org.junit.Test;

import com.revaturebank.systemclass.UserAccount;

public class UserAccountTest {
    UserAccount userAccount=new UserAccount("ssn", "fName", "lName", "phone", "email", "username", "password", 3000.0);
    @Test
	public void CountBalanceTest() {
    	userAccount.withdraw(500);
    	double d=userAccount.getBalanceAccount();
		assertEquals(2500, d);
		userAccount.deposit(200.0);
		d=userAccount.getBalanceAccount();
		assertEquals(2700, d);
	}
	

}
