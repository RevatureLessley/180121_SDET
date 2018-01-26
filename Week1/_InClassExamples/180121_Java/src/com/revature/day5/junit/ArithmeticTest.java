package com.revature.day5.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/*
 * JUnit is testing tool for unit tests.
 * A unit test is the most micro level of testing you can perform on an application.
 * The goal of unit testing is to have a test for every single method in the application
 * to ensure it works as expected before you even bother running it.
 */

public class ArithmeticTest {
	Arithmetic arithmetic;
	
	//BeforeClass methods should aim to set up the global environment for your tests
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("BeforeClass");
	}

	/*
	 * AfterClass should aim to tear down the global environment.
	 * IE, close any streams that were being used for the whole test.
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("AfterClass");
	}

	/*
	 * Before should be used to set up the environment for each individual test.
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("Before");
		arithmetic = new Arithmetic();
	}

	/*
	 * After should be used to tear down the environment for each individual test.
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("After");
		arithmetic = null;
	}

	//Indicates a specific test to be run.
	@Test
	public void additionTest() {
		assertEquals(15, arithmetic.addition(5, 10));
	}
	
	@Test
	public void subtractionTest() {
		assertEquals(-5, arithmetic.subtraction(5, 10));
	}
	
	@Test
	public void divisionTest() {
		assertEquals(0, arithmetic.division(5, 10));
	}
	
	@Test
	public void multiplicationTest() {
		assertEquals(50, arithmetic.multiplication(5, 10));
		/*
		 * assertEquals
		 * assertNotEquals
		 * assertNull
		 * assertNotNull
		 * assertArrayEquals
		 */
	}
	
	//@Ignore will skip a given test.
	@Ignore
	@Test
	public void randomNonsense() {
		fail("woops");
	}
	
	/*
	 * The @Test annotation can make use of two separate properties.
	 * One of which is the expected property which tells the test to expect an exception of some 
	 * kind.
	 * If the expected exception does NOT occur, the test fails.
	 */
	@Test(expected=RuntimeException.class)
	public void exceptionTest() {
		throw new RuntimeException();
	}
	/*
	 * The timeout property aims to fail a test if takes too long to complete.
	 * You provide it with a time limit that, when exceeded, automatically fails the test
	 * and halts method.
	 */
	@Test(timeout=3000)
	public void timeoutTest() throws InterruptedException {
		Thread.sleep(4000);
	}

}
