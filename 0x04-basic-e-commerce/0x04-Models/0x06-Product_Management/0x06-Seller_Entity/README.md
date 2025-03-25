## **Seller Entity**

### **1. Purpose of the `seller` Table**  
The `seller` table stores information about sellers who list products in an e-commerce platform. It helps manage seller details, including contact information, business details, and banking information, ensuring smooth transactions and seller verification.

---

### **2. Common Fields in a `seller` Table**  

| **Column Name**   | **Data Type**            | **Description**                           |
|-------------------|-------------------------|-------------------------------------------|
| `id`             | `BIGINT`                  | Unique identifier for the seller.         |
| `seller_name`    | `VARCHAR(255)`            | Name of the seller or business.          |
| `mobile`         | `VARCHAR(20)`             | Contact number of the seller.            |
| `email`          | `VARCHAR(255)`            | Unique email address of the seller.      |
| `password`       | `VARCHAR(255)`            | Hashed password for authentication.      |
| `business_name`  | `VARCHAR(255)`            | Legal business name.                     |
| `GSTIN`          | `VARCHAR(20)`             | GST Identification Number.               |
| `account_status` | `ENUM('PENDING_VERIFICATION', 'ACTIVE', 'SUSPENDED')` | Status of the seller's account. |
| `is_email_verified` | `BOOLEAN`             | Whether the email is verified.           |
| `created_at`     | `TIMESTAMP`               | Timestamp when the seller registered.    |

---

### **3. Relationships with Other Tables**  

- **Product (`product.seller_id`)** → A seller can list multiple products (**One-to-Many**).  
- **Address (`seller.pickup_address_id`)** → A seller has a pickup address (**One-to-One**).  
- **Orders (`order.seller_id`)** → A seller can be linked to multiple orders (**One-to-Many**).  

---

### **4. SQL Schema**  
```sql
CREATE TABLE seller (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    seller_name VARCHAR(255) NOT NULL,
    mobile VARCHAR(20),
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    business_name VARCHAR(255) NOT NULL,
    GSTIN VARCHAR(20) UNIQUE,
    account_status ENUM('PENDING_VERIFICATION', 'ACTIVE', 'SUSPENDED') DEFAULT 'PENDING_VERIFICATION',
    is_email_verified BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

### **5. Entity Class (Java – Spring Boot JPA)**  
```java
@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String sellerName;

    private String mobile;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String businessName;

    @Column(unique = true)
    private String GSTIN;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus = AccountStatus.PENDING_VERIFICATION;

    private boolean isEmailVerified = false;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
```

---

### **6. Other Things to Consider**  

- **Indexes:**  
  - Indexes on `email` and `GSTIN` for quick lookups.  
  ```sql
  CREATE INDEX idx_seller_email ON seller(email);
  CREATE INDEX idx_seller_gstin ON seller(GSTIN);
  ```
  
- **Security Considerations:**  
  - Store `password` in a hashed format (e.g., BCrypt).  
  - Validate `email` and `GSTIN` to prevent duplicate entries.  

Would you like help with repository and service implementations next? 🚀
