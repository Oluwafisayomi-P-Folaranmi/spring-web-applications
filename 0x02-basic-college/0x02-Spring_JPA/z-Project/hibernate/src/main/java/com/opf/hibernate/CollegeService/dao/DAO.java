package com.opf.hibernate.CollegeService.dao;

import com.opf.hibernate.CollegeService.Entity.Student;

import java.util.List;

public interface DAO {

    void save(Student student);

    Student findById(Long theId);

    List<Student> findAll();

    List<Student> findByFirstName(String firstName);

    List<Student> findByLastName(String lastName);

    Student update(Student student);

    void delete(Long studentId);
}
