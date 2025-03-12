package com.opf.office.dao;

import com.opf.office.entity.Employee;

import java.util.List;

public interface DAO {

    Employee save(Employee employee);

    List<Employee> findAll();

    Employee findById(Long employeeId);

    Employee findByEmail(String email);

    void delete(Long employeeId);
}
