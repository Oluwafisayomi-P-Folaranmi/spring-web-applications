## **JsonIgnore**

### **Understanding `@JsonIgnore` in Java (Jackson Library)**

`@JsonIgnore` is an annotation from the **Jackson** library (used for JSON serialization and deserialization) that tells the framework to **ignore a specific field** when converting a Java object to JSON and vice versa.

#### **Why Use `@JsonIgnore`?**
1. **Avoid Infinite Recursion**  
   - In bidirectional relationships (e.g., `User` ↔ `Coupon` with `@ManyToMany`), serialization can cause an infinite loop.
   - `@JsonIgnore` prevents this by excluding the marked field.

2. **Hide Sensitive Data**  
   - Fields like `password` in the `User` entity should not be exposed in API responses.
   - Example:
     ```java
     @JsonIgnore
     private String password;
     ```

3. **Exclude Unnecessary Fields**  
   - Some internal fields may not be relevant for API consumers.
   - Example: `usedByUsers` in `Coupon` is not always needed in API responses.

#### **Example: Preventing Infinite Recursion**
Without `@JsonIgnore`, this might happen:

```java
public class User {
    @ManyToMany
    private Set<Coupon> usedCoupons = new HashSet<>();
}

public class Coupon {
    @ManyToMany(mappedBy = "usedCoupons")
    private Set<User> usedByUsers = new HashSet<>();
}
```
- When serializing `User`, it tries to serialize `usedCoupons`.
- Each `Coupon` then tries to serialize `usedByUsers` (which contains `User` again).
- This creates an infinite loop and results in **StackOverflowError**.

**Solution:** Use `@JsonIgnore` in `Coupon`:
```java
@ManyToMany(mappedBy = "usedCoupons")
@JsonIgnore
private Set<User> usedByUsers = new HashSet<>();
```

#### **Alternatives to `@JsonIgnore`**
- `@JsonManagedReference` and `@JsonBackReference` (for bidirectional relationships).
- `@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)` (if you want to allow deserialization but not serialization).

Would you like to explore these alternatives in more detail? 😊
