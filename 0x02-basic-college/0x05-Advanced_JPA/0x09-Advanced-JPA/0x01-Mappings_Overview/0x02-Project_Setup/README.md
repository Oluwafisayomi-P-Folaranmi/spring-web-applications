# Project Setup


## Dependency

The dependencies will be needed for the database connection. So we will use the JPA dependency, and the sql driver dependency, since we're builing a database app. As seen below are the dependencies respectively.


```xml
		...
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>

		...

```


## Database Connectivity

We will configure the database connection in the application.properties file. We will also want to edit the look and feel of our command line. 


```application.properties
## Configure using the database
spring.datasource.url=jdbc:mysql://localhost:3306/hb-01-one-to-one-uni
spring.datasource.username=springstudent
spring.datasource.password=springstudent

## Show JPA/Hibernate logging
logging.level.org.hibernate.SQL=trace
logging.level.org.hibernate.orm.jdbc.bind=trace

##
## Look and Feel of Our Command Line
##
## Off the spring banner
spring.main.banner-mode=off

## Hide logging aside warning
logging.level.root=WARN

```


### The Schema

In the project, the schema defines the structure of your database tables and how they map to the entities in your Java application. JPA allows you to work with database entities through Java classes rather than writing SQL queries manually.


```sql
DROP SCHEMA IF EXISTS `hb-01-one-to-one-uni`;
CREATE SCHEMA `hb-01-one-to-one-uni`;
use `hb-01-one-to-one-uni`;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `instructor_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `youtube_channel` varchar(128) DEFAULT NULL,
  `hobby` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `instructor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `instructor_detail_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DETAIL_idx` (`instructor_detail_id`),
  CONSTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_detail_id`) REFERENCES `instructor_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

```
