# Ordered Test

There are some uses cases when you want to control the order of tests:

  + You want tests to appear in alphabetical order for reporting purposes.
  + Sharing reports with project management, QA team etc.
  + Group tests based on functionality or requirements

The `@TestMethodOrderer` configures the order/sort algorithm for the test methods. Then specify method order by:

  + MethodOrderer.DisplayName, sorts test methods alphanumerically based on display names.
  + MethodOrderer.MethodName, sorts test methods alphanumerically based on method names.
  + MethodOrderer.Random Pseudo-random order based on method names.
  + MethodOrderer.OrderAnnotation Sorts test methods numerically based on @Order annotation.


## `@Order`

The `@Order` works under `@TestMethodOrder(MethodOrderer.OrderAnnotation.class)` to manually specify the order with an int number
  - Order with lowest number has highest priority
  - Negative numbers are allowed

