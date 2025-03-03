# Delete Employee 


## Add the Delete Button to the Employee List Page 

```html list-employees 
...
        <tr th:each="tempEmployee : ${employees}">

            <td th:text="${tempEmployee.firstName}" />
            <td th:text="${tempEmployee.lastName}" />
            <td th:text="${tempEmployee.email}" />
            <td>
                <a th:href="@{/employees/showFormForUpdate(employeeId=${tempEmployee.Id})}"
                class="btn btn-info btn-sm">
                    Update
                </a>
                <a th:href="@{/employees/delete(employeeId=${tempEmployee.Id})}"
                   class="btn btn-danger btn-sm"
                   onclick="if (!(confirm('Are you sure you ant to delete this employee?'))) return false"> <!-- If not confirm -->
                    Delete
                </a>
            </td>

        </tr>
...

```


## Update the Employee Controller 

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
	...

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId,
                                    Model theModel) {
        // Delete the employee with the id theId
        employeeService.deleteById(theId);

        return "/employees/employee-form";
    }

}

```
