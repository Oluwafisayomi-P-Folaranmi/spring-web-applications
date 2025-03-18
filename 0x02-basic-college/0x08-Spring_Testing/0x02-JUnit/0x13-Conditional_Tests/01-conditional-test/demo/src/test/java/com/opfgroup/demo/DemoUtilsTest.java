package com.opfgroup.demo;

import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    @DisplayName("Test if the add() methods actually adds.")
    public void testIfEqualOrNotEqual() {

        //
        System.out.println("Inside the testIfEqualOrNotEqual()");
        // values
        int expected = 8;
        int actual = demoUtils.add(4, 4);

        // assert
        Assertions.assertEquals(expected, actual,
                "Must return true.");
    }

}
