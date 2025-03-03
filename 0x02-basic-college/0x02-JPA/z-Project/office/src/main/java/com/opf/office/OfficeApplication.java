package com.opf.office;

import com.opf.office.entity.Employee;
import com.opf.office.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OfficeApplication {

	public static void main(String[] args) {

		SpringApplication.run(OfficeApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(EmployeeService employeeService) {
		return runner -> {
			System.out.println("Welcoming the developer. The application is running.");

			/**
			String email = "folaranmifisayo@gmail.com";
			findEmployeeByEmail(employeeService, email);
			 */
		};
	}

	private void findEmployeeByEmail(EmployeeService employeeService, String email) {
		System.out.println("Query for the employee with email '" + email + "'");
		Employee employee = null;
		try {
			employee = employeeService.findByEmail(email);
			if (employee == null) {
				throw new RuntimeException("There is no employee with such email");
			}
		}
		catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("The employee with the email '" + email + "' is " + employee);
	}

}
