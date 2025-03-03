package com.opfgroup.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DemoUtilsTest {

    DemoUtils demoUtils;

    @BeforeEach
    public void doBeforeEachTest() {

        System.out.println("Executing @BeforeEach before each test.");
        // Set up
        demoUtils = new DemoUtils();
    }

    @AfterEach
    public void doAfterEachTest() {

        System.out.println("Executing @AfterEach." + '\n');
    }

    @Test
    void testEqualsAndNotEquals() {

        System.out.println("Running test: testEqualsAndNotEquals");

        // execute
        int expected = 6;
        int actual = demoUtils.add(2, 4);

        // verify
        Assertions.assertEquals(expected, actual, "2 + 4 must be 6");
    }

    @Test
    public void testNullAndNotNull() {

        System.out.println("Running test: testNullAndNotNull");

        // Execute
        String str1 = null;
        String str2 = "FOLLY";

        // Assert
        Assertions.assertNotNull(demoUtils.checkNull(str2), "Must be null");
        Assertions.assertNull(demoUtils.checkNull(str1), "Must be null");
    }
}
