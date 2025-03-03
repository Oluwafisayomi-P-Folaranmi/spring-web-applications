# Update Employee 

For every employee listed on the employee page, we will have an **Update** link that will be used to update the data of the employee. Each link will have the current employee's ID embedded. When clicked, it will load the data from the database and pre-populate the form. 


## Update Employee List Form 

Add the **Update** button/link to the list of employees. 

```html list-employees
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Employee Directory</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>

<div class="container">

    <h3>Employee Directory</h3>
    <hr>

    <!-- Add button -->
    <a th:href="@{/employees/showFormForAdd}"
       class="btn btn-primary btn-sm mb-3">
        Add Employee
    </a>

    <table class="table table-bordered table-striped">
        <thead class="table-dark">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Update</th>
        </tr>
        </thead>

        <tbody>
        <tr th:each="tempEmployee : ${employees}">

            <td th:text="${tempEmployee.firstName}" />
            <td th:text="${tempEmployee.lastName}" />
            <td th:text="${tempEmployee.email}" />
            <td>
                <a th:href="@{/employees/showFormForUpdate(employeeId=${tempEmployee.Id})"
                class="btn btn-info btn-sm">
                    Update
                </a>
            </td>

        </tr>
        </tbody>
    </table>

</div>
</body>
</html>

```


## Update the Controller 

```java EmployeeController 
package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.luv2code.springboot.cruddemo.service.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    ... 

    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId,
                                    Model theModel) {
        // Get the employee to be updated
        Employee theEmployee = employeeService.findById(theId);

        // Bind the employee to the model
        theModel.addAttribute("employee", theEmployee);

        return "/employees/employee-form";
    }

}

```


## Update the Form 

The controller will load the data from the database and pre-populate the form. We will need an hidden link inside the form which will be responsible for updating the employees. This is strictly required for doing updates. This form field binds to the model-attribute and tells your app which form to update. 

```html employee-form 
    ...
    
    <form action="#" th:action="@{/employees/save}" th:object="${employee}" method="POST"> 
    	<input /> 

        <!-- Add hidden form field to handle update -->
        <input type="hidden" th:field="*{id}" />

        <input type="text" th:field="*{firstName}" placeholder="First name"
               class="form-control mb-4 w-25"/>

        <input type="text" th:field="*{lastName}" placeholder="Last name"
               class="form-control mb-4 w-25"/>

        <input type="text" th:field="*{email}" placeholder="Email"
               class="form-control mb-4 w-25"/>

        <button type="submit" class="btn btn-info col-2">Save</button>
    </form>

```
