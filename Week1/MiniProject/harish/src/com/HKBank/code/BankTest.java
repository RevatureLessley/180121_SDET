package com.HKBank.code;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BankTest {
	
	Bank b;

	@Before
	public void setUp() throws Exception {
		b = new Bank();
	}

	@After
	public void tearDown() throws Exception {
		b = null;
	}
	
	//Exit test
	@Test
	public void exitTest() {
		assertEquals(false, b.exit());
	}
	
}
