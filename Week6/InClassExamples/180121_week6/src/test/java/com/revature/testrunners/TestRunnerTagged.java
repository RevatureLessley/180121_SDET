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
			features={"Features/MercuryLoginTagged.feature"}, //I point to where my feature files are
			glue={"com.revature.gluecode"}, //I point to where my feature implementation is
			tags={"@smoke"}
		)
public class TestRunnerTagged {
	/*
	 * When saying which tags to use in cucumber options,
	 * you have the following options:
	 * Excluding tags: tag={"~@tagname"}
	 * Run tests that have ALL indicated tags only. 
	 * 			tags={"@tag1", "@tag2"}
	 * Run tests that have Either/or of labeled tags:
	 * 			tags={"@tag1,@tag2"}
	 */
}
