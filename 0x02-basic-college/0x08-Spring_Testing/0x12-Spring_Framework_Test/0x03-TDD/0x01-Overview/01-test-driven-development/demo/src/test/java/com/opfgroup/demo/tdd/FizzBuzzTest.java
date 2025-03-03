package com.opfgroup.demo.tdd;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FizzBuzzTest {

    // Test for: If number is divisible by 3, print Fizz
    @Test
    @DisplayName("Divisible by three.")
    @Order(1)
    public void testForDivisibleByThree() {

        // fail("fail.");
        String expected = "Fizz";
        // Execute and assert
        Assertions.assertEquals(expected,
                FizzBuzz.compute(3),
                "Must return Fizz.");

    }

    // Test for: If number is divisible by 5, print Buzz
    @Test
    @DisplayName("Divisible by five.")
    @Order(2)
    public void testForDivisibleByFive() {

        String expected = "Buzz";
        // Execute and assert
        Assertions.assertEquals(expected,
                FizzBuzz.compute(5),
                "Must return Buzz.");
    }

    // If number is divisible by 3 and 5, print FizzBuzz
    @Test
    @DisplayName("Divisible by three and five.")
    @Order(3)
    public void testForDivisibleByThreeAndFive() {

        String expected = "FizzBuzz";
        // Execute and assert
        Assertions.assertEquals(expected,
                FizzBuzz.compute(15),
                "Must return FizzBuzz.");
    }

    // If number is not divisible by 3 or 5, then print the number
    @Test
    @DisplayName("Not divisible by three or five.")
    @Order(4)
    public void testForNotDivisibleByThreeOrFive() {

        // object passed
        int objectPassed = 1;

        String expected = Integer.toString(objectPassed);
        // Execute and assert
        Assertions.assertEquals(expected,
                FizzBuzz.compute(1),
                "Must return the String of the object passed to it.");
    }
}
