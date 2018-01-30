package com.revature.miniproject;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
public class JUnitTest {
	BankAccount bankAccount;
	@BeforeClass
	public static void beforeClass() throws Exception
	{
		System.out.println("Before Class");	
	}
	@AfterClass
	public static void afterClass() throws Exception
	{
		System.out.println("After Class");
	}
	@Before
	public void before() throws Exception{
		System.out.println("Before");
		bankAccount = new BankAccount("hello", "123");
		bankAccount.setAmount(1000);

	}

	@After
	public void after() throws Exception
	{
		System.out.println("After");
		bankAccount = null;
	}

	@SuppressWarnings("deprecation")
	@Test
	public void withrawalTest()
	{
		assertEquals(900, bankAccount.withdraw(100));
	}
	@SuppressWarnings("deprecation")
	public void depositTest()
	{
		assertEquals(1100, bankAccount.deposit(100));
	}
	@Test(expected=RuntimeException.class)
	public void exceptionTest() {
		throw new RuntimeException();
	}
	@Test(timeout=3000)
	public void timeoutTest() throws InterruptedException {
		Thread.sleep(4000);
	}
}
