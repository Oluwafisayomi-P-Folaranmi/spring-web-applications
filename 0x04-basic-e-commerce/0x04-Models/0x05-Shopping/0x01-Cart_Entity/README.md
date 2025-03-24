## **Cart Entity**: `01:44:00`

### **Purpose of the `Cart` Table**

The `cart` table is a core component of an e-commerce system that stores temporary selections of products before a customer proceeds to checkout. It ensures a seamless shopping experience by allowing users to:

  + **Select products** before finalizing a purchase.
  + **Adjust quantities** of items.
  + **Save items for later purchase** (if implemented).
  + **Apply discounts or promotions** before checkout.

#### **Types of Shopping Carts**

  + **Persistent Cart**: Items remain saved even after a user logs out.
  + **Session-Based Cart**: Items are temporarily stored and lost when the session expires.

### **Common Fields in a `Cart` Table**

A well-structured `cart` table typically includes the following fields:

| Column Name        | Data Type               | Description |
|--------------------|------------------------|-------------|
| `id`              | BIGINT (Primary Key, Auto-increment) | Unique identifier for each cart. |
| `total_selling_price`         | DOUBLE    | Stores the final selling price after applying discounts.. |
| `total_item`         | INT    | Stores the total number of unique items in the cart.. |
| `total_mrp_price`         | INT     | Stores the original total price before applying discounts. |
| `discount`         | INT     | Stores the total discount applied.. |
| `coupon_code`         | VARCHAR(50)     | Stores any applied coupon codes. |
| `created_at`      | DATETIME DEFAULT NOW()  | Timestamp when the cart was created. |
| `updated_at`      | DATETIME ON UPDATE NOW() | Last modification timestamp. |
| `user_id`         | BIGINT (Foreign Key)    | Links the cart to a specific user. |

### **Relationships with Other Tables**

The `cart` table interacts with multiple tables to function effectively:

#### **One-to-One Relationship with `User`**

  + A single user **can have single cart**. We will use a one-to-one relationship with `user` in our project.
  + Each cart **belongs to a single user**.

#### **Many-to-One Relationship with `User`**

  + A single user **can have multiple carts** (e.g., previous abandoned carts).
  + Each cart **belongs to a single user**.

#### **Many-to-Many Relationship with `Product` (via `cart_item`)**

  + A cart **can contain multiple products**.
  + A product **can appear in multiple carts**.

#### **One-to-One Relationship with `Order`**

  + Once a cart is checked out, it **can be converted into an order**.

### **SQL Schema**

Below is the SQL schema for the `cart` table and its related `cart_items` table:

```sql
CREATE TABLE `cart` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `total_selling_price` DOUBLE NOT NULL CHECK (`total_selling_price` >= 0),
    `total_item` INT NOT NULL CHECK (`total_item` >= 0),
    `total_mrp_price` INT NOT NULL CHECK (`total_mrp_price` >= 0),
    `discount` INT DEFAULT 0 CHECK (`discount` >= 0),
    `coupon_code` VARCHAR(50),
    `created_at` DATETIME DEFAULT NOW(),
    `updated_at` DATETIME DEFAULT NULL ON UPDATE NOW(),
    `user_id` BIGINT UNIQUE NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE CASCADE
);
```

### **Entity Class**

```java
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>();

    @Column(name = "total_selling_price", nullable = false)
    private double totalSellingPrice;

    @Column(name = "total_item", nullable = false)
    private int totalItem;

    @Column(name = "total_mrp_price", nullable = false)
    private int totalMrpPrice;

    @Column(name = "discount", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int discount;

    @Column(name = "coupon_code", length = 50)
    private String couponCode;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
```

  1. **`@OneToOne` Relationship (`user_id`)**  
     - Ensures **each user has only one cart**.  
     - `@JoinColumn(name = "user_id", nullable = false, unique = true)` defines the foreign key.  

  2. **Constraints & Validations**
     - `nullable = false` ensures mandatory fields.  
     - `columnDefinition = "INT DEFAULT 0"` sets a default value for `discount`.  

  3. **Timestamp Management (`created_at` & `updated_at`)**
     - `@PrePersist` initializes `createdAt` on entity creation.  
     - `@PreUpdate` updates `updatedAt` whenever the entity is modified.  

### **Other Considerations**

#### ✅ **Additional Features**

  + **Cart Expiry Mechanism** → Implement **TTL (Time to Live)** for abandoned carts.
  + **Discount & Coupons Integration** → Allow users to apply **coupon codes**.
  + **Wishlist Integration** → Enable saving items for later.
  + **Multi-Currency Support** → Store prices based on **user location**.

#### ✅ **Performance Optimizations**
  
  + **Indexing on `user_id` & `updated_at`** → Faster retrieval.
  + **Partitioning by `status`** → Improve querying performance.

### **Summary**

  + The `cart` table is essential for managing **temporary product selections**.
  + It stores **user selections, quantities, and prices** before checkout.
  + A **many-to-many relationship** with `Product` ensures flexibility.
  + The **SQL schema** and **Java entity classes** allow seamless integration.
  + Additional features like **discounts, expiry, and performance tuning** make it **scalable**.
