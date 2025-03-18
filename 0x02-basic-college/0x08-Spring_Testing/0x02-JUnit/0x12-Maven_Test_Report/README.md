# Maven Test Report


## Maven Support for Code Coverage

You can run test report using Maven. This is done on the command line, no IDE is required.

Useful when running as part of DevOps build process. Continuous Integration / Continuous Deployment (CI/CD) environments.


## System Requirements

To complete these steps, you must have Maven installed (not with IDE) from `https://maven.apache.org`

Once installed, verify your Maven installation


```shell
mvn -version
Apache Maven 3.8.4 (9b656c72d54e5bacbed989b64718c159fe39b537)
Maven home: ...
...

```

By default, Maven will NOT find JUnit 5 test classes. At command-line:


```shell
mvn clean test

```

By defaults, no tests will be found.


## Step 2: Configure Maven to find unit tests

You have to configure Maven to find unit tests. To resolve this, you have to use Maven Surefire plugin. For example:


```xml
<build>
  <plugins>
    <!-- Plugin configuration starts here -->
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-surefire-plugin</artifactId>
      <version>3.0.0-M5</version>
    </plugin>
    <!-- Plugin configuration ends here -->
  </plugins>
</build>


```

..............................
