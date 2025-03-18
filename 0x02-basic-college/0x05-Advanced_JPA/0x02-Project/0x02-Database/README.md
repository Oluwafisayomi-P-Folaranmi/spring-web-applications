## **Database**

For now, we will create a database, `college` in MySQL. Create the database with the following sql code:

```sql
CREATE DATABASE IF NOT EXISTS `college`;
USE `college`;
SET FOREIGN_KEY_CHECKS = 0;
```

In the `application.properties` file, configure the data source by writing the properties of the data source

```properties
# Data Source Configuration
spring.datasource.url = jdbc:mysql://localhost:3306/college
spring.datasource.username = <username>
spring.datasource.password = <password>
spring.jpa.open-in-view=false
```

Put the `username` and `password` of the database workbench.

### **The Schema**

In the project, the **schema** defines the structure of your database tables and how they map to the entities in your Java application.

JPA allows you to work with database entities through Java classes rather than writing SQL queries manually.

Use the following sql script to create the `instructor` and `instructor_detail` tables, and add the relationships.

```sql
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `instructor`;
DROP TABLE IF EXISTS `instructor_detail`;

SET FOREIGN_KEY_CHECKS = 1;

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
  KEY `FK_DETAIL_idx` (`instructor_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

ALTER TABLE `instructor`
ADD CONSTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_detail_id`) 
REFERENCES `instructor_detail` (`id`) 
ON DELETE CASCADE ON UPDATE NO ACTION;

SET FOREIGN_KEY_CHECKS = 1;
```
