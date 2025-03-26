## **Validations**

### **Validations in Java (Jakarta Bean Validation)**

Validation in Java is commonly implemented using **Jakarta Bean Validation** (formerly **Javax Validation**) and is widely used in **Spring Boot, JPA**, and other frameworks. These **validations help ensure that data meets specific rules before being processed or stored in a database**.

### **Common Validation Annotations**

Jakarta Bean Validation provides several annotations for validating fields in Java objects. Below are some of the most frequently used ones:

#### **1. `@NotNull`**

  - Ensures that a field **must not be `null`**.
  - Allows empty values (like `""` for strings).
  - Used for **mandatory** fields.

```java
import jakarta.validation.constraints.NotNull;

public class User {

    @NotNull
    private String name;

    // Getters and Setters
}
```

✅ `name = "John"` → ✅ Valid  
❌ `name = null` → ❌ Invalid  

#### **2. `@NotEmpty`**

  - Ensures that a field is **not `null` and not empty (`""`)**.
  - Used mostly for **strings and collections**.

```java
import jakarta.validation.constraints.NotEmpty;

public class Product {

    @NotEmpty
    private String productName;
}
```

✅ `productName = "Laptop"` → ✅ Valid   
✅ `productName = " "` → ✅ Valid  
❌ `productName = ""` → ❌ Invalid  
❌ `productName = null` → ❌ Invalid  

#### **3. `@NotBlank`**
  
  - Ensures that a string is **not `null`, not empty (`""`), and not just spaces**.
  - More strict than `@NotEmpty`.

```java
import jakarta.validation.constraints.NotBlank;

public class Comment {

    @NotBlank
    private String text;
}
```

✅ `"Hello"` → ✅ Valid  
❌ `""` → ❌ Invalid  
❌ `"   "` → ❌ Invalid  

#### **4. `@Size(min, max)`**

  - Ensures that the field's **length** is within a specific range.
  - Works for **strings, lists, arrays, etc.**.

```java
import jakarta.validation.constraints.Size;

public class PasswordReset {

    @Size(min = 8, max = 20)
    private String newPassword;
}
```

✅ `"Password123"` → ✅ Valid  
❌ `"short"` → ❌ Invalid  
❌ `"thispasswordiswaytoolong"` → ❌ Invalid  

#### **5. `@Min` and `@Max`**

  - Ensures that a **numeric value** is within a range.

```java
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

public class Order {
    @Min(1)
    @Max(100)
    private int quantity;
}
```

✅ `quantity = 10` → ✅ Valid  
❌ `quantity = 0` → ❌ Invalid  
❌ `quantity = 101` → ❌ Invalid  

#### **6. `@Pattern`**

  - Ensures that a string follows a **regex pattern**.

```java
import jakarta.validation.constraints.Pattern;

public class User {
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only letters allowed")
    private String firstName;
}
```

✅ `"John"` → ✅ Valid  
❌ `"John123"` → ❌ Invalid  

#### **7. `@Email`**

  - Ensures that the value is a **valid email address**.

```java
import jakarta.validation.constraints.Email;

public class Contact {
    @Email
    private String email;
}
```

✅ `"test@example.com"` → ✅ Valid  
❌ `"invalid-email"` → ❌ Invalid  

### **How to Use Validations in Spring Boot**

In a **Spring Boot REST API**, you can apply validation to request bodies using `@Valid` or `@Validated`.

#### **Example: Applying Validations to a DTO**

```java
import jakarta.validation.constraints.*;

public class UserDTO {
    @NotNull
    private String name;

    @Email
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    // Getters and Setters
}
```

#### **Example: Applying Validation in a Controller**

```java
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody UserDTO user) {
        return "User registered successfully!";
    }
}
```

### **Conclusion**

  - `@NotNull` ensures a field is not `null` but allows empty values.
  - `@NotEmpty` ensures a field is not `null` or empty (`""`).
  - `@NotBlank` ensures a field is not `null`, empty, or just spaces.
  - Other annotations like `@Size`, `@Min`, `@Max`, `@Pattern`, and `@Email` help enforce additional constraints.
