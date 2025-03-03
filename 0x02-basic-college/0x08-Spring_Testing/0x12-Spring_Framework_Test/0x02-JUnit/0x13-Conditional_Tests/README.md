# Conditional Tests

We may not need to run all of the tests. There are various reasons we may not want to run tests.

  + Don't run a test because the method to test is broken, and we are waiting on dev team to fix it
  + A test should only run for a specific version of Java (Java 18) or range of versions (13 - 18)
  + A test should only run on a given operating system: MS Windows, Mac, Linux
  + A test should only run if specific environment variables or system properties are set

We could comment the code but then it won't be displayed in reports.

Conditional annotations can be applied on both class and method levels.


## @Disabled and @EnabledOnOs

The annotation `@Disabled` disables a test method, and `@EnabledOnOs` enables test when running on a given operating system.


```java
package conditional;

class ConditionalTest {

    @Test
    @Disabled("Don't run until JIRA #123 is resolved")
    void basicTest() {
        // ...
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void executeOnWindowsOnly() {
        // perform assertions
        // ...
    }

    @Test
    void executeMethodOnly() {
        // perform assertions
        // ...
    }

    @Test
    @EnabledOnOs(OS.MAC)
    void executeOnMacOnly() {
        // perform assertions
        // ...
   }

   @Test
   void executeMethodAndPerformAssertionsWindowsAndLinux() {
       // Execute method & perform assertions for Windows and Linux 
       // ...
   }
}

```


## @EnabledOnJre @EnabledForJreRange

`@EnabledOnJre` enables test for a given Java version `@EnabledForJreRange` enables test for a given Java version range.


```java
class ConditionalTest {

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void onlyOnJava8() {
        // execute method and perform assertions
    }

    @Test
    @DisabledOnJre(JRE.JAVA_17)
    void testOnlyForJava17() {
        // execute method and perform assertions
    }

    @Test
    @EnabledOnJre(JRE.JAVA_13)
    void testOnlyForJava13() {
        // execute method and perform assertions
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_11)
    void testFromJava8to11() {
        // execute method and perform assertions
   }

   @Test
   @DisabledForJreRange(min = JRE.JAVA_9, max = JRE.JAVA_14)
   void testExcludeJava9to14() {
       // execute method and perform assertions 
   }
}

```


## @EnabledIfSystemProperty @EnabledIfEnvironmentVariable

`@EnabledIfSystemProperty` enables test based on system property. `@EnabledIfEnvironmentVariable` enables test based on environment variable.


```java
class ConditionalTest {

    @EnabledIfSystemProperty(named="LV_CODE_SYS_PROP", matches="CI_CD_DEPLOY")
    void testOnlyOnSystemProperty() {
        // execute method and perform assertions
    }

    @EnabledIfEnvironmentVariable(named="ENV", matches="DEV")
    void testOnlyOnDevEnvironment() {
        // execute method and perform assertions
    }
}

```

Let's test some methods.
