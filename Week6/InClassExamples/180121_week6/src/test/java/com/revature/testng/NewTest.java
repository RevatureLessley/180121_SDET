package com.revature.testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/*
 * With TestNG, we get access to more before/after options than our JUnit predecessor.
 * 
 * Should you utilize other classes witn the test file, the before/after class annotation
 * will trigger. for each invocation of the contents.
 * 
 * Should you utilize multiple test classes within the same class file (IE, a suite)
 * the before/after test annotations will trigger.
 * 
 * Should you utilize multiple suites, the before/after suite annotations will trigger.
 * 
 * Before//after method triggers for each @Test annotation.
 * 
 * Note: There is a 5th before/after annotation called before/after group.
 * TestNg allows you to make custom group tags and apply them to groupings of methods.
 * Before and after each specific group executes, these annotations trigger.
 */

public class NewTest {
  @Test(groups={"group1"})
  public void test1() {
	  System.out.println("TEST1");
	  
  }
  
  @Test(groups={"group1"})
  public void test3() {
	  System.out.println("TEST3");
	  
  }
  @Test(groups={"group2"})
  public void test2() {
	  System.out.println("TEST2");
	  
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("BEFORE METHOD");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("AFTER METHOD");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("BEFORE CLASS");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("AFTER CLASS");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("BEFORE TEST");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("AFTER TEST");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("BEFORE SUITE");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("AFTER SUITE");
  }

}
