package com.revature.project1.bankingsoftware;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.project1.bankingsoftware.Backend;;

public class ATestBackend {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("BeforeClass");
		Backend.serializeFile();
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("AfterClass");
		Backend.deserializeFile();
	}
	
	@Before
	public void setUp() throws Exception {System.out.println("Before");}

	@After
	public void tearDown() throws Exception {System.out.println("After");}	
	
	
	@Test
	public void checkDuplicateUserTest() {assertEquals(true, Backend.checkDuplicateUser("Admin"));}
	
	@Test
	public void checkLoginTest() {assertEquals(4, Backend.checkLogin("Christian", "Diaz"));}
	
	@Test
	public void depositUserBitsTest() {assertEquals(true, Backend.depositUserBits(1));}
	
	@Test
	public void withdrawUserBitsTest() {assertEquals(true, Backend.withdrawUserBits(1));}
	
	@Test
	public void validateUserTest() {assertEquals(3, Backend.checkLogin("Jasmin", "SecurePassword1"));}
	
	@Test
	public void  getLastIndexTest() {assertEquals(0,Backend.getLastIndex());}
	
	
}
