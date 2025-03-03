# Laboratory: Form Tags 


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

    First Name: <input type="text" th:field="${student.firstName}" placeholder="Enter your first name" />

    <br><br>

    Second Name: <input type="text" th:field="${student.secondName}" placeholder="Enter your second name" />

    <br><br>

    Country: 
    <select th:field="${student.country}" >
        <option th:value="Nigeria">Nigeria</option>
        <option th:value="Togo">Togo</option>
        <option th:value="Ghana">Ghana</option>
        <option th:value="Kenya">Kenya</option>
    </select>

    <br><br>

    Programming Language:
    <input type="radio" th:field="${student.favouriteLanguage}" th:value="Go">Go</input>
    <input type="radio" th:field="${student.favouriteLanguage}" th:value="Java">Java</input>
    <input type="radio" th:field="${student.favouriteLanguage}" th:value="Python">Python</input>

    <br><br>

    Favorite Operating Systems:

    <input type="checkbox" th:field="*{favoriteSystems}" th:value="Linux">Linux</input>
    <input type="checkbox" th:field="*{favoriteSystems}" th:value="macOS">macOS</input>
    <input type="checkbox" th:field="*{favoriteSystems}"
                           th:value="'Microsoft Windows'">Microsoft Windows</input>

    <br><br>

    <input type="submit" />

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

        Student name is: <span th:text="${student.firstName} + ' ' + ${student.secondName}" /> 

        <br><br>

        Country is: <span th:text="${student.country} + '.'" />

        <br><br>

        Your favourite programming language is: <span th:text="${student.favouriteLanguage} + '.'" /> 

        <br><br>

        Favorite Operating Systems:
        <ul>
            <li th:each="tempSystem : ${student.favoriteSystems}" th:text="${tempSystem}" />
        </ul>


    </body>

</html>

```
