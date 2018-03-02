package testrunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/*
 * The cucumber test runner is used to actually execute the cucumber tests
 * This also serves as our test suite for cucumber tests
 */

@RunWith(Cucumber.class)
@CucumberOptions(
			features = "features", //I point to where my feature files are
			glue="gluecode"
		)
public class TestRunner {
	
}
