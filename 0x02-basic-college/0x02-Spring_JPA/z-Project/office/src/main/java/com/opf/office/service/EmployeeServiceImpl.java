package com.opf.office.service;

import com.opf.office.dao.EmployeeDAO;
import com.opf.office.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeDAO employeeDAO;

    /**
     *
     * @param employeeDAO
     * @return
     */
    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public Employee save(Employee employee) {
        Employee theEmployee = employeeDAO.save(employee);
        return theEmployee;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(Long employeeId) {
        return employeeDAO.findById(employeeId);
    }

    @Override
    public Employee findByEmail(String email) {
        Employee employee = employeeDAO.findByEmail(email);
        return employee;
    }

    @Override
    public void delete(Long employeeId) {
        employeeDAO.delete(employeeId);
    }
}
