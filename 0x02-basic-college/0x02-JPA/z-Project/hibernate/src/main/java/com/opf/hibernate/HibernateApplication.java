package com.opf.hibernate;

import com.opf.hibernate.CollegeService.Entity.Student;
import com.opf.hibernate.CollegeService.dao.StudentDAOImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAOImpl studentDAOImpl) {
		return runner -> {
			System.out.println("Welcoming the developer. The application is running.");
			// findStudentById(studentDAOImpl, 5L);
			// createStudent(studentDAOImpl); // Creates a student
			// createStudents(studentDAOImpl); // Creates multiple students
			// updateStudent(studentDAOImpl, 2L); // Updates a student
			// deleteStudent(studentDAOImpl, 4L); // Deletes a student
		};
	}

	private void deleteStudent(StudentDAOImpl studentDAOImpl, Long studentId) {
		// Retrieve student before delete
		Student student = studentDAOImpl.findById(studentId);
		System.out.println("Student before delete: " + student);
		studentDAOImpl.delete(studentId);
	}

	private void updateStudent(StudentDAOImpl studentDAOImpl, Long studentId) {
		// Retrieve student before update
		Student student = studentDAOImpl.findById(studentId);
		System.out.println("Student before update: " + student);

		// Update student
		System.out.println("Updating...");
		Student newStudent = new Student("Bolu", "Peter", "bolupeter@outlook.com");
		newStudent.setId(studentId);
		Student updatedStudent = studentDAOImpl.update(newStudent);

		// Retrieve student after update
		student = studentDAOImpl.findById(studentId);
		System.out.println("Student after update: " + student);
	}

	private void createStudents(StudentDAOImpl studentDAOImpl) {
		// Create multiple Student objects
		Student student1 = new Student("Bolu", "Peter", "bpeter@outlook.com");
		Student student2 = new Student("Fisayo", "Folaranmi", "ffolaranmi@gmail.com");
		Student student3 = new Student("Wole", "Folaranmi", "wfolaranmi@yahoo.com");
		// Add the objects to the database
		studentDAOImpl.save(student1);
		studentDAOImpl.save(student2);
		studentDAOImpl.save(student3);
	}

	private void createStudent(StudentDAOImpl studentDAOImpl) {
		// Create a Student object
		Student student = new Student("Doyin", "Folaranmi", "doyinfolaranmi@gmail.com");
		// Add it to the database
		studentDAOImpl.save(student);
	}

	private void findStudentById(StudentDAOImpl studentDAOImpl, Long studentId) {
		Student student = studentDAOImpl.findById(studentId);
		System.out.println(student);
	}
}
