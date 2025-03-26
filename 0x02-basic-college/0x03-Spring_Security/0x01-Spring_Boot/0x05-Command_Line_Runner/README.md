## **Command Line Runner**

We'll create a Spring Boot command line application. This will allow us to focus on the Hibernate JPA coding and regular testing.

```Java
@SpringBootApplication
public class AdvancedJpaApplication {

	public static void main(String[] args) {

		SpringApplication.run(AdvancedJpaApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return runner -> {
			System.out.println("Welcoming the developer. The application is running.");
		};
	}
}
```

The application should run fine after the configurations are put in place.
