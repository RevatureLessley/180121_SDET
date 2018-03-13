Feature: Mercury Tours Login
	I wish to login to Mercury using proper credentials.
	
	Background:
		Given: a user is hanging out at the login screen for mercury tours
		
	@smoke @stuff
	Scenario: Logging into mercury tours
		When After inputting username
		And password, a user logs in
		Then the user is having a great time at the find flights page

	@regression
	Scenario Outline: Logging into mercury tours
		When a user inputs their "<username>" and "<password>"
		And then clicks submit
		Then the user finds themselves at the find flight page.
		
		Examples:
			|username|password|
			|bobbert|bobbert|
			|tropicana|tropicana|