Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: create an user with role as CREATE_ROOM
Then an user with the data johndoe@symphony.com John Doe johndoe CREATE_ROOM is created /v2/5bc4d4ad300000d73f758b97

Scenario: Create user without required firstName
Then an user cannot be created without field firstName

Scenario: Create user without required userName
Then an user cannot be created without field userName

Scenario: Create user with invalid roles
Then an user cannot be created without field roles

Scenario: Create user with invalid session token
Then an user cannot be created without field sessionToken

Scenario: create an user
Then an user with the data janedoe@symphony.com Jane Doe janedoe INDIVIDUAL is created /v2/5baba27f3100005500654488