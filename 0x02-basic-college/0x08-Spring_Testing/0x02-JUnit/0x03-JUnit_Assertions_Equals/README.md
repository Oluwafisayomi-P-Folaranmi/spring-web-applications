# JUnit Assertions

JUnit has a collection of assertions. Defined in class: `org.junit.jupiter.api.Assertions`

  + assertEquals
    - Assert that the values are equal

  + assertNotEquals
    - Assert that the values are not equal

  + assertNull
    - Assert that the value is null

  + assertNotNull
    - Assert that the value is not null


## assertEquals and assertNotEquals

Below is the method `assertEquals`.


```java
Assertions.assertEquals(expected, actual, "2+4 must be 6");

```

`expected` is the expected value, `actual` is the actual value after executing method under test. The `"2+4 must be 6"` is the optional message if test fails.

Below is the method `assertNotEquals`.


```java
Assertions.assertEquals(unexpected, actual, "2+4 must be 6");

```

`unexpected` is the unexpected value, `actual` is the actual value after executing method under test. The `"2+4 must be 6"` is the optional message if test fails. The test class may look as thus.


```java
package com.luv2code.junit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class DemoUtilsTest {

  @Test
  void testEqualsAndNotEquals() {
    // setup
    DemoUtils demoUtils = new DemoUtils();
    int expected = 6;
    int unexpected = 8;
    
    // execute
    int actual = demoUtils.add(2, 4);
    
    // assertions
    Assertions.assertEquals(expected, actual, "2+4 must be 6");
    Assertions.assertNotEquals(unexpected, actual, "2+4 must not be 8");
  }
}

```


## Assertions: Null and NotNull

  + assertNull
    - Assert that the value is null
  
  + assertNotNull 
    - Assert that the value is not null
