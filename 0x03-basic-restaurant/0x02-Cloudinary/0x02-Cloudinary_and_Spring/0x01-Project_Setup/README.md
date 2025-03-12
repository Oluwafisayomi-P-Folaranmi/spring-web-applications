## **Project Setup**

### **Step 1: Create a Spring Boot Project**

Use [Spring Initializr](https://start.spring.io/) to create a new project with the following dependencies:

  + Spring Web
  + Spring Boot DevTools
  + Lombok
  + Spring Data JPA (for optional database storage)

### **Step 2: Add Cloudinary Dependency**

Add the Cloudinary Java SDK to your `pom.xml`:

```xml
<dependency>
    <groupId>com.cloudinary</groupId>
    <artifactId>cloudinary</artifactId>
    <version>1.33.0</version>
</dependency>
```

For Gradle, add:

```gradle
implementation 'com.cloudinary:cloudinary:1.33.0'
```
