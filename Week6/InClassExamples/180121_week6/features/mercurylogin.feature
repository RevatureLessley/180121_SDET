#Feature is the first keyword of a feature file that simply designates it as sucj
Feature: Mercury Tours Login
	I wish to login to Mercury using proper credentials
	
	#Scenario keywords designates a single test
	#Scenario Outline: Is used for a parameterized test. It must be used with an 
	#Examples: table
	Scenario Outline: Logging into mercury tours
		Given A user at the login screen for mercury tours.
				|username		|password		|
				|bobbert		|bobbert		|
				|superman		|bobbert		|
				|bobbert		|bobbert		|
				|bobbert		|bobbert		|			
		When a user shall input a "<username>" and a "<password>" and click submit
		Then a user shall be redirected to the find flights page
		#Whitespace doesnt matter
		
#		Examples:
#		|username		|password		|
#		|bobbert		|bobbert		|
#		|superman		|bobbert		|
#		|bobbert		|bobbert		|
#		|bobbert		|bobbert		|	