## **Coupon Entity**: `01:37:00`

### **Purpose of the `Coupon` Table**

The `Coupon` table is a crucial part of an e-commerce or retail database system, designed to manage promotional discounts offered to customers. 

#### **Key Roles of the `Coupon` Table:**

  + **Discount Management**: Offers percentage-based or fixed discounts to customers.
  + **Promotion Tracking**: Stores details of marketing campaigns.
  + **Customer Incentives**: Encourages purchases through discounts.

### **Common Fields in a `Coupon` Table**

A well-structured `Coupon` table contains fields that define its behavior, restrictions, and relationships with other tables.

| Column Name            | Data Type            | Description |
|------------------------|---------------------|-------------|
| `id`                  | BIGINT (Primary Key, Auto-increment) | Unique identifier for each coupon. |
| `code`                | VARCHAR(50) UNIQUE NOT NULL | The alphanumeric code customers enter at checkout. |
| `discount_percentage`  | DOUBLE NOT NULL      | The percentage discount applied to the order. |
| `validity_start_date`  | DATETIME NOT NULL    | The start date for coupon validity. |
| `validity_end_date`    | DATETIME NOT NULL    | The expiration date for the coupon. |
| `minimum_order_value`  | DOUBLE NOT NULL      | The minimum purchase amount required to apply the coupon. |
| `active`              | BOOLEAN DEFAULT TRUE | Indicates if the coupon is currently active. |

### **Relationships with Other Tables**

The `Coupon` table interacts with multiple tables in a relational database:

#### **Many-to-Many Relationship with `User`**

  + Users can apply multiple coupons across different orders.
  + The same coupon can be used by multiple users (if applicable).
  + A **junction table** (`user_coupons`) is used to track coupon usage.

### **SQL Schema**

Here’s the SQL schema for the `coupon` table along with the many-to-many relationship with `user`:

```sql
CREATE TABLE `coupon` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `code` VARCHAR(50) UNIQUE NOT NULL,
    `discount_percentage` DOUBLE NOT NULL,
    `validity_start_date` DATETIME NOT NULL,
    `validity_end_date` DATETIME NOT NULL,
    `minimum_order_value` DOUBLE NOT NULL,
    `active` BOOLEAN DEFAULT TRUE
);

CREATE TABLE `user_coupon` (
    `user_id` BIGINT,
    `coupon_id` BIGINT,
    PRIMARY KEY (`user_id`, `coupon_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`coupon_id`) REFERENCES `coupon`(`id`) ON DELETE CASCADE
);
```

### **Entity Class**

The corresponding Java entity class for `coupon`:

```java
@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private double discountPercentage;

    @Column(nullable = false)
    private LocalDateTime validityStartDate;

    @Column(nullable = false)
    private LocalDateTime validityEndDate;

    @Column(nullable = false)
    private double minimumOrderValue;

    private boolean active = true;

    @ManyToMany(mappedBy = "usedCoupons")
    private Set<User> usedByUsers = new HashSet<>();
}
```

### **Other Considerations**

Here are additional enhancements and best practices to improve the `Coupon` table:

#### **✅ Additional Fields to Consider**

  + **`max_usage_limit`** → Limits how many times a coupon can be used overall.
  + **`user_limit`** → Defines how many times a single user can use the coupon.
  + **`discount_type`** → Specifies whether the discount is a **percentage** or a **fixed amount**.
  + **`applicable_products`** → Links coupons to specific product categories.

#### **✅ Enhancements for Better Performance**

  + **Indexes on `code`, `validity_end_date`** for faster lookups.
  + **Partitioning by `validity_end_date`** to improve performance in large databases.
  + **Triggers to enforce usage limits** (as MySQL does not support `CHECK` constraints referencing another table).

### **Summary**
- The `Coupon` table helps manage **discounts and promotions** in an e-commerce system.
- It has key fields like `code`, `discount_percentage`, and `validity_end_date`.
- **Many-to-Many** relationships are used to track coupon usage by users.
- A well-designed schema ensures **efficiency**, **scalability**, and **business rule enforcement**.
