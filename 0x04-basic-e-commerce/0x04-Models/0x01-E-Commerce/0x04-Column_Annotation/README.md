## **`@Column` Annotation**

### **Understanding `@Column` in JPA (Java Persistence API)**  

The `@Column` annotation in JPA is used to **customize** how a field is mapped to a database column. It provides more control over column properties like name, length, uniqueness, and nullability.  

---

## **1. Basic Usage**  
By default, JPA automatically maps entity fields to columns with the same name. However, you can customize this using `@Column`.  

```java
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "full_name")  // Maps `fullName` to `full_name` in the database
    private String fullName;
}
```

- **Without `@Column`**, JPA maps `fullName` to `fullName`.  
- **With `@Column(name = "full_name")`**, it maps `fullName` to `full_name` instead.

---

## **2. Common Attributes of `@Column`**  

| Attribute       | Description |
|----------------|-------------|
| `name`         | Changes the column name in the database |
| `nullable`     | Controls whether the column allows `NULL` values (default: `true`) |
| `length`       | Sets the maximum length (default: `255` for `String`) |
| `unique`       | Enforces a unique constraint |
| `updatable`    | Determines if the column can be updated (default: `true`) |
| `insertable`   | Determines if the column is included in INSERT statements (default: `true`) |
| `columnDefinition` | Defines the exact SQL data type (useful for database-specific types) |

---

## **3. Examples of `@Column` Customization**

### **a) Setting Column Name, Length, and Nullability**
```java
@Column(name = "email_address", length = 100, nullable = false)
private String email;
```
- The column name in the database will be `email_address`.
- The maximum length is `100` characters.
- The column **cannot** be `NULL`.

---

### **b) Enforcing Uniqueness**
```java
@Column(unique = true)
private String username;
```
- The `username` column will have a **unique constraint**.

---

### **c) Preventing Updates**
```java
@Column(updatable = false)
private String createdBy;
```
- Once saved, `createdBy` **cannot be modified**.

---

### **d) Using `columnDefinition` for Custom SQL Types**
```java
@Column(columnDefinition = "TEXT")
private String description;
```
- Instead of the default `VARCHAR(255)`, this maps `description` to **TEXT** in SQL.

---

## **4. When to Use `@Column`?**
- When you **want to rename a column** in the database.
- When you need to **enforce constraints** like `nullable = false` or `unique = true`.
- When the **default column type is not suitable** (e.g., using `TEXT` instead of `VARCHAR`).
- When you need to **restrict updates or inserts** (e.g., `updatable = false`).

Would you like to see an example with multiple column configurations? 🚀
