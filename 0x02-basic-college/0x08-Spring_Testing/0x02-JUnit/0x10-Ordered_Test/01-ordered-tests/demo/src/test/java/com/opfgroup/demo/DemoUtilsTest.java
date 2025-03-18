package com.opfgroup.demo;

import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.DisplayName.class)
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
    @DisplayName("Test if add() really adds.")
    public void testIfEqualOrNot() {

        //
        System.out.println("Inside testIfEqualOrNot()");
        // Values
        int expected = 9;

        // Execute and assert
        Assertions.assertEquals(expected, demoUtils.add(4, 5));
    }

    @Test
    @DisplayName("Test if the field reference the same object.")
    public void testIfNullOrNotNull() {

        //
        System.out.println("Inside testIfNullOrNotNull()");

        // Test two equal object references
        Assertions.assertSame(demoUtils.getAcademy(),
                demoUtils.getAcademyDuplicate(), "Must return true.");
    }

    @Test
    @DisplayName("Test if multiply() really multiplies.")
    public void testIfEqualOrNotEqual() {

        //
        System.out.println("Inside testIfEqualOrNotEqual()");
        int expected = 15;

        Assertions.assertEquals(expected,
                demoUtils.multiply(3, 5));
    }

    @Test
    @DisplayName("Test if two arrays are equal.")
    public void testIfTheArraysAreEqualOrNot() {

        //
        System.out.println("Inside testIfTheArraysAreEqualOrNot()");

        String[] exampleArray = {"A", "B", "C"};

        // Execute and assert
        Assertions.assertArrayEquals(exampleArray,
                demoUtils.getFirstThreeLettersOfAlphabet(),
                "Must return true.");
    }


    @Test
    @DisplayName("Test if two iterables are equal.")
    public void testIfTwoIterablesAreEqualOrNot() {

        List<String> exampleAcademyInList = List.of("luv", "2", "code");
        //
        System.out.println("Inside testIfTwoIterablesAreEqualOrNot()");

        Assertions.assertIterableEquals(exampleAcademyInList,
                demoUtils.getAcademyInList(),
                "Must return true.");
    }

    @Test
    @DisplayName("Test if the method will throw an exception.")
    public void testIfMethodWillThrowException() {

        //
        System.out.println("Inside testIfMethodWillThrowException()");

        Assertions.assertThrows(Exception.class,
                () -> { demoUtils.throwException(-7); },
                "Must return true.");
    }
}
