#Feature is the first keyword of a feature file that simply designates it as such.
Feature: Mercury Tours Login
	I wish to login to Mercury using proper credentials.

	# Scenario keyword designates a single test
	Scenario: Logging into mercury tours
		Given a user is at the login screen for mercury tours.
		When a user shall input a username and a password and click submit
		Then a user shall be redirected to the find flights page
