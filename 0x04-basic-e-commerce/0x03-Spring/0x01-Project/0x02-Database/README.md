## **Database**

For now, we will create a database, `shopfull` in MySQL. Create the database with the following sql code:

```sql
CREATE DATABASE IF NOT EXISTS `shopfull`;
USE `shopfull`;
SET FOREIGN_KEY_CHECKS = 0;
```

In the `application.properties` file, configure the data source by writing the properties of the data source

```properties
# Data Source Configuration
spring.datasource.url = jdbc:mysql://localhost:3306/shopfull
spring.datasource.username = <username>
spring.datasource.password = <password>
spring.jpa.open-in-view=false
```

Put the `username` and `password` of the database workbench.

### **The Schema**

In the project, the **schema** defines the structure of your database tables and how they map to the entities in your Java application.

JPA allows you to work with database entities through Java classes rather than writing SQL queries manually.

We will create the schema in the database `shopfull`.
