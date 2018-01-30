package com.miniproject;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UnitTest {
	UserAdmin userAdmin;

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
			userAdmin = new UserAdmin();
		}

		/*
		 * After should be used to tear down the environment for each individual test.
		 */
		@After
		public void tearDown() throws Exception {
			System.out.println("After");
			userAdmin = null;
		}

		//Indicates a specific test to be run.
		@Test
		public static boolean authenticate(ArrayList<String> al, ArrayList<String> al1) {
	        // takes in array of strings
	        if (al.equals(al) && al1.equals(al1)) {
	            return true;
	        }
	        return false;
}
}