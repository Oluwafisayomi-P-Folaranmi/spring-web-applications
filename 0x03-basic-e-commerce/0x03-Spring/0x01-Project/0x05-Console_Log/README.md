## **Editing Console Log Interface**

One thing I'd like to do is that when we're running these standalone applications, we want to focus on doing some operation and then printing out the results. We want to turn off some of the chatter. 

### **Turn of the Spring Boot Banner**

We don't want to see the Spring Boot banner every time we are running Spring Boot.

```properties
# Turn of Spring Boot banner 
spring.main.banner-mode = off
```

Now, the application runs without the Spring Boot banner being displayed. 

### **Turn off the Loggings**

Again, we're just trying to minimize some of the logging and minimize some of the chatter that we see when we run our application, therefore we want to reduce the logging level. We will set the `logging-level` to `warn` such that it'll only show the warnings and the errors but not all the normal background logging information for Spring. 

```properties
# Reduce logging level. Set logging level to warn 
logging.level.root = warn
```
