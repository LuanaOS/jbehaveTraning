# BDD REST API JBehave
Simple HTTP Request in Java using mock.io


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


## Running tests:
Run /src/test/java/steps/user/RoomTest.java
