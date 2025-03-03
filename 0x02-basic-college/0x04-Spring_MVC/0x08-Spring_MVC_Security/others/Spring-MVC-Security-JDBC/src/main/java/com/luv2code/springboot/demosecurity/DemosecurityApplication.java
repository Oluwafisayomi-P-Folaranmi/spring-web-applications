package com.luv2code.springboot.demosecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemosecurityApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemosecurityApplication.class, args);
		String devName = "Opf";
		System.out.println("Welcome " + devName);
		System.out.println("Your program is running...");
	}
}
