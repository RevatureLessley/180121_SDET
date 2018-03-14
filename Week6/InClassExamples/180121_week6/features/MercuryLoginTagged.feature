Feature: Mercury Tours Login
	I wish to login to Mercury using proper credentials.

#	Background: 
#		Given I am at the login page for mercury tours
#		When	I have inputted my username and password and logged in.
#		Then (Typically not used for background)
#Use the background tag to create a pre condition scenario that runs 
#before EVERY scenario in a file. 		This is useful for sitautions where I have multiple
#scenarios in a feature file and I want a specific set-up scenario to run before all the
#other scenarios. #Consider '@BeforeMethod' in testNg/
	Background:
		Given a user is at the mercury tours homepage


	#Use '@' to apply a custom tag to any element of your feature file
	#Tags are the equivalent of 'groups' in testNg
	@smoke @stuff
	Scenario: Logging into mercury tours
		When After inputting username 
		And password, 
		But a user logs in 
		Then the use is having a great time at the find flights page

		@regression @stuff
	Scenario Outline: Logging into mercury tours
		When a user inputs their "<username>" and "<password>"
		And then clicks submit
		Then the user finds themselves at the find flight page.
		
		Examples:
			|username|password|
			|bobbert|bobbert|
			|tropicana|tropicana|