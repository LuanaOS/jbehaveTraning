Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: create room
Given verify if user 7215545078542 has role as CREATE_ROOM
Then create a room

Scenario: Create room without required name
Given verify if user 7215545078542 has role as CREATE_ROOM
Then a room cannot be created without field name

Scenario: Create room without required description
Given verify if user 7215545078542 has role as CREATE_ROOM
Then a room cannot be created without field description

Scenario: create room with user role INDIVIDUAL
Given verify if user 7215545078541 has role as CREATE_ROOM
Then create a room