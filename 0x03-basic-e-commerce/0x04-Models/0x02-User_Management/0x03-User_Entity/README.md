## **User**

The **`User`** table is a fundamental part of many database models, ***especially in applications that involve authentication, authorization, or user-related data management***.

### **Purpose of the `User` Table**

The **`User`** table stores **user-related information** in an application. It typically includes essential details such as ***user credentials***, ***personal information***, ***roles***, and ***preferences***.

### **Common Fields in a `User` Table**

| Column Name         | Data Type           | Description |
|---------------------|--------------------|-------------|
| `id`               | `BIGINT` / `UUID` (Primary Key) | Unique identifier for the user.|
| `username`         | `VARCHAR(255)` / `TEXT` | Unique username for login/authentication.|
| `email`            | `VARCHAR(255)` | User's email (often unique).|
| `password_hash`    | `TEXT` | Hashed password for authentication.|
| `first_name`       | `VARCHAR(100)` | User's first name.|
| `last_name`        | `VARCHAR(100)` | User's last name.|
| `phone_number`     | `VARCHAR(20)` | Optional contact number.|
| `role`             | `VARCHAR(50)` | Role of the user (e.g., ADMIN, USER).|
| `status`           | `ENUM('ACTIVE', 'INACTIVE', 'BANNED')` | Account status.|
| `created_at`       | `TIMESTAMP` | Date and time when the user was created.|
| `updated_at`       | `TIMESTAMP` | Last modification timestamp.|

### **Relationships with Other Tables**

The **`User`** table often has relationships with other tables:

  + **One-to-Many** with `Address`: A user can have multiple addresses (`address` table), and multiple addresses (`address` table) can belong to one user.

  + **Many-to-Many** with `Coupon`: Many users can use one coupon (`coupon` table) and a coupon can be used by many users.
  
   A user can have multiple orders (`orders` table) or posts (`posts` table).
  + **Many-to-Many**: Users can belong to multiple groups (`user_groups` table).
  + **One-to-One**: A user may have one profile (`user_profile` table).

### **SQL Schema**

A basic `User` table definition in **SQL**:

```sql
CREATE TABLE `User` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `full_name` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) UNIQUE NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `mobile` VARCHAR(20) UNIQUE,
    `role` ENUM('ROLE_CUSTOMER', 'ROLE_ADMIN', 'ROLE_SELLER') NOT NULL DEFAULT 'ROLE_CUSTOMER'
    ---
);
```

### **Entity Class**

The table schema that we have generated can be mapped to an entity.

```java
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    private String email;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String password;

    private String mobile;

    private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;

    @OneToMany
    private Set<Address> addresses = new HashSet<>();

    @ManyToMany
    @JsonIgnore
    private Set<Coupon> usedCoupons = new HashSet<>();
}
```

We have annotated `password` with `JsonProperty.Access.READ_ONLY`. The password will be hid whenever we fetch this data from front-end, as we do not want to expose the password.

We want to choose the `role` of the user from an enum of roles. The enum type of roles, `domain/USER_ROLE` may consist:

```java
public enum USER_ROLE {

    ROLE_ADMIN,

    ROLE_SELLER,

    ROLE_CUSTOMER
}
```

Using this `USER_ROLE` enum, we want to refuse input of any other type value coming from the front-end aside the enum values - `ROLE_ADMIN`, `ROLE_SELLER`, `ROLE_CUSTOMER`.

We have a set of addresses. We can use anyone of the addresses whenever we want to send purchased product to the customer.

For the `usedCoupon`, when we realise that the user have used a particular, then we don't want the user to use it again.

We have created the relationships between the `User` class and the other classes: `Address`, `Coupon`, 

  + **One-to-Many** with `Address`: We have annotated the `address` field as `@OneToMany`.

  + **Many-to-Many** with `Coupon`: We have annotated the `coupon` field as `@ManyToMany`. Also, whenever we fetch the user data, we don't need the coupon, so we set the `@JsonIgnore`.

### **Security Considerations**

  + **Password Hashing**: Store passwords **hashed** (e.g., using bcrypt or Argon2) instead of plaintext.
  + **Unique Constraints**: Ensure **unique usernames and emails** to avoid duplicates.
  + **Indexing**: Index frequently queried fields like `email` and `username` for performance.
  + **Access Control**: Implement proper **role-based access control (RBAC)**.

### **Use Cases**

  + **Authentication & Authorization** (Login, Registration, Password Reset)
  + **User Profiles & Preferences**
  + **Access Control & Permissions**
  + **Audit Logs & User Activity Tracking**

***The `User` entity is now ready*** 🚀
