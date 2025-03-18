package com.opfgroup.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
