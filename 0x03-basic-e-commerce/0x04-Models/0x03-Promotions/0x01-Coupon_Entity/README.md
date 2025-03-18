## **Coupon Entity**

The `Coupon` table in an e-commerce database is designed to store discount vouchers or promotional codes that customers can use to receive discounts on their purchases.

Coupons can have different conditions, such as **minimum order value**, **expiry dates**, and **applicable user categories** (e.g., new users, loyal customers).

### **Purpose of the `Coupon` Table**

The `Coupon` table helps manage:
- **Discounts & Promotions** – Apply percentage-based or fixed-value discounts.
- **Usage Restrictions** – Limit coupons to certain users, products, or order amounts.
- **Redemption Tracking** – Ensure a coupon is used only within its valid period and under specified conditions.
- **Business Analytics** – Track coupon usage to measure the effectiveness of promotions.

### **Schema for `Coupon` Table**

A **well-structured** `Coupon` table in a **relational database** (e.g., MySQL) might look like this:

```sql
CREATE TABLE Coupon (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) UNIQUE NOT NULL,  -- Unique coupon code
    description TEXT,                  -- Description of the coupon
    discount_type ENUM('PERCENTAGE', 'FIXED') NOT NULL,  -- Type of discount
    discount_value DECIMAL(10,2) NOT NULL, -- Discount amount or percentage
    min_order_value DECIMAL(10,2),    -- Minimum order amount required
    max_discount DECIMAL(10,2),       -- Maximum discount limit for percentage coupons
    usage_limit INT DEFAULT 1,        -- Max times a user can use the coupon
    total_usage INT DEFAULT 0,        -- How many times the coupon has been used
    valid_from DATETIME NOT NULL,     -- Start date
    valid_to DATETIME NOT NULL,       -- Expiry date
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

### **Relationship**

Since multiple users can use the same coupon **(if applicable)**, and users may use multiple coupons over time, a **junction table** (`User_Coupon`) is needed:

```sql
CREATE TABLE `Coupon` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `code` VARCHAR(255) NOT NULL,
    `discountPercentage` DOUBLE NOT NULL,
    `validityStartDate` DATETIME NOT NULL,
    `validityEndDate` DATETIME NOT NULL,
    `minimumOrderValue` DOUBLE NOT NULL,
    `active` BOOLEAN NOT NULL DEFAULT TRUE
);
```

This table:

  + Tracks which users have used which coupons.
  + Prevents users from reusing one-time-use coupons.
  + Helps in reporting coupon usage.

### **Entity Class**

```java
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;

    private double discountPercentage;

    private LocalDateTime validityStartDate;

    private LocalDateTime validityEndDate;

    private double minimumOrderValue;

    private boolean active = true;

    @ManyToMany(mappedBy = "usedCoupons")
    private Set<User> usedByUsers = new HashSet<>();
}
```

The `discountPercentage` is used as the amount of discount the user will get after using the coupon. The validity dates are used to track the available and expiry dates of the coupon.

If the user is applying this coupon, what will be the minimum order value, i.e., the lowest amount a customer must spend to qualify for a discount, coupon, free shipping, or other promotional offers. This is what the field `minimumOrderValue` stores.

The admin can make the coupon `active` or deactivate it.

We have created the relationships between the `Coupon` class and the other class:

  + **Many-to-Many** with `User`: We have annotated the `usedByUsers` field as `@ManyToMany(mappedBy = "usedCoupons")`, which map it to the `usedCoupons` field in `User` class. Here’s the corrected version with improved clarity:  

Using the `@ManyToMany` annotation, Hibernate **automatically creates a junction table** in the database to manage the many-to-many relationship. Therefore, while you do not need to define an extra table manually, Hibernate **will generate** a separate junction table to store the relationship between the two entities. The actual name of the resulting table depends on ***JPA's default naming strategy*** or any explicit configurations you define.

### **Possible Enhancements**

  + **Category-Based Coupons** – Apply coupons to specific product categories.
  + **Product-Specific Coupons** – Only allow discounts on certain products.
  + **Referral Coupons** – Coupons granted through referral programs.
  + **Stackable Discounts** – Allow multiple coupons on a single order (if supported).  

### **Use Cases**

| Scenario | SQL Query |
|----------|----------|
| Get all active coupons | `SELECT * FROM Coupon WHERE valid_from <= NOW() AND valid_to >= NOW();` |
| Check if a user has used a coupon | `SELECT * FROM User_Coupon WHERE user_id = 5 AND coupon_id = 10;` |
| Apply a coupon to an order | `UPDATE User_Coupon SET used_at = NOW() WHERE user_id = 5 AND coupon_id = 10;` |

The `Coupon` table is crucial for **marketing and promotions** in an e-commerce system.

It should be designed to **handle multiple use cases** while maintaining **efficiency and scalability**.

**Business rules**, such as usage limits, expiry dates, and applicable users, should be **strictly enforced** at the database level.

🚀
