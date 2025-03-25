## **`@ElementCollection` Annotation**

The `@ElementCollection` annotation in **JPA (Java Persistence API)** is used to define a collection of **non-entity** (basic or embeddable) values in an entity. It allows an entity to have a collection of **primitive values** or **embedded objects** without creating a separate entity class.

### **Key Features of `@ElementCollection`**
1. **Used for non-entity collections**: Stores collections of primitive types (e.g., `List<String>`, `Set<Integer>`) or embeddable objects.
2. **Creates a separate table**: The collection values are stored in a separate table, automatically mapped by JPA.
3. **Works with `@CollectionTable`**: You can specify the name of the collection table using `@CollectionTable`.
4. **Can be indexed with `@OrderColumn`**: Maintains order in `List<T>` collections.

---

### **Example 1: Storing a List of Strings**
#### **Entity: Storing a list of hobbies**
```java
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "person_hobbies", joinColumns = @JoinColumn(name = "person_id"))
    @Column(name = "hobby")
    private List<String> hobbies;

    // Getters and Setters
}
```
#### **Generated Table Structure**
| person_hobbies (collection table) |
|---------------|----------------|
| person_id     | hobby          |
| 1            | Football        |
| 1            | Chess           |

---

### **Example 2: Storing a Set of Addresses (Embeddable Type)**
#### **Embeddable Class**
```java
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String city;
    private String country;

    // Getters and Setters
}
```

#### **Entity with `@ElementCollection`**
```java
import jakarta.persistence.*;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "user_addresses", joinColumns = @JoinColumn(name = "user_id"))
    private Set<Address> addresses;

    // Getters and Setters
}
```
#### **Generated Table Structure**
| user_addresses (collection table) |
|-----------|-----------|-----------|-----------|
| user_id   | street    | city      | country   |
| 1         | 123 Main  | Lagos     | Nigeria   |
| 1         | 456 Park  | Abuja     | Nigeria   |

---

### **When to Use `@ElementCollection`**
✅ When storing simple values or embeddable types without a separate entity.  
✅ When an entity has a small collection of related data that does not require independent lifecycle management.  

🚫 **Avoid using it for large datasets**—use a separate entity with `@OneToMany` instead for better scalability.  

Would you like more details on performance considerations or best practices?
