package com.revature.testrunners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/*
 * The cucumber test runner is used to actually execute the cucumber tests
 * Note: This also serves as our 'test suite' for cucumber tests.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
			features="Features", //I point to where my feature files are
			glue="com.revature.gluecode" //I point to where my feature implementation is
		)
public class TestRunner {

}
