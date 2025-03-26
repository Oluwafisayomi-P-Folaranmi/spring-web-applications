## **`@ElementCollection` Annotation**

The `@ElementCollection` annotation in **JPA (Java Persistence API)** is used to define a collection of **non-entity** (basic or embeddable) values in an entity. It allows an entity to have a collection of **primitive values** or **embedded objects** without creating a separate entity class.

### **Key Features of `@ElementCollection`**

  1. **Used for non-entity collections**: Stores collections of primitive types (e.g., `List<String>`, `Set<Integer>`) or embeddable objects.
  2. **Creates a separate table**: The collection values are stored in a separate table, automatically mapped by JPA.
  3. **Works with `@CollectionTable`**: You can specify the name of the collection table using `@CollectionTable`.
  4. **Can be indexed with `@OrderColumn`**: Maintains order in `List<T>` collections.

### **Example: Storing a List of Strings**

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

### **When to Use `@ElementCollection`**

  ✅ When **storing simple values or embeddable types without a separate entity**.  
  ✅ When **an entity has a small collection of related data that does not require independent lifecycle management**.  
  🚫 **Avoid using it for large datasets**—use a separate entity with `@OneToMany` instead for better scalability.  
