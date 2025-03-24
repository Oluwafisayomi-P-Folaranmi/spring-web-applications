## **Address Entity**: `01:25:00`

The `Address` model represents **location-based information** related to users, customers, sellers, or businesses. It is commonly **used to store delivery and billing addresses**.

### **Key Aspects of the `Address` Table**

  1. **Normalization & Reusability**  
     - Instead of storing addresses details directly in the `User` table, a **separate `Address` table** ensures better database design by avoiding redundancy.
     - Users can have **multiple addresses** (e.g., home, office).
     - Addresses can be **linked to multiple entities** (e.g., customers, sellers, warehouses).

  2. **Relationships**  
     - Typically, the `Address` table has a **one-to-many (1:N) relationship** with the `User` table, where one user can have multiple addresses.
     - It may also relate to orders for **shipping & billing addresses**.

### **Common Fields in an `Address` Table**

| Column Name   | Data Type                          | Description                           |
|--------------|---------------------------------|---------------------------------------|
| `id`        | BIGINT (PK)                     | Unique identifier for the address    |
| `name`      | BIGINT (FK)                     | References the user who owns the address |
| `locality`  | VARCHAR(255)                    | Locality or neighborhood             |
| `address`   | VARCHAR(255)                    | Street name and number               |
| `city`      | VARCHAR(100)                    | City name                            |
| `state`     | VARCHAR(100)                    | State or province                    |
| `pin_code`  | VARCHAR(20)                     | ZIP or postal code                   |
| `country`   | VARCHAR(100)                    | Country name                         |
| `type`      | ENUM('HOME', 'OFFICE', 'OTHER') | Type of address                      |
| `mobile`    | VARCHAR(20)                     | Mobile phone number                  |
| `created_at`| TIMESTAMP                       | When the address was added           |
| `updated_at`| TIMESTAMP                       | Last update timestamp                |

### **Example SQL Schema for `Address` Table**

```sql
CREATE TABLE `address` (
);
```

### **Entity Class**

```java
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String locality;

    private String address;

    private String city;

    private String state;

    private String pinCode;

    private String mobile; 
}
```

### **Usage in E-Commerce & Logistics**

  - **E-Commerce:** Stores **billing and shipping addresses** for customers.
  - **Logistics:** Helps manage delivery locations for orders.
  - **Multi-User Systems:** Users can store multiple addresses for different purposes.

Would you like an example query to retrieve a user's addresses? 🚀
