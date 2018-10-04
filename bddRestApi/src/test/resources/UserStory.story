Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: create an user
Given perform a POST request to the server to create an user
When verify the if status code is 200
Then perform a GET request and check if the information is the same as from the POST
