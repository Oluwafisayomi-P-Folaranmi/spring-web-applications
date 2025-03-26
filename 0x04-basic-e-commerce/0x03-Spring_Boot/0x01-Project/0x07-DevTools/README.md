## **DevTools**

To configure **Spring Boot DevTools** in a **Spring Boot application** using **IntelliJ IDEA**, follow these steps:

#### **Step 1: Enable "Build Project Automatically"**

1. Open **Preferences**.  
2. Navigate to **Build, Execution, Deployment → Compiler**.  
3. Check the box **Build Project Automatically**.  

#### **Step 2: Enable "Auto-Make"**  
1. Open **Preferences** again.  
2. Go to **Advanced Settings**.  
3. Check the box **Allow Auto-Make to Start**.  

After completing these steps, IntelliJ Community Edition will work seamlessly with Spring Boot DevTools.

### **Configuration**

```properties
# Spring Boot DevTools Configuration
spring.devtools.restart.enabled=true
spring.devtools.livereload.enabled=true
```
