# HTTP Request
Simple HTTP Request in Java

## Getting Started
### Setting up Maven
#### Step 1 - Create project:
* Open **IntelliJ** and create a *new project*.
* Select **Maven** if you do **not** have SDK then you should *download JDK*.
* Fill up the GroupId (Package Name) and ArtifactId (Project Name).

#### Step 2 - Set up environment:
Installs plugins and Restart: 
* File > Settings > Plugins > Browser Repositories...
  * JBehave BuDDy
  * JBehave Support

#### Step 3 - Organization of the directories:
You should have the structure of the files as below:
* .idea
* src
  * main
    * java
    * resources
  * test
    * java
* <Project_Name>.iml
* pom.xml

Under the folder **src/test** you should create a folder **resources**.
* test
  * java
  * resources

##### Stories files (.story): Story
Stories file should be create on the folder **src/test/resources**.
* Right click on **src/test/resources** and select **New > JBehave Story**

##### Java files (.java): Test and Steps
Tests files and Steps files should be create inside a package.

Create a package:
* On the folder **src/test/java** right click and select **New > Package**.

Create tests and steps file
* On the folder **src/test/java/<my_package>** right click and select **New > Java Class**.

#### Step 4 - Setting up pom.xlm
To set up JBehave you need to add the dependency as a dependency on pom.xml file.
```
<dependencies>
    <dependency>
        <groupId>org.jbehave</groupId>
        <artifactId>jbehave-core</artifactId>
        <version>4.3.5</version>
    </dependency>
</dependencies>
```

#### Step 5 - Running tests:
On the right side of the screen click on MavenProjects(*m*).

Open Lifecycle and execute the commands:
* clean
* compile
* test

Or you can go on the top bar: Run > Run '<Test>.java'

## Sources:

* Do a Simple HTTP Request in Java: https://www.baeldung.com/java-http-request