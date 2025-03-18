## **Response**

### **`@Data` Annotation in Lombok**

The `@Data` annotation in Lombok is a shortcut that combines several commonly used annotations to reduce boilerplate code in Java classes. ***It is equivalent to using all of the following combined***:

- `@Getter` – Generates getters for all fields.  
- `@Setter` – Generates setters for all non-final fields.  
- `@ToString` – Generates a `toString()` method including all fields.  
- `@EqualsAndHashCode` – Generates `equals()` and `hashCode()` methods.  
- `@RequiredArgsConstructor` – Generates a constructor for final and `@NonNull` fields.  

We provide a response that contains a message which will be sent when this first API is accessed. We will use the `@Data` annotation from Lombok to provide necessary utilities for the response class.

The class contains fields, in which `message` is a part. The `message` field outputs the message to the API json response. While the `time` tells what time the response was processed. The class is as thus in `response/ApiResponse`.

```java
@Data
public class ApiResponse {

    private String message;

    private LocalDateTime time;

}
```
