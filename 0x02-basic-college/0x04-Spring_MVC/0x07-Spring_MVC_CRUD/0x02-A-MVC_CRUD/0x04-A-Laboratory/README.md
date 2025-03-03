# Laboratory: 


```Java EmployeeController
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
    @GetMapping("/list")
    public String listEmployess(Model theModel) {

        // Get the list of employees from the database
        List<Employee> theEmployee = employeeService.findAll();

        // Add the list to the model
        theModel.addAttribute("employees", theEmployee);

        return "list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create an instance of employee
        Employee theEmployee = new Employee();

        // use the instance of the created employee in the model
        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String processEmployeeForm(@ModelAttribute("employee")
                                        Employee theEmployee) {
        // save the employee
        employeeService.save(theEmployee);

        // redirect the homepage
        return "redirect:/employees/list";
    }

}

```

```Html list-employees
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
                    </tr>
                </thead>

                <tbody>
                    <tr th:each="tempEmployee : ${employees}">

                        <td th:text="${tempEmployee.firstName}" />
                        <td th:text="${tempEmployee.lastName}" />
                        <td th:text="${tempEmployee.email}" />

                    </tr>
                </tbody>
            </table>

        </div>

    </body>
</html>

```

```html employee-form 
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Employee Form</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>

<div class="container">

    <h3>Employee Form</h3>
    <hr>

    <form action="#" th:action="@{/employees/save}" th:object="${employee}" method="POST">
        <input type="text" th:field="*{firstName}" placeholder="First name"
               class="form-control mb-4 w-25"/>

        <input type="text" th:field="*{lastName}" placeholder="Last name"
               class="form-control mb-4 w-25"/>

        <input type="text" th:field="*{email}" placeholder="Email"
               class="form-control mb-4 w-25"/>

        <button type="submit" class="btn btn-info col-2">Save</button>
    </form>

</div>
</body>
</html>
```

```Html static/index
<meta http-equiv="refresh"
      content="0; URL='/employees/list'">

```
