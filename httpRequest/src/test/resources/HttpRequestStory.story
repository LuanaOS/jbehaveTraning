Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: connect to the API and look up for the food
Given I perform a GET request to the server APP_Id 0220cd5c APP_Key f0b71e7521f9a8d8fb3d731f0fc7347e looking up for shitake
When I verify the status code is 200
Then I check the body of the response has the value of shitake

Given I perform a GET request to the server APP_Id 0220cd5c APP_Key f0b71e7521f9a8d8fb3d731f0fc7347a looking up for apple galla
When I verify the status code is 401