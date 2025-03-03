# Laboratory: Form Tags using Properties File 


```Java StudentController.java
package com.opf.springboot.demo.controller;

import com.opf.springboot.demo.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    @GetMapping("/showStudentForm")
    public String showStudentForm(Model studentModel) {

        Student theStudent = new Student();
        studentModel.addAttribute("student", theStudent);

        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processStudentForm(@ModelAttribute("student") Student theStudent) {

        return "student-confirmation";

    }
}

```

```application.properties
countries=Brazil,France,Germany,India,Mexico,Spain,United States
languages=Go,Java,Python,Rust,TypeScript
systems=Linux,macOS,Microsoft Windows,Android OS,iOS 

```

```Html student-form
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <title>Student Input Form</title>
</head>
<body>

<h3>Student Registration</h3>

<form th:action="@{/processStudentForm}" th:object="${student}" method="POST">

    First name: <input type="text" th:field="*{firstName}" />

    <br><br>

    Last name: <input type="text" th:field="*{lastName}" />

    <br><br>

    Country:
    <select th:field="*{country}">

        <option th:each="tempCountry : ${countries}" th:value="${tempCountry}" th:text="${tempCountry}" />

    </select>

    <br><br>

    Favorite Programming Language:
    <input type="radio" th:field="*{favoriteLanguage}"
                        th:each="tempLang : ${languages}"
                        th:value="${tempLang}"
                        th:text="${tempLang}" />

    <br><br>

    Favorite Operating Systems:
    <input type="checkbox" th:field="*{favoriteSystems}"
                           th:each="tempSystem : ${systems}"
                           th:value="${tempSystem}"
                           th:text="${tempSystem}" />

    <br><br>

    <input type="submit" value="Submit" />

</form>

</body>
</html>

```

```Html student-confirmation
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">

    <head>
        <title>Thymeleaf Demo</title>
    </head>

    <body>

        <h3>Student Confirmation</h3>

        <br><br>

        The student is confirmed: <span th:text="${student.firstName} + ' ' + ${student.lastName}" />

        <br><br>

        Country: <span th:text="${student.country}" />

        <br><br>

        Favorite Programming Language: <span th:text="${student.favoriteLanguage}" />

        <br><br>

        Favorite Operating Systems:
        <ul>
            <li th:each="tempSystem : ${student.favoriteSystems}" th:text="${tempSystem}" />
        </ul>


    </body>

</html>

```
