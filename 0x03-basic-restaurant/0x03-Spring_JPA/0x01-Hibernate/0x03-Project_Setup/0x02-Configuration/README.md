## Configuration

### **Lombok Maven Dependency**

One thing to edit for the **lombok** dependency is to replace it with the one on **lombok maven dependency** website. Visit the website to get the latest version as thus:

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.36</version>
    <scope>provided</scope>
</dependency>
```

Also, we need to add the `version` type inside the **plugins** at the bottom of the `pom.xml` file.

### **Automatic Data Source Configuration**

Spring Boot will read the database connection information from the `application.properties` file. Let's take a quick look at the `application.properties` file. 

```properties
# Data Source Configuration
spring.datasource.url = jdbc:mysql://localhost:3306/restaurant
spring.datasource.username = <username>
spring.datasource.password = <password>
```

We will determine the values to use after we have developed the data source.

### **Editing Console Log Interface**

One thing I'd like to do is that when we're running these standalone applications, we want to focus on doing some operation and then printing out the results. We want to turn off some of the chatter. 

#### **Turn of the Spring Boot Banner**

We don't want to see the Spring Boot banner every time we are running Spring Boot.

```properties
# Turn of Spring Boot banner 
spring.main.banner-mode = off
```

Now, the application runs without the Spring Boot banner being displayed. 

#### **Turn off the Loggings**

Again, we're just trying to minimize some of the logging and minimize some of the chatter that we see when we run our application, therefore we want to reduce the logging level. We will set the `logging-level` to `warn` such that it'll only show the warnings and the errors but not all the normal background logging information for Spring. 

```properties
# Reduce logging level. Set logging level to warn 
logging.level.root = warn
```

### **Spring Boot Command-Line Application**

We'll create a Spring Boot command line application. This will allow us to focus on the Hibernate JPA coding and regular testing.

```Java
@SpringBootApplication
public class RestaurantApplication {

	public static void main(String[] args) {

		SpringApplication.run(RestaurantApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return runner -> {
			System.out.println("Welcoming the developer. The application is running.");
		};
	}
}
```
