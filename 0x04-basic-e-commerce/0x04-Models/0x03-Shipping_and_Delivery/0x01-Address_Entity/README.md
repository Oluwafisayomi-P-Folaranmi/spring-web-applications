## **Address Entity**: `01:25:00`

### **Purpose of the `Address` Table**

The `Address` table serves as a structured way to store and manage **location-based data related to users**, businesses, orders, or any entity that requires address information. Instead of duplicating address details in multiple tables, a dedicated `Address` table ensures **data integrity, normalization, and reusability**.

#### **Common Use Cases:**

  + **E-commerce:** Stores customer shipping and billing addresses.
  + **Logistics & Delivery Services:** Manages delivery locations.
  + **Business Directories:** Stores addresses for businesses and offices.
  + **Multi-Tenant Applications:** Allows users or companies to maintain multiple addresses.

### **Common Fields in an `Address` Table**

An `Address` table typically includes the following fields:

| Column Name   | Data Type                          | Description                           |
|--------------|---------------------------------|---------------------------------------|
| `id`        | BIGINT (PK)                     | Unique identifier for the address    |
| `name`      | VARCHAR(255)                    | Label for the address (e.g., Home, Office) |
| `locality`  | VARCHAR(255)                    | Locality or neighborhood             |
| `address`   | TEXT                            | Detailed street name and number      |
| `city`      | VARCHAR(100)                    | City name                            |
| `state`     | VARCHAR(100)                    | State or province                    |
| `pin_code`  | VARCHAR(20)                     | ZIP or postal code                   |
| `country`   | VARCHAR(100)                    | Country name                         |
| `mobile`    | VARCHAR(20)                     | Contact number associated with the address |
| `created_at`| TIMESTAMP DEFAULT CURRENT_TIMESTAMP | When the address was added           |
| `updated_at`| TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | Last update timestamp |
| `user_id`   | BIGINT (FK)                     | References the user who owns the address |

### **Relationships with Other Tables**

The `Address` table typically interacts with multiple entities in a relational database.

- **User (`1:N`)**: A **user can have multiple addresses**, but each address belongs to a single user.
- **Order (`1:1` or `1:N`)**: An order may have one **shipping address** and one **billing address**.
- **Warehouse (`1:N`)**: A company may have multiple **warehouse locations** stored in the `Address` table.
- **Company (`1:N`)**: Businesses can have multiple registered offices.

### **SQL Schema for `Address` Table**

```sql
CREATE TABLE `address` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255),
    `locality` VARCHAR(255),
    `address` TEXT NOT NULL,
    `city` VARCHAR(100),
    `state` VARCHAR(100),
    `pin_code` VARCHAR(20),
    `country` VARCHAR(100),
    `mobile` VARCHAR(20),
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `user_id` BIGINT NOT NULL,
    CONSTRAINT `fk_address_user`
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE CASCADE
); 
```

### **Entity Class Representation in Java**

```java
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String locality;
    private String address;
    private String city;
    private String state;
    private String pinCode;
    private String country;
    private String mobile;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;    
}
```

### **Additional Considerations**
  
  + **Indexing:** Add indexes on `user_id` for faster lookups.
  + **Validation:** Ensure valid formats for `pin_code`, `mobile`, and `country`.
  + **Geolocation:** Store latitude & longitude for mapping.
  + **Soft Deletes:** Use a `deleted_at` field for logical deletion.

The `Address` table plays a vital role in database models by providing structured, reusable, and relational address data. By normalizing it as a separate table, **data duplication is reduced, relationships are well-defined, and querying is optimized.** 🚀
