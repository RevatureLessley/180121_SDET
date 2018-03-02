Feature: Title of your feature
  I want to use this template for my feature file

  Scenario: Get to Confirmation Page
		Given a user is at the login screen for mercury tours.
		When a user shall input a username and a password and click submit 
    Given a user is redirected to the flight finder page
		Then a user shall fill out the form
		Given a user is redirected to the select flight page
		Then a user shall choose a radio button from each group
		Given a user is redirected to the book a flight page
		Then a user shall fill out the booking form
		Given a user is redirected to the confirmation page
		