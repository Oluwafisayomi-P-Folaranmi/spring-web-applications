package com.opf.office.dao;

import com.opf.office.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAO implements DAO {

    EntityManager entityManager;

    /**
     *
     * @param entityManager
     */
    @Autowired
    public EmployeeDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Saves and employee
     * @param employee
     */
    @Override
    @Transactional
    public Employee save(Employee employee) {
        Employee theEmployee = entityManager.merge(employee);
        return theEmployee;
    }

    /**
     * find all employee
     * @return
     */
    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> typedQuery = entityManager.createQuery("FROM Employee ORDER BY lastName ASC", Employee.class);
        List<Employee> employees = typedQuery.getResultList();
        return employees;
    }

    /**
     * find employee by id
     * @param employeeId
     * @return
     */
    @Override
    public Employee findById(Long employeeId) {
        return entityManager.find(Employee.class, employeeId);
    }

    @Override
    public Employee findByEmail(String email) {
        TypedQuery<Employee> typedQuery = entityManager.createQuery(
                "FROM Employee WHERE email=:email", Employee.class);
        typedQuery.setParameter("email", email);
        Employee employee = typedQuery.getSingleResult();
        return employee;
    }

    /**
     * delete employee
     * @param employeeId
     */
    @Override
    @Transactional
    public void delete(Long employeeId) {
        Employee employee = entityManager.find(Employee.class, employeeId);
        entityManager.remove(employee);
    }
}
