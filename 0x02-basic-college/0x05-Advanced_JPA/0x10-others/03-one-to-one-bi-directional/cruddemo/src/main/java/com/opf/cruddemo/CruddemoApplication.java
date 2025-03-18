package com.opf.cruddemo;

import com.opf.cruddemo.dao.AppDAO;
import com.opf.cruddemo.entity.Instructor;
import com.opf.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			String devName = "Opf";
			System.out.println("Welcome " + devName + ". Your program is running.");
			System.out.println("You are now on the command line...");
			// createInstructor(appDAO);
			// findInstructorDetail(appDAO);
			deleteInstructorDetailById(appDAO);
		};
	}

	public void findInstructorDetail(AppDAO appDAO) {

		// find instructor detail
		int theId = 6;
		InstructorDetail instructorDetail1 = appDAO.findInstructorDetailByID(theId);

		// print out the instructor detail
		System.out.println("The instructor detail is: " + instructorDetail1);

		// print out the instructor
		System.out.println("The instructor is: " + instructorDetail1.getInstructor());

	}
	public void createInstructor(AppDAO appDAO) {

		// create an instructor and instructor detail instances and bind the two
		Instructor instructor1 = new Instructor("Oluwafisayomi", "Folaranmi", "folaranmifisayo@gmail.com");
		InstructorDetail instructorDetail1 = new InstructorDetail("fola_youtube", "reading");
		instructor1.setInstructorDetail(instructorDetail1);

		// saving the instructor to the database
		appDAO.save(instructor1);
	}

	void deleteInstructorDetailById(AppDAO appDAO) {

		int theId = 6;
		appDAO.deleteInstructorDetailById(theId);
	}
}
