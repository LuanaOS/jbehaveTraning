# BDD REST API JBehave [SOLVED]
Simple project solved using HTTP Request in Java using mock.io

## Organization of the directories:
You should have the structure of the files as below:
* src
  * main
    * java
        * apis (Package)
            * ApiException.java
            * room (Package)
                * RoomApi.java
                * model (Package)
                    * RoomAttributes.java
                    * RoomRequest.java
                    * RoomResponse.java
                    * RoomSystemInfo.java
            * user (Package)
                * UserApi.java
                * model (Package)
                    * UserAttributes.java
                    * UserRequest.java
                    * UserResponse.java
                    * UserSystemInfo.java
        * client (Package)
            * BaseClient.java
            * model (Package)
                *  Envelope
                * Request
  * test
    * java
        * steps (Package)
            * room (Package)
                * RoomStep.java
                * RoomTest.java
            * user (Package)
                * UserStep.java
                * UserTest.java
    * resources
        * RoomStory.story
        * UserStory.story
* <Project_Name>.iml
* pom.xml


## Setting up pom.xlm
In this project you need to add the dependencies below on pom.xml file.
```
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <configuration>
                <source>7</source>
                <target>7</target>
            </configuration>
        </plugin>
    </plugins>
</build>

<dependencies>
    <dependency>
        <groupId>org.jbehave</groupId>
        <artifactId>jbehave-core</artifactId>
        <version>4.3.5</version>
    </dependency>
    <dependency>
        <groupId>org.glassfish.jersey.core</groupId>
        <artifactId>jersey-client</artifactId>
        <version>2.25</version>
    </dependency>
    <dependency>
        <groupId>org.glassfish.jersey.media</groupId>
        <artifactId>jersey-media-multipart</artifactId>
        <version>2.25</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-core</artifactId>
        <version>2.9.7</version>
    </dependency>
    <dependency>
        <groupId>org.glassfish.jersey.media</groupId>
        <artifactId>jersey-media-json-jackson</artifactId>
        <version>2.25</version>
    </dependency>
    <dependency>
        <groupId>org.apache.httpcomponents</groupId>
        <artifactId>httpclient</artifactId>
        <version>4.3.6</version>
    </dependency>
</dependencies>
```

## Setting up UserTest.java (Test class):
This is the file that will run the test. That means it will make the connection between the story file and the step file and the java classes.

```
import org.jbehave.core.configuration.Configuration; 
import org.jbehave.core.configuration.MostUsefulConfiguration; 
import org.jbehave.core.junit.JUnitStories; 
import org.jbehave.core.reporters.Format; 
import org.jbehave.core.reporters.StoryReporterBuilder; 
import org.jbehave.core.steps.InjectableStepsFactory; 
import org.jbehave.core.steps.InstanceStepsFactory; 

// In case you need to connect/link more than one story with the same test class 
//import steps.<package_name>.<class_name2>; 
 
import java.util.Arrays; 
import java.util.List; 

public class <class_nameTest> extends JUnitStories {
    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(),
        // Create an instance of the class with step definitions
        new <class_nameStep>()
        // In case you need to connect/link more than one story with the same test class
        // , new <class_nameStep2>()
        );   
    }
    
    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration().useStoryReporterBuilder(new StoryReporterBuilder().withFormats(Format.CONSOLE));   
    }
    
    protected List<String> storyPaths() {
        return Arrays.asList("<name_story>.story"
        // In case you need to connect/link more than one story with the same test class
        // , "<name_story2>.story"
        );
    } 
}
``` 

## Running tests:
On the right side of the screen click on MavenProjects(m).

Open Lifecycle and execute the commands:
* clean
* compile
* test

Or you can go on the file /src/test/java/steps/user/UserTest.java right click on the code background and select Run UserTest. 

# Tasks

## 1) Understand how the project is working with the basic code 

