package com.banksystem.junit;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.banksystem.dao.AccountDaoImpl;

public class BankSystemJUnitTest {

	@Test
	public void getAccountByIdTest() {
		AccountDaoImpl dao = new AccountDaoImpl();
		assertEquals(dao.getAccountById(1).getUsername(), "admin");
	}
	
	
	@Test
	public void depositTest(){
		AccountDaoImpl dao = new AccountDaoImpl();
		dao.updateBalance(2, 500);
		assertEquals(500, dao.getAccountById(2).getBalance(), 0);
	}

}
