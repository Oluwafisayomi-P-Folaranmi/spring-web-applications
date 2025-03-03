package com.opf.hibernate.CollegeService.controller;

import com.opf.hibernate.CollegeService.Entity.Student;
import com.opf.hibernate.CollegeService.dao.StudentDAOImpl;
import com.opf.hibernate.CollegeService.exception.StudentErrorResponse;
import com.opf.hibernate.CollegeService.exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/college")
public class CollegeController {

    /*
     * The fields
     */
    StudentDAOImpl studentDAO;

    /**
     *
     * @param studentDAO
     */
    public CollegeController(StudentDAOImpl studentDAO) {
        this.studentDAO = studentDAO;
    }

    /**
     *
     * @return a list of students
     */
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = studentDAO.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getStudent(@PathVariable Long studentId) {

        Student student = null;

        // Try if student id is a number
        try {
            student = studentDAO.findById(studentId);
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Input parameter is not a number");
        }

        // Try this to catch `0` or negative integers
        if (studentId == 0 || studentId < 0 || student == null) {
            throw new StudentNotFoundException("The student is not found.");
        }

        // If there is no exception
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
