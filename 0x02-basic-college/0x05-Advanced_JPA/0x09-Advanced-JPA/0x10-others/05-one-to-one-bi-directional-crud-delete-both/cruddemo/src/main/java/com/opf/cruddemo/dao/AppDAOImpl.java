package com.opf.cruddemo.dao;

import com.opf.cruddemo.entity.Instructor;
import com.opf.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AppDAOImpl implements AppDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {

        // retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        // delete the instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailByID(int theId) {

        int instructorId = theId;
        return entityManager.find(InstructorDetail.class, instructorId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {

        // find the instructordetail
        InstructorDetail theInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        // remove the associated object reference
        // break the bi-directional link
        theInstructorDetail.getInstructor().setInstructorDetail(null);

        // delete the instructor detail
        entityManager.remove(theInstructorDetail);
    }
}
