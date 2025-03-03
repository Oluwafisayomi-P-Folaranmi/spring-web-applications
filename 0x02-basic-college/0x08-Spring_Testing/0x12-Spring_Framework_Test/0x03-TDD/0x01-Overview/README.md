# Overview

Traditional/convectional development generically goes through: design, code, and test.

An alternative and better way to write code is the Test Driven Development which goes through: test, code, and refactor, then repeat the process.

We will first write a failing test; then we will write our code to make the test pass; then we will refactor and improve the design, then repeat the process for the next test.


## Benefits

  + Clear task list of things to test and develop.
  + Tests will help you identify edge cases.
  + Develop code in small increments.
  + Passing tests increases confidence in code.
  + Gives freedom to refactor ... tests are your safety net ... did I break anything??

We will apply the concepts of testing that we've learnt so far on the FizzBuzz project using Test Driven Development.



# Laboratory


## Step 1: Write a failing test

Say, or assume we have a class FizzBuzz with a method that does our fizzbuzz method that we want to test.


```java
public class FizzBuzz {

    public static String compute(int i) {

      // If number is divisible by 3, print Fizz
      // If number is divisible by 5, print Buzz
      // If number is divisible by 3 and 5, print FizzBuzz
      // If number is not divisible by 3 or 5, then print the number

      ...

    }
}

```

Let's write a failing test first. To make our work easier, we can add the failing method, `void fail(String string)` to make the test fail.

Using TDD we will code, the test methods sequentially according to the `@Order` annotation.


### Test `compute()` to take 3

The flow is to write the test first, then the following step is to write the code to make the test pass.


```java
public class FizzBuzzTest {
    // Test for: If number is divisible by 3, print Fizz
    @Test
    @DisplayName("Divisible by three.")
    @Order(1)
    public void testForDivisibleByThree() {

        // fail("fail.");
        String expected = "Fizz";

        Assertions.assertEquals(expected,
                FizzBuzz.compute(3),
                "Must return Fizz.");
    }
}

```

If we run this, it will not pass, because we don't have any algorithm in the FizzBuzz class's `compute` method. Now, let's write the code to make the test pass.


```java
package com.opfgroup.demo.tdd;

public class FizzBuzz {

    // If number is divisible by 3, print Fizz
    // If number is divisible by 5, print Buzz
    // If number is divisible by 3 and 5, print FizzBuzz
    // If number is not divisible by 3 or 5, then print the number

    public static String compute(int i) {

        if (i % 3 == 0) {

            return "Fizz";
        }
        return null;
    }
}

```

We did the "test-code" for when the argument passed into `compute` is 3. We can try the other arguments. For the other cases, we have the FizzBuzzTest as below followed by the complete implementation of the FizzBuzz class.


```java FizzBuzzTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FizzBuzzTest {

    // Test for: If number is divisible by 3, print Fizz
    @Test
    @DisplayName("Divisible by three.")
    @Order(1)
    public void testForDivisibleByThree() {

        // fail("fail.");
        String expected = "Fizz";

        Assertions.assertEquals(expected,
                FizzBuzz.compute(3),
                "Must return Fizz.");
    }

    // Test for: If number is divisible by 5, print Buzz
    @Test
    @DisplayName("Divisible by five.")
    @Order(2)
    public void testForDivisibleByFive() {

        // fail("fail.");
        String expected = "Buzz";

        Assertions.assertEquals(expected,
                FizzBuzz.compute(5),
                "Must return Buzz.");
    }

    // Test for: If number is divisible by 3 and 5, print FizzBuzz
    @Test
    @DisplayName("Divisible by three and five.")
    @Order(3)
    public void testForDivisibleByThreeAndFive() {

        // fail("fail.");
        String expected = "FizzBuzz";

        Assertions.assertEquals(expected,
                FizzBuzz.compute(30),
                "Must return FizzBuzz.");
    }

    // If number is not divisible by 3 or 5, then print the number
    @Test
    @DisplayName("Not divisible by three or five.")
    @Order(4)
    public void testForNotDivisibleByThreeOrFive() {

        // compute number
        int computeNumber = 1;

        // fail("fail.");
        String expected = Integer.toString(computeNumber);

        Assertions.assertEquals(expected,
                FizzBuzz.compute(computeNumber),
                "Must return the input number as a String object.");
    }
}

```

Below is the complete implementation of the FizzBuzz class.


```java
package com.opfgroup.demo.tdd;

public class FizzBuzz {

    // If number is divisible by 3, print Fizz
    // If number is divisible by 5, print Buzz
    // If number is divisible by 3 and 5, print FizzBuzz
    // If number is not divisible by 3 or 5, then print the number

    public static String compute(int i) {

        if ((i % 3 == 0) && (i % 5 == 0)) {

            return "FizzBuzz";
        }
        else if (i % 3 == 0) {

            return "Fizz";
        }
        else if (i % 5 == 0) {

            return "Buzz";
        }
        else {

            return Integer.toString(i);
        }
    }
}

```


## Step 2: Refactoring

We saw the way we can write failing tests first, writing code to pass, and we kind of repeated that process over and over again.

One of the benefits of Test-Driven Development is that when we pass tests, it increases confidence in our code. It also gives us the freedom to refactor because the tests are our safety net.

So let’s go ahead and do that now. I’ll provide an alternate implementation here for this method and make sure that it still works.


```java
package com.opfgroup.demo.tdd;

import java.net.Inet4Address;

public class FizzBuzz {

    // If number is divisible by 3, print Fizz
    // If number is divisible by 5, print Buzz
    // If number is divisible by 3 and 5, print FizzBuzz
    // If number is not divisible by 3 or 5, then print the number

    public static String compute(int i) {

        StringBuilder result = new StringBuilder();

        if ((i % 3 == 0) && (i % 5) == 0) {

            result.append("FizzBuzz");
        }
        else if (i % 3 == 0) {

            result.append("Fizz");
        }
        else if (i % 5 == 0){

            result.append("Buzz");
        }
        else {

            result.append(i);
        }
        return result.toString();
    }

    /*
    public static String compute(int i) {

        if ((i % 3 == 0) && (i % 5) == 0) {

            return "FizzBuzz";
        }
        else if (i % 3 == 0) {

            return "Fizz";
        }
        else if (i % 5 == 0){

            return "Buzz";
        }
        else {

            return Integer.toString(i);
        }
    }
     */
}

```
