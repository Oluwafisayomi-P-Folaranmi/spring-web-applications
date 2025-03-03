# Overview 


The Spring Security is implemented using servlet filters in the background. There are two methods of securing an app: declarative and programmatic. 

These servlet filters are used to pre-process or post-process web requests. Servlet filters can route web requests based on security logic. **Routing web requests** refers to the process of directing incoming network requests to the appropriate server or service based on predefined rules or criteria. 


## Security Concepts 

+ Authentication: 
  - Check user id and password with credentials stored in app/db. 

+ Authorisation: 
  - Check to see if user has an authorised role. 

+ Declarative Security: 
  - This is defining application security in configurations like the Java config, `@Configuration`. It provides separation of concerns between application code and security code. 

+ Programmatic Security: 
  - Spring Security provides an API for custom application coding. It provides greater customisation for specific app requirements. 


## Enablng Spring Security 

```xml pom
		...
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		...

```

This dependency will automatically secure all endpoints. When you want to access the application, Spring Security will prompt a login form. 

We will see how to customise the login details, including storing information in the database. 


## Login Methods 

The Security dependency requires log in when we want to access the application. 

+ HTTP Basic Authentication 
  - Built-in dialog from browser. 
  
+ Default login form 
  - Spring Security provides a default login form. 

+ Custom login form 
  - Creating a login form. 
