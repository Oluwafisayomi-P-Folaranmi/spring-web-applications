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
	CommandLineRunner commandLineRunner() {
		return runner -> {
			System.out.println("Welcoming the developer. The application is running.");

		};
	}

}
