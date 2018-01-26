package com.revature.day5.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ArithmeticTest.class, ArithmeticTest2.class, ArithmeticTest3.class })
public class AllTests {

}
