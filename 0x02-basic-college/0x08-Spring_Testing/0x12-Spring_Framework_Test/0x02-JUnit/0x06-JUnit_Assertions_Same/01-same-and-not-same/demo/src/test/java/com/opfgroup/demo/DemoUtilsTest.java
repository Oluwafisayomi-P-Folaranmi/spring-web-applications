package com.opfgroup.demo;

import org.junit.jupiter.api.*;

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
    @DisplayName("Tell if same or not")
    public void testSameAndNotSame() {

        String str1 = "Non-OPF Academy";

        // Execute and Assert
        Assertions.assertSame(demoUtils.getAcademy(),
                demoUtils.getDuplicateAcademy(),
                "They must be the same object references.");
        Assertions.assertNotSame(str1,
                demoUtils.getAcademy(),
                "They must be different object references.");
    }
}
