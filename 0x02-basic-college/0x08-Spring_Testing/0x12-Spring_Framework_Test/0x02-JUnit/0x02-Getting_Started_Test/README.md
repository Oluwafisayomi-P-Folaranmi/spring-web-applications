# Getting Started Test

+ Let's start with some simple examples for unit testing

+ At the moment, we will focus on the JUnit fundamentals for testing
  - How to define a test
  - JUnit Assertions
  - Running tests

Say we have a class, `DemoUtils` and we want to make some test on the implemented methods in it, `int add(int x, int y)`.


```java DemoUtils
package com.luv2code.junitdemo;

public class DemoUtils {
    public int add(int a, int b) {
        return a + b;
    }
}

```

  + How do we define the test?
  + How do we use JUnit Assertions?
  + How do we run the tests?



# The Laboratory: Getting Started Test

We have the code to test.


```java DemoUtils
package com.luv2code.junitdemo;

public class DemoUtils {
    public int add(int a, int b) {
        return a + b;
    }
}

``` 


## Step 1: Add Maven dependency for JUnit

So as to make use of JUnit in our project, we must add dependency for JUnit in the pom.xml file.


```xml
...

    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>5.8.2</version>
      <scope>test</scope>
    </dependency>

...

```


## Step 2: Create Test Package

+ The code we are testing is located in the application base package, and maybe downward.

+ A convention is to create test classes 
in similar package structure under `/test`.

Not a hard requirement, merely a convention: helps with organization of test classes, easy to find test classes when joining new projects, special edge cases for accessing protected class member.


## Step 3: Create Unit Test

Unit tests have the following structure:

  + Set Up: Create instance of the class to test.

  + Execute: Call the method you want to test.

  + Assert: Check the result and verify that it is the expected result.


```java
package org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import com.demo.DemoUtils;

class DemoUtilsTest {

    @Test
    void testEqualsAndNotEquals() {
        // setUp
        DemoUtils demoUtils = new DemoUtils();

        // execute
        int expected = 6;
        int actual = demoUtils.add(2, 4);

        // verify
        Assertions.assertEquals(expected, actual, "2 + 4 must be 6");
    }
}

```
