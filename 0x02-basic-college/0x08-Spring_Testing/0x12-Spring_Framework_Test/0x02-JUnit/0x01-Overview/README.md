# Overview

Manual code testing may follow this process:

  + Write the code
  + Run the code
  + Examine results.

This is great when we are testing small scale codes. It requires human interaction and analysis, test case is not reproducible, no way to automate the test. What if we want to run a battery of tests for each code commit, and also produce a report of test results? Unit testing is the answer.


## Unit Testing

Unit-testing of codes follow the process of:

  + Testing an individual unit of code for correctness.
  + Provide fixed inputs
  + Expect known output

Say we write the code for a method `int add(int x, int y`. We may create different test cases.

  + Input `add(5, 2)`, expected result = 7
  + Input `add(-3, -8)`, expected result = -11
  + Input `add(-3, 1)`, expected result = -2
  + Input `add(0, 0)`, expected result = 0

We may write another code, `String capitalise(String data)`. We may create test cases as:

  + Input: `capitalize("Luv2code")`, Output: LUV2CODE
  + Input: `capitalize("LUV2CODE")`, Output: LUV2CODE
  + Input: `capitalize("luv2code")`, Output: LUV2CODE
  + Input: `capitalize(null)`, Output: ???


### Benefits of Unit Testing

  + Automated tests
  + Better code design
  + Fewer bugs and higher reliability
  + Increases confidence for code refactoring ... did I break anything??
  + Basic requirement for
    - DevOps and Build Pipelines
    - Continuous Integration / Continuous Deployment (CI/CD)


## Integration Testing

  + Test multiple components together as part of a test plan
  + Determine if software units work together as expected
  + Identify any negative side effects due to integration
  + Can test using mocks / stubs
  + Can also test using live integrations (database, file system)


## Unit Testing Frameworks

Spring supports using JUnit and Mockito for unit and integration testing respectively.

  + JUnit
    - Supports creating test cases
    - Automation of the test cases with pass / fail
    - Utilities for test setup, teardown and assertions

  + Mockito
    - Create mocks and stubs
    - Minimize dependencies on external components
