## **Address Entity**

The `Address` table in a database model represents **location-based information** related to users, customers, sellers, or businesses. It is commonly used in **e-commerce, logistics, and user management systems** to store delivery and billing addresses.

---

### **Key Aspects of the `Address` Table**
1. **Normalization & Reusability**  
   - Instead of storing address details directly in the `User` table, a **separate `Address` table** ensures better database design by avoiding redundancy.
   - Users can have **multiple addresses** (e.g., home, office).
   - Addresses can be **linked to multiple entities** (e.g., customers, sellers, warehouses).

2. **Relationships**  
   - Typically, the `Address` table has a **one-to-many (1:M) relationship** with the `User` table, where one user can have multiple addresses.
   - It may also relate to orders for **shipping & billing addresses**.

---

### **Common Fields in an `Address` Table**
| Column Name       | Data Type        | Description |
|------------------|----------------|-------------|
| `id`            | BIGINT (PK)     | Unique identifier for the address |
| `user_id`       | BIGINT (FK)     | References the user who owns the address |
| `street`        | VARCHAR(255)    | Street name and number |
| `city`          | VARCHAR(100)    | City name |
| `state`         | VARCHAR(100)    | State or province |
| `postal_code`   | VARCHAR(20)     | ZIP or postal code |
| `country`       | VARCHAR(100)    | Country name |
| `address_type`  | ENUM('HOME', 'OFFICE', 'OTHER') | Type of address |
| `created_at`    | TIMESTAMP       | When the address was added |
| `updated_at`    | TIMESTAMP       | Last update timestamp |

---

### **Example SQL Schema for `Address` Table**
```sql
CREATE TABLE `Address` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT NOT NULL,
    `street` VARCHAR(255) NOT NULL,
    `city` VARCHAR(100) NOT NULL,
    `state` VARCHAR(100) NOT NULL,
    `postal_code` VARCHAR(20) NOT NULL,
    `country` VARCHAR(100) NOT NULL,
    `address_type` ENUM('HOME', 'OFFICE', 'OTHER') NOT NULL DEFAULT 'HOME',
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`user_id`) REFERENCES `User`(`id`) ON DELETE CASCADE
);
```

---

### **Usage in E-Commerce & Logistics**
- **E-Commerce:** Stores **billing and shipping addresses** for customers.
- **Logistics:** Helps manage delivery locations for orders.
- **Multi-User Systems:** Users can store multiple addresses for different purposes.

Would you like an example query to retrieve a user's addresses? 🚀
