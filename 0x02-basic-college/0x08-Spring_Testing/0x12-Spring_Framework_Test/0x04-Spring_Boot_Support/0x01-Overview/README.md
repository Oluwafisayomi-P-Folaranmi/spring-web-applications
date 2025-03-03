# Overview

So far, we've not been doing stuffs with Spring. We want to pick up testing Spring beans. Spring Boot have support to testing beans.


## What You need for Spring Boot Unit Testing

The `@SpringBootTest` annotation:

  + Loads Spring Application Context for access.
  + Support for Spring dependency injection.
  + Loads and retrieve data from Spring application.properties.
  + Mock object support for web, data, REST APIs etc.

The Maven dependency to use Spring Boot Unit Testing is:


```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>

```

This starter includes a transitive dependency on JUnit 5. We get it for free.


## Step 1: Loads Spring Application Context for access

Use the `@SpringBootTest` annotation as a top level annotation to load the Spring Application Context.


```java
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationExampleTest {

    @Test
    void basicTest() {
        // ...
    }
}

```


## Step 2: Inject Spring Application

After gaining access to the Spring Application, you can inject the Spring beans.


```java
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class ApplicationExampleTest {

    @Autowired
    private StudentGrades studentGrades;

    @Test
    void basicTest() {
        // ...
    }
}

```


### Access Application Properties

You can access the Spring application.properties file by using the `@Value` annotation. Spring Boot uses the `@Value` annotation with the value passed into the parenthesis to locate any properties in the application.properties file.


```java
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Value;

@SpringBootTest
public class SpringBootTestExample {

    @Value("${info.school.name}")
    private String schoolName;

    @Value("${info.appName}")
    private String appInfo;

    @Test
    void basicTest() {
        // ...
    }
}

```


### Access Application Context

We can also access the Application Context.


```java
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class ApplicationExampleTest {

    @Autowired
    private StudentGrades studentGrades;

    @Test
    void basicTest() {
        // ...
    }
}

```
