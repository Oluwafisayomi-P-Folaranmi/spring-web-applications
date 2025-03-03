# JUnit Assertions


## assertArrayEquals

There is an assertion type to check if two array objects are deeply equal. The asssert type is `assertArrayEquals`.

Let's test the class:


```java
package com.luv2code.junitdemo;

public class DemoUtils {

    private String[] firstThreeLettersOfAlphabet = {"A", "B", "C"};

    public String[] getFirstThreeLettersOfAlphabet() {
        return firstThreeLettersOfAlphabet;
    }
}

```


## assertIterableEquals

`assertIterableEquals` asserts that both object iterables are deeply equal.

An "iterable" is an instance of a class that implements the java.lang.Iterable interface. Examples: ArrayList, LinkedList, HashSet, TreeSet.



```java
package com.luv2code.junitdemo;

import java.util.List;

public class DemoUtils {

    private List<String> academyInList = List.of("luv", "2", "code");

    public List<String> getAcademyInList() {
        return academyInList;
    }
}

```
