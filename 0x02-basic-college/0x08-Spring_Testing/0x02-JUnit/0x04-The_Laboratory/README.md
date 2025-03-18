# The Laboratory: JUnit Assertions


The class to test is:


```java
package com.luv2code.junitdemo;

public class DemoUtils {

	public Object checkNull(Object obj) {
 		
 		if (obj != null) {
 			return obj;
 		}
 		return null;
	}
}

```


## Step 1: The Test Package

We that in place


## Step 2: Create the Test

The test class is below.



```java
package com.opfgroup.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DemoUtilsTest {

    @Test
    public void testNullAndNotNull() {

        // Set up
        DemoUtils demoUtils = new DemoUtils();

        // Execute
        String str1 = null;
        String str2 = "FOLLY";

        // Assert
        Assertions.assertNotNull(demoUtils.checkNull(str2), "Must be null");
        Assertions.assertNull(demoUtils.checkNull(str1), "Must be null");
    }
}

```
