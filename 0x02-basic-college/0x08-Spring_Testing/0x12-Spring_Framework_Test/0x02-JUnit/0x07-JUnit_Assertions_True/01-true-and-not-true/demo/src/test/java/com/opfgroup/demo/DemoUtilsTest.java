package com.opfgroup.demo;

import org.junit.jupiter.api.*;

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
    @DisplayName("Tell if same or not")
    public void testTrueAndNotTrue() {

        // Values
        int num1 = 3;
        int num2 = 2;

        // Execute and assert
        Assertions.assertTrue(demoUtils.isGreater(num1, num2), "This should return true.");
        Assertions.assertFalse(demoUtils.isGreater(num2, num1), "This should return false.");
    }
}
