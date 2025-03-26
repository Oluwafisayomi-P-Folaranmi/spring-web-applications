## **REST Controller**

Let's try to create a REST controller and send a request to it. Below is the REST controller. This is so as to test Spring Security Configuration that we put in place already.

```java
@RestController
@RequestMapping("/security")
public class SecurityController {

    @GetMapping("/hello/{developer-name}") // `/security/hello/<developer-name>`
    public String sayHello(@PathVariable("developer-name") String developerName) {

        return "Hello " + developerName;
    }
}
```

Use the endpoint `http://localhost:8080/security/hello/<developer-name>`. The client will prompt you to input your login details for authentification.

Now, we're set to proceed to applying advanced security concepts in the Spring framework.
