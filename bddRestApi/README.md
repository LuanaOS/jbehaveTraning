# BDD REST API JBehave
Simple HTTP Request in Java using mock.io


## Setting up pom.xlm
In this project you need to add the dependencies below on pom.xml file.
```
<dependencies>
    <dependency>
                <groupId>org.jbehave</groupId>
                <artifactId>jbehave-core</artifactId>
                <version>4.3.5</version>
            </dependency>
            <dependency>
                <groupId>org.glassfish.jersey.core</groupId>
                <artifactId>jersey-client</artifactId>
                <version>2.27</version>
            </dependency>
            <dependency>
                <groupId>org.glassfish.jersey.media</groupId>
                <artifactId>jersey-media-multipart</artifactId>
                <version>2.27</version>
            </dependency>
            <dependency>
                <groupId>com.fasterxml.jackson.core</groupId>
                <artifactId>jackson-core</artifactId>
                <version>2.9.7</version>
            </dependency>
            <dependency>
                <groupId>org.glassfish.jersey.media</groupId>
                <artifactId>jersey-media-json-jackson</artifactId>
                <version>2.27</version>
            </dependency>
</dependencies>
```


## Organization of the directories:
You should have the structure of the files as below:
* src
  * main
    * java
        * apis (Package)
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
            * user (Package)
                * UserStep.java
                * UserTest.java
    * resources
        * UserStory.story
* <Project_Name>.iml
* pom.xml


## Running tests:
Run /src/test/java/steps/user/UserTest.java
