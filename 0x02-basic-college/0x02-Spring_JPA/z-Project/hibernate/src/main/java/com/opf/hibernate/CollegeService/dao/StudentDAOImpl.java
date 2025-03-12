package com.opf.hibernate.CollegeService.dao;

import com.opf.hibernate.CollegeService.Entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements DAO {

    /*
     * Fields
     */
    public EntityManager entityManager;

    /*
     * Constructors
     */
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(Long theId) {
        return entityManager.find(Student.class, theId);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> typedQuery = entityManager.createQuery(
                "FROM Student ORDER BY lastName", Student.class);
        List<Student> students = typedQuery.getResultList();
        return students;
    }

    @Override
    public List<Student> findByFirstName(String firstName) {
        TypedQuery<Student> typedQuery = entityManager.createQuery(
                "FROM Student WHERE firstName=:firstName", Student.class);
        typedQuery.setParameter(firstName, firstName);
        List<Student> students = typedQuery.getResultList();
        return students;
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> typedQuery = entityManager.createQuery(
                "FROM Student WHERE firstName=:lastName", Student.class);
        typedQuery.setParameter(lastName, lastName);
        List<Student> students = typedQuery.getResultList();
        return students;
    }

    @Override
    @Transactional
    public Student update(Student student) {
        Student theStudent = entityManager.merge(student);
        return theStudent;
    }

    @Override
    @Transactional
    public void delete(Long studentId) {
        Student student = entityManager.find(Student.class, studentId);
        entityManager.remove(student);
    }
}
