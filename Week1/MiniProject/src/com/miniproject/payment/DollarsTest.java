package com.miniproject.payment;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DollarsTest {
	final static Logger logger = Logger.getLogger(DollarsTest.class);
	Dollars d;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger.info("BEFORECLASS");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		logger.info("AFTERCLASS");
	}

	@Before
	public void setUp() throws Exception {
		d = new Dollars();
	}

	@After
	public void tearDown() throws Exception {
		d = null;
	}

	@Test
	public void setDollarAmntTest() {
		d.setCurrency(10.30);
		assertEquals(10.30, d.getCurrency(), 0.001);
	}
	
	@Test
	public void decDollarTest() {
		d.setCurrency(10.30);
		d.decreaseCurrency(3.20);
		assertEquals(7.10, d.getCurrency(), 0.001);
	}
	
	@Test
	public void incDollarTest() {
		d.setCurrency(10.30);
		d.increaseCurrency(9.95);
		assertEquals(20.25, d.getCurrency(), 0.001);
	}

}
