package com.revature.day5.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ArithmeticTest {
	
	Arithmetic arithmetic;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Before Class");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("AfterClass");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("Before");
		arithmetic = new Arithmetic();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("After");
		arithmetic = null;
	}

	@Test
	public void test1() {
		assertEquals(15, arithmetic.addition(5,10));
		//System.out.println("Test 1");
	}
	@Test
	public void test2() {
		assertEquals(-5, arithmetic.subtraction(5,10));
		//System.out.println("Test 2");
	}
	@Test
	public void test3() {
		assertEquals(0, arithmetic.division(5,10));
		//System.out.println("Test 3");
	}
	public void test4() {
		assertEquals(50, arithmetic.multiplication(5,10));
		//System.out.println("Test 3");
	}

}
