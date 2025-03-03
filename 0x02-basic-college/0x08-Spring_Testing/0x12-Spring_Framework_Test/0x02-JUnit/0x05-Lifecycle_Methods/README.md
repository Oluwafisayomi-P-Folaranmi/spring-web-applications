# Lifecycle Methods


## `@BeforeEach` and `@AfterEach`

When developing tests, we may need to perform common operations:

  + Before each test
    - Create objects, set up test data

  + After each test
    - Release resources, clean up test data.

From before, our test class is:


```java
package com.luv2code.junit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DemoUtilsTest {

    @Test
    void testEqualsAndHashCode() {
        // set up
        DemoUtils demoUtils = new DemoUtils();
        assertNotEquals(5, demoUtils.add(2, 2), "4 must not be 5");
        assertEquals(6, demoUtils.add(1, 2), "1+2 must be 6");

        // tearDown
    }

    @Test
    void testNotNullAndNull() {
        // set up
        String str1 = null;
        DemoUtils demoUtils = new DemoUtils();

        // test not null
        assertNotNull(demoUtils.checkNull(str1), "Object should not be null");
        
        // tearDown
    }
}

```

Notice that for the set up of each test method, we always create a new object, `demoUtils`. We can drop down on this and create the object once. We can do this by an `@BeforeEach` annotated method.


```java
@BeforeEach
void setupBeforeEach() {

 // set up
 demoUtils = new DemoUtils();
 System.out.println("@BeforeEach executes before the execution of each test method");
}

```

A complement method to clear things up after is an `@AfterEach` annotated method.


```java
@AfterEach
void tearDownAfterEach() {

 System.out.println("Running @AfterEach\n");
}
```

Consequently, the new class will look as thus:


```java
package com.luv2code.junitdemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

class DemoUtilsTest {

    DemoUtils demoUtils;

    @BeforeEach
    void setupBeforeEach() {
        // set up
        demoUtils = new DemoUtils();
        System.out.println("@BeforeEach executes before the execution of each test method");
    }

    @AfterEach
    void tearDownAfterEach() {
        System.out.println("Running @AfterEach");
    }

    @Test
    void testEqualsAndNotEquals() {

    	System.out.println("Running test: testEqualsAndNotEquals");

        // execute and assert
        assertEquals(6, demoUtils.add(2, 4), "2+4 must be 6");
        assertNotEquals(8, demoUtils.add(1, 9), "1+9 must not be 8");
    }

    @Test
    void testNullAndNotNull() {

    	System.out.println("Running test: testNullAndNotNull");

        String str1 = null;
        String str2 = "luv2code";
        assertNull(demoUtils.checkNull(str1), "Object should be null");
        assertNotNull(demoUtils.checkNull(str2), "Object should not be null");
    }
}

```

The execution sequence for the test is:

  + @BeforeEach: This method runs before each test method. It’s used to set up any necessary preconditions or initial configurations.
  
  + @Test Method One: The first test method runs, performing specific assertions to verify the code’s behavior.

  + @AfterEach: This method runs after each test method to clean up or reset any changes made during the test.

  + @BeforeEach: The setup method runs again before the next test.

  + @Test Method Two: The second test method runs, performing its own assertions.

  + @AfterEach: The cleanup method runs again after the second test.


## `@BeforeAll` and `@AfterAll`

When developing tests, we may need to perform one-time operations.

+ One-time set up before all tests
  - Get database connections, connect to remote servers ...

+ One-time clean up after all tests
  - Release database connections, disconnect from remote servers ..

A method annotated with `@BeforeAll` is executed only once, before all test methods. Useful for getting database connections, connecting to servers.

A `@AfterAll` method is executed only once, after all test methods. Useful for releasing database connections, disconnecting from servers.

## Custom Display Names

We can make our test mmethods display descriptive names that include spaces and special descriptive characters. It is useful for sharing test reports with project management and non-techies.

The `@DisplayName` annotation create custom display name with spaces, special characters and emojis.
Useful for test reports in IDE or external test runner.

## Display Name Generators

JUnit can generate display names for you. The `@DisplayNameGeneration` generates names for you depending on the `DisplayNameGenerator` value.

  + Simple: Removes trailing parentheses from test method name.


```java
package com.luv2code.junitdemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
class DemoUtilsTest {

    DemoUtils demoUtils;

    @BeforeEach
    void setupBeforeEach() {
        // set up
        demoUtils = new DemoUtils();
    }

    @Test
    void testNullAndNotNull() {
        String str1 = null;
        String str2 = "luv2code";
        assertNull(demoUtils.checkNull(str1), "Object should be null");
        assertNotNull(demoUtils.checkNull(str2), "Object should not be null");
    }

    @Test
    void testEqualsAndNotEquals() {
        // execute and assert
        assertEquals(6, demoUtils.add(2, 4), "2+4 must be 6");
        assertNotEquals(8, demoUtils.add(1, 9), "1+9 must not be 8");
    }
}

```

  + ReplaceUnderscores: Replaces underscores in test method name with spaces.


```java
package com.luv2code.junitdemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DemoUtilsTest {

    DemoUtils demoUtils;

    @BeforeEach
    void setupBeforeEach() {
        // set up
        demoUtils = new DemoUtils();
    }

    @AfterEach
    void tearDownAfterEach() {
        // teardown
    }

    @Test
    void test_equals_and_not_equals() {
        assertEquals(6, demoUtils.add(2, 4), "2 + 4 must be 6");
        assertNotEquals(8, demoUtils.add(2, 4), "2 + 4 must not be 8");
    }

    @Test
    void test_not_null_and_null() {
        String str1 = null;
        String str2 = "luv2code";
        assertNull(demoUtils.checkNull(str1), "Object should be null");
        assertNotNull(demoUtils.checkNull(str2), "Object should not be null");
    }
}

```

  + IndicativeSentences: Generate sentence based on test class name and test method name.


```java
package com.luvaldo.demo.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DemoUtilsTest {

    @BeforeEach
    void setUp(TestInfo testInfo) {
        // set up
    }

    @Test
    void doesNotThrowAnException() {
        // tests
    }

    @Test
    void demoUtils_Not_Null() {
        String str1 = "object should not be null";
        String str2 = "object should not be null";

        assertNotNull(demoUtils.checkNull(str1), "Object should not be null");
        assertNotNull(demoUtils.checkNull(str2), "Object should not be null");
    }

    @Test
    void add_Two_Plus_Two_Equals_Four() {
        assertEquals(4, demoUtils.add(2, 2), "2+2 must be 4");
        assertNotEquals(6, demoUtils.add(2, 4), "2+4 must not be 6");
        
        // assertEquals and assertNotEquals examples
        assertNotEquals(8, demoUtils.add(4, 9), "4+9 must not be 8");
    }
}

```
