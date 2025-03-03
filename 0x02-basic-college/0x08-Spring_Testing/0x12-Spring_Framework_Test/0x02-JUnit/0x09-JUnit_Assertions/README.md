# JUnit Assertions

The `assetThrows` assert that an executable throws an exception of expected type.

The class code to test is:


```java
package com.luv2code.junitdemo;

public class DemoUtils {

    public String throwException(int a) throws Exception {
        if (a < 0) {
            throw new Exception("Value should be greater than or equal to 0");
        }
        return "Value is greater than or equal to 0";
    }
}

```

We can test the class code with the following:


```java
    public void testThrowsExceptionOrNot() {

        // Entering message
        System.out.println("Inside the testThrowsExceptionOrNot()");

        // Execute and assert
        Assertions.assertThrows(Exception.class, () -> { demoUtils.throwException(-7); }, "hdd");
    }

```


## assertTimeoutPreemptively

Asserts that an executable completes before given timeout is exceeded.

We can test the following class:


```java
package com.luv2code.utils;

public class DemoUtils {

    public void checkTimeout() throws InterruptedException {
        System.out.println("I am going to sleep");
        Thread.sleep(2000);
        System.out.println("Sleeping over");
    }
}

```

To run our test, we can use the method below.


```java
package com.luv2code.junitdemo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;

class DemoUtilsTest {

    DemoUtils demoUtils;

    @DisplayName("timeout")
    @Test
    void testTimeout() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            demoUtils.checkTimeout();
        }, "Method should execute in 3 seconds");
    }
}

```

The `Duration.ofSeconds(3)` is the Timeout duration.
