package com.opfgroup.demo;

import org.junit.jupiter.api.*;

import java.util.List;

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
    @DisplayName("Tell if arrays are equal or not")
    public void testArraysEqualOrNot() {

        // The array
        String[] stringArray = {"A", "B", "C"};

        // Execute and assert
        Assertions.assertArrayEquals(demoUtils.getFirstThreeLettersOfAlphabet(), stringArray, "Must return true.");
    }

    @Test
    @DisplayName("Tell if iterables are equal or not")
    public void testIterablesEqualOrNot() {

        // The array
        List<String> stringList = List.of("luv", "2", "code");

        // Execute and assert
        Assertions.assertIterableEquals(demoUtils.getAcademyInList(), stringList, "Must return true.");
    }
}
