## **Controller**

The first API endpoint we will create here will return a message response. We create a `@RestController` class `controller/HomeController` which is our first controller.

```java
@RestController
public class HomeController {

    @GetMapping
    public ResponseEntity<ApiResponse> HomeControllerHandler() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Welcome to shopfull");
        apiResponse.setTime(LocalDateTime.now());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
```
