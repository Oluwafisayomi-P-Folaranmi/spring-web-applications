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
}
