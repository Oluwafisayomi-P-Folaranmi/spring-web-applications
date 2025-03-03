package com.opf.office.rest;

import com.opf.office.entity.Employee;
import com.opf.office.exception.EmployeeNotFoundException;
import com.opf.office.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/office")
public class EmployeeController {

    EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    // expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    // add mapping for GET "/employee/{employeeId}"
    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<?> findById(@PathVariable Long employeeId) {

        Employee employee = null;

        // Try if student id is a number
        try {
            employee = employeeService.findById(employeeId);
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Input parameter is not a number");
        }

        // Try this to catch `0` or negative integers
        if (employeeId == 0 || employeeId < 0 || employee == null) {
            throw new EmployeeNotFoundException("The employee is not found.");
        }

        // If there is no exception
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // add mapping for POST /employees - add new employee
    @PostMapping("/employees")
    public ResponseEntity<?> setStudent(@RequestBody Employee employee) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        employee.setId(null);
        Employee theEmployee = employeeService.save(employee);
        return new ResponseEntity<>(theEmployee, HttpStatus.OK);
    }

    // add mapping for PUT /employees - update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {

        Employee theEmployee = employeeService.save(employee);

        return theEmployee;
    }

    // add mapping for DELETE /employees/{employeeId} - delete employee
    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long employeeId) {

        Employee employee;

        // Try if student id is a number
        try {
            employee = employeeService.findById(employeeId);
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Input parameter is not a number");
        }

        // Try this to catch `0` or negative integers
        if (employeeId == 0 || employeeId < 0 || employee == null) {
            throw new EmployeeNotFoundException("The employee is not found.");
        }

        // delete
        employeeService.delete(employeeId);

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
