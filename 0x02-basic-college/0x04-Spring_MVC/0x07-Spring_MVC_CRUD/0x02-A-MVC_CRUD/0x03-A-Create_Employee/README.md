# Create Employees 


We want to present the names in the ascending order of the last names. We want to make a new modification to the DAO and add a method that will list the employees in the ascending order of their last names. 


## Update DAO 

```java EmployeeRepository
package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!

    //
    public List<Employee> findAllByOrderByLastNameAsc();

}

```

Then implement the method, `findAllByOrderByLastNameAsc()` in the service. 

```java 
package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.dao.EmployeeRepository;
import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);

        Employee theEmployee = null;

        if (result.isPresent()) {
            theEmployee = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }
}

```

## Add the New Button 

Use the code below to add a button to your page. 

```html
		    <!-- Add button -->
		    <a th:href="@{/employees/showAddEmployeeForm}"
		       class="btn btn-primary btn-sm mb-3">
		        Add Employee
		    </a>            

```


## Create a Form to Create the New Employee 

```html
<form action="#" th:action="@{/employees/save}" th:object="${employee}" method="POST"> 
	<input type="text" th:field="*{firstName}" placeholder="First name"
	class="form-control mb-4 w-25">

	<input type="text" th:field="*{lastName}" placeholder="Last name" 
	class="form-control mb-4 w-25">

	<input type="text" th:field="*{email}" placeholder="Email" 
	class="form-control mb-4 w-25">

	<button type="submit" class="btn btn-info col-2">Save</button>
</form>
```
