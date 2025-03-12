package com.opf.office.service;

import com.opf.office.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee save(Employee employee);

    List<Employee> findAll();

    Employee findById(Long employeeId);

    Employee findByEmail(String email);

    void delete(Long employeeId);
}