### User’s Structures:
REQUEST(PAYLOAD): 
```
{
    "userAttributes": {
        "emailAddress": STRING,
        "firstName": STRING (required),
        "lastName": STRING,
        "userName": STRING (required)
    },
    "roles": ARRAY STRING (INDIVIDUAL, SEND_MESSAGES, CREATE_ROOM) 
} 
``` 

RESPONSE: 
```
{
    "userAttributes": {
        "emailAddress": "janedoe@symphony.com",
        "firstName": "Jane",
        "lastName": "Doe",
        "userName": "janedoe"
    },
    "userSystemInfo": {
        "id": 7215545078541,
        "status": "ENABLED",
        "createdDate": 1461509290000,
        "createdBy": "7215545078229",
        "lastUpdatedDate": 1461509290000
    },
    "roles": [
        "INDIVIDUAL"
    ],
    "sessionToken": "testSessionToken"
} 
```

### Room’s Structures:
REQUEST 
```
{
    "name": STRING (required),
    "description": STRING (required),
    "membersCanInvite": BOOLEAN default TRUE,
    "discoverable": BOOLEAN default TRUE,
    "public": BOOLEAN default TRUE
} 
```

RESPONSE 
```
{
    "roomAttributes": {
        "name": "API room",
        "description": "Created via the API",
        "membersCanInvite": true,
        "discoverable": true,
        "public": false
    },
    "roomSystemInfo": {
        "id": "w7-C9e34O4EqJJoXnyXLMH___qsIFLKEdA",
        "creationDate": 1464448273802,
        "createdByUserId": 7215545078229,
        "active": true
    }
} 
```

## 2) Create a user
In this part you would have to create a user with different endpoints, as there is no real information, those returned JSON values had to be mocked, and each URL will correspond to a different response. 

On the real-world scenario, those requests should be done against a given endpoint and the different JSONs would be returned based on the parameters used to make the call to the endpoint.

### 2.1) Successful case, status 200 and all the info of the user has been returned

http://www.mocky.io/v2/5baba27f3100005500654488 (200)

### 2.2) Endpoint firstName is required (error 400)  -  (Done as example) 
    
http://www.mocky.io/v2/5bab8fe53100000f006543eb (400 => firstName is required)

### 2.3) Endpoint userName is required (error 400) 

http://www.mocky.io/v2/5bab90183100004c006543ef (400 => userName is required) 

### 2.4) Endpoint invalid roles (error 400) 

http://www.mocky.io/v2/5bab92893100004e00654418 (400 => Invalid roles) 

### 2.5) Endpoint invalid session token (error 401) 

http://www.mocky.io/v2/5bab907131000034006543f3 (401 => Invalid session token)

## 3) Create a room 
      
As on the previous exercise, the returned values also had to be mocked. 
      
This scenario was intended to test the room creation, as well as their error statuses. So, the returned values should match with a given value, based on pre-requisites. For example, when creating a room, it should be always be created as active, otherwise, the test should fail. Another test case could be: When a given user that has no CREATE_ROOM role tries to create a room, there should be an error message informing that it is not possible, in case that user creates a room without that role, the test should fail.

### Prerequisites:  
* The room always should be created as ACTIVE
* Just an user that has the role CREATE_ROOM can create it.
    * Use this endpoint so you can use the user to create a room
        * http://www.mocky.io/v2/5bc4d4ad300000d73f758b97 (200 => user that has the role as CREATE_ROOM)

### 3.1) Successful case, status 200 and all the info of the room has been returned 

http://www.mocky.io/v2/5bbfc2f03200004e006a3006 (200) 

### 3.2) Endpoint name is required (error 400) 

http://www.mocky.io/v2/5bab974b3100007000654446 (400 => name is required) 

### 3.3) Endpoint description is required (error 400) 

http://www.mocky.io/v2/5bab97223100006400654445 (400 => description is required) 

### 3.4) Endpoint Forbidden User (error 403) 

http://www.mocky.io/v2/5bab97d1310000100065444b (403 => Forbidden user)