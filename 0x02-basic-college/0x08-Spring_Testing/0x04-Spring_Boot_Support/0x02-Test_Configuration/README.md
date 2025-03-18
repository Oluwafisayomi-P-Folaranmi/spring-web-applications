# `@SpringBootTest` configuration

Place your test class in test package same as your main package. This implicitly defines a base search

  + Allows you to leverage default configuration.
  + No need to explicitly reference the main Spring Boot application class

A sample project created by a previous employee doesn't have any tests. We've been tasked with developing the unit tests.

Here we have a project for a school. There are two beans (classes): `StudentGrades` and `CollegeStudent`. The `CollegeStudent` class uses the `StudentGrades` class to manage the student’s grades and provides methods to get and set these grades. The `studentInformation` method combines the student’s full name and email address into a single string.


## Step 1: `assertNotEquals`

A use-case of the `assertNotEquals` here is to check/test/ensures that the sum of the student’s math grades is not zero, which could be useful for validating that the grades have been correctly recorded and are not all zeros. If the sum is zero, it might indicate an issue with the grade data.

At first we can print out some information about the application being tested, including its name (appInfo), description (appDescription), version (appVersion), and the current test execution count.

Set up the test data for the student object. Set the first name, last name, and email address of the student, and initializes the studentGrades object with a list of math grades. This ensures that each test method starts with a consistent and known state. By the time the test method runs, the student and studentGrades objects are already initialized with the specified values.


In the `addGradeResultsForStudentGradesAssertNotEquals` method, the `studentGrades.addGradeResultsForSingleClass` method is called with the list of math grades that were set up in the `beforeEach` method. The test then asserts that the sum of these grades is not equal to zero. It is almost like an higer level confirmation that the student grade is not empty.


```java
@SpringBootTest(classes= MvcTestingExampleApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApplicationExampleTest {

    private static int count = 0;

    @Value("${info.app.name}")
    private String appInfo;

    @Value("${info.app.description}")
    private String appDescription;

    @Value("${info.app.version}")
    private String appVersion;

    @Value("${info.school.name}")
    private String schoolName;

    @Autowired
    CollegeStudent student;

    @Autowired
    StudentGrades studentGrades;

    @Autowired
    ApplicationContext context;

    @BeforeEach
    public void beforeEach() {
        count = count + 1;
        System.out.println("Testing: " + appInfo + " which is " + appDescription +
                "  Version: " + appVersion + ". Execution of test method " + count);
        student.setFirstname("Eric");
        student.setLastname("Roby");
        student.setEmailAddress("eric.roby@luv2code_school.com");
        studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(100.0, 85.0, 76.50, 91.75)));
        student.setStudentGrades(studentGrades);
    }

    @DisplayName("Add grade results for student grades not equal")
    @Test
    @Order(1)
    public void addGradeResultsForStudentGradesAssertNotEquals() {
        assertNotEquals(0, studentGrades.addGradeResultsForSingleClass(
                student.getStudentGrades().getMathGradeResults()
        ));
    }

}

```


## Step 2: `assertTrue` and `assertFalse`

Here, we can check if the `StudentGrade`'s `isGreater` works well.


```java
@SpringBootTest(classes= MvcTestingExampleApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApplicationExampleTest {

	...

    @DisplayName("Is grade greater")
    @Test
    @Order(2)
    public void isGradeGreaterThanStudentsGrade() {
        Assertions.assertTrue(studentGrades.isGradeGreater(90, 80),
                "Must return true.");
    }

	@DisplayName("Is grade greater false")
    @Test
    public void isGradeGreaterStudentGradesAssertFalse() {
        assertFalse(studentGrades.isGradeGreater(89, 92),
                "failure - should be false");
    }
}

```


## Step 3: `assertNotNull`

We can verify if the student has grades.


```java
@SpringBootTest(classes= MvcTestingExampleApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApplicationExampleTest {

	...

    @DisplayName("Check Null for student grades")
    @Test
    @Order(4)
    public void testIfGradeHasObject() {

        Assertions.assertNotNull(student.getStudentGrades().getMathGradeResults(),
                "Must not be empty.");
    }
}

```


Let's test to create a new object reference. The purpose of this test is to verify that a CollegeStudent object can be created without initializing its grades and that the properties (first name, last name, and email address) are correctly set and not null. Additionally, it checks that the studentGrades property is null, confirming that the student was created without any grades initialized.


```java
@SpringBootTest(classes= MvcTestingExampleApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApplicationExampleTest {

	...

	@DisplayName("Create student without grade init")
    @Test
    @Order(5)
    public void createStudentWithoutGradesInit() {
        CollegeStudent studentTwo = context.getBean("collegeStudent", CollegeStudent.class);
        studentTwo.setFirstname("Oluwafisayomi");
        studentTwo.setLastname("Folaranmi");
        studentTwo.setEmailAddress("folaranmifisayo@gmail.com");
        Assertions.assertNotNull(studentTwo.getFirstname(), "Must have a first name, since we set a first name.");
        Assertions.assertNotNull(studentTwo.getLastname(), "Must have a last name, since we set a last name.");
        Assertions.assertNotNull(studentTwo.getEmailAddress(), "Must have an email address, since we set an email address.");
        Assertions.assertNull(studentTwo.getStudentGrades(), "Must not have a student grade because we didn't set one.");
    }
}

```

This test ensures that the CollegeStudent bean can be properly instantiated and its properties can be set as expected, even when the grades are not initialized.


## Step 4: `assertNotSame`

We want to test if two object references generated from the Application Context are different. We can use the `assertNotSame` to verify.


```java
@SpringBootTest(classes= MvcTestingExampleApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApplicationExampleTest {

	...

    @DisplayName("Verify students are prototypes")
    @Test
    @Order(6)
    public void verifyStudentsArePrototypes() {
        CollegeStudent studentTwo = context.getBean("collegeStudent", CollegeStudent.class);
        Assertions.assertNotSame(student, studentTwo, "Returns true. They must not be the same.");

    }
}

```


## Step 5: `assertAll`

Here, we want to test the functionalities related to student grades.

`assertAll`: This method allows you to group multiple assertions together. If any assertion fails, the test will fail, but it will still execute all assertions and report all failures.


```java
@SpringBootTest(classes= MvcTestingExampleApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApplicationExampleTest {

	...

    @DisplayName("Find Grade Point Average")
    @Test
    public void findGradePointAverage() {
        assertAll("Testing all assertEquals",
                ()-> assertEquals(353.25, studentGrades.addGradeResultsForSingleClass(
                        student.getStudentGrades().getMathGradeResults())),
                ()-> assertEquals(88.31, studentGrades.findGradePointAverage(
                        student.getStudentGrades().getMathGradeResults()))
        );
    }
}

```

`assertEquals(353.25...)` checks if `studentGrades.addGradeResultsForSingleClass` method is equal to 353.25. `assertEquals(88.31...)` checks  if the result of studentGrades.findGradePointAverage method is equal to 88.31.
