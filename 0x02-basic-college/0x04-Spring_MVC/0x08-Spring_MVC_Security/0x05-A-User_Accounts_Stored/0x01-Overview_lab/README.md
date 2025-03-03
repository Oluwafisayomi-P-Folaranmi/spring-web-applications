# Overview


## Dependency

The dependency needed to add database support for Spring Security is the `mysql-connector-j`.


```xml pom
    ...
    <dependency>
      <groupId>org.mysql</groupId>
      <artifactId>mysql-connector-j</artifactId>
    </dependency>
    ...

```


## Database Support for Spring Security

So far users' account were hard-coded. We will store users' accounts in the database. Our users are:

   + john:
     + Password: test123
     + Roles: EMPLOYEE

   + mary:
     + Password: test123
     + Roles: EMPLOYEE, MANAGER 

   + susan: 
     + Password: test123
     + Roles: EMPLOYEE, MANAGER, ADMIN

We can give any names to the roles. Now we will put this information in the database. By default, Spring Security can read users' details from the database. You have to follow Spring Security's predefined table schemas. Spring Security will read all the JDBC code to read information from the database. All you have to do is set the configuration, create the tables as predefined and Spring Security will do all the heavy lifting in the background. 

We will develop SQL script to create the database table. You will be responsible to develop the code to access the using: JDBC, JPA/Hibernate. The we will update the Spring Security configuration to use JDBC authentication.

Spring Security has default tables: users, and authorities. The users tables have these specific columns: `username`, `password`, and `enabled`. The authorities authorities is same thing as roles. 


## The Controller


```java


```


## The Spring Security Configuration

Now we update our configuration file to use JDBC. We've injected the datasource which is auto-configured by SpringBoot. The `JdbcUserDetailsManager` tells Spring Security to use user details from the data source.


```java

```


## The JDBC

We will want to connect to the MySQL workbench in the application.properties file. Also we have added some other properties to log for JDBC. This will be useful when we run the application where we can verify that the application is reading information from the database. It is good for academic purposes.


```application.properties
#
# JDBC Connection 
# 
spring.datasource.url=jdbc:mysql://localhost:3306/employee_directory
spring.datasource.username=springstudent
spring.datasource.password=springstudent

#
# Log JDBC SQL Statements
#
# Only use this for dev/testing
# DO NOT use for PRODUCTION since it will log username
logging.level.org.springframework.jdbc.core=TRACE

```

The SQL scripts that will be used to create the users and authorities table is seen below. We have input data for our users, john, mary, and susan.


```sql
CREATE DATABASE  IF NOT EXISTS `employee_directory`;
USE `employee_directory`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--

INSERT INTO `users` 
VALUES 
('john','{noop}test123',1),
('mary','{noop}test123',1),
('susan','{noop}test123',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('john','ROLE_EMPLOYEE'),
('mary','ROLE_EMPLOYEE'),
('mary','ROLE_MANAGER'),
('susan','ROLE_EMPLOYEE'),
('susan','ROLE_MANAGER'),
('susan','ROLE_ADMIN');

```


## The Views


```html

```
