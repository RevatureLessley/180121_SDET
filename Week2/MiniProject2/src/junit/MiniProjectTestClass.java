package junit;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import dao.MechanicMethods;


public class MiniProjectTestClass {
	
	static Scanner scan;
	static FileOutputStream fos;
	static ObjectOutputStream oos;
	
	
	final static Logger logger = Logger.getLogger(MiniProjectTestClass.class);
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		scan = new Scanner(System.in);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		scan.close();
	}

	@Before
	public void setUp() throws Exception {


	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDeleteUser() {
	}
	

}
