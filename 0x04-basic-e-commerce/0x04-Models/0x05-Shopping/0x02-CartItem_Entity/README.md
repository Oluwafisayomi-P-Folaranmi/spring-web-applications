## **CartItem Entity**

### ****Common Fields in a `CartItem` Table****

In addition to `cart` table, a **`cart_item`** table is required to store product details.

| Column Name       | Data Type               | Description |
|-------------------|------------------------|-------------|
| `id`        | BIGINT (Foreign Key)    | Links the item to a cart. |
| `cart_id`        | BIGINT (Foreign Key)    | Links the item to a cart. |
| `product_id`     | BIGINT (Foreign Key)    | References the product being added. |
| `size`        | VARCHAR(50)     | Links the item to a cart. |
| `quantity`       | INT NOT NULL            | Number of units added. |
| `mrp_price`        | INT     | Links the item to a cart. |
| `selling_price`        | INT (Foreign Key)    | Links the item to a cart. |
| `user_id`        | BIGINT     | Links the item to a cart. |

### **SQL Schema**

```sql
CREATE TABLE `cart_item` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `cart_id` BIGINT NOT NULL,
    `product_id` BIGINT NOT NULL,
    `size` VARCHAR(50),
    `quantity` INT NOT NULL DEFAULT 1 CHECK (`quantity` > 0),
    `mrp_price` INT CHECK (`mrp_price` >= 0),
    `selling_price` INT CHECK (`selling_price` >= 0),
    `user_id` BIGINT NOT NULL,
    FOREIGN KEY (`cart_id`) REFERENCES `cart`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`product_id`) REFERENCES `product`(`id`) ON DELETE CASCADE
);
```

#### **Primary Key**
  
  + `id BIGINT AUTO_INCREMENT PRIMARY KEY`: Ensures each cart item has a unique identifier.

#### **Foreign Keys & Relationships**

  + `cart_id BIGINT NOT NULL`
    - **Many-to-One relationship with `Cart`**.
    - **ON DELETE CASCADE**: If a cart is deleted, all its associated items are removed.
  + `product_id BIGINT NOT NULL`
    - **Many-to-One relationship with `Product`**.
    - **ON DELETE CASCADE**: If a product is deleted, its related cart items are removed.

#### **Other Fields**

  + `size VARCHAR(50)`: Represents the product size (e.g., Small, Medium, Large).
  + `quantity INT NOT NULL DEFAULT 1 CHECK (quantity > 0)`: 
    - Ensures that at least **one item** is added to the cart.
  + `mrp_price INT CHECK (mrp_price >= 0)`: 
    - Prevents negative values for the maximum retail price (MRP).
  + `selling_price INT CHECK (selling_price >= 0)`: 
    - Ensures that the selling price is **not negative**.
  + `user_id BIGINT NOT NULL`: 
    - Stores the **user who added the item** to the cart.

## **Key Features**

  + **Data Integrity**: Constraints ensure valid values (e.g., no negative prices, quantity > 0).  
  + **Referential Integrity**: Foreign keys maintain relationships with `Cart` and `Product`.  
  + **Cascade Deletions**: Prevent orphaned records when a `Cart` or `Product` is removed.

### **Entity Class**

```java
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Cart cart;

    private Product product;

    private String size;

    private int quantity = 1;

    private Integer mrpPrice;

    private Integer sellingPrice;

    private Long userId;
}
```

  1. **Primary Key (`id`)**
     - Auto-generated unique identifier using `@Id` and `@GeneratedValue(strategy = GenerationType.AUTO)`.

  2. **Foreign Key Relationships**
     - `@ManyToOne private Cart cart;`  
       - Each cart item is linked to a **cart**.
       - `@JoinColumn(name = "cart_id", nullable = false)`: Ensures a valid `cart_id` reference.
     - `@ManyToOne private Product product;`
       - Links each cart item to a **product**.
       - `@JoinColumn(name = "product_id", nullable = false)`: Ensures a valid `product_id` reference.

  3. **Size Attribute**
     - `@Column(name = "size", length = 50)`: Stores the size of the product (e.g., **Small, Medium, Large**).

  4. **Quantity with Default Value and Check Constraint**
     - `@Column(name = "quantity", nullable = false, columnDefinition = "INT DEFAULT 1 CHECK (quantity > 0)")`
     - Ensures:
       - Default quantity is **1**.
       - Quantity **cannot be negative or zero**.

  5. **Price Fields with Constraints**
     - `mrpPrice` and `sellingPrice` are defined as:
       ```java
       @Column(name = "mrp_price", columnDefinition = "INT CHECK (mrp_price >= 0)")
       @Column(name = "selling_price", columnDefinition = "INT CHECK (selling_price >= 0)")
       ```
     - This ensures that both prices **cannot be negative**.

  6. **User ID**
     - `@Column(name = "user_id", nullable = false)`:  
       - Stores the **ID of the user** who added this item to the cart.

## **Additional Benefits**
✅ **Data Integrity**: Enforces constraints like **non-negative prices, valid quantity, and required foreign keys**.  
✅ **Referential Integrity**: Prevents orphan records by **cascading deletions** from `Cart` and `Product`.  
✅ **Better Performance**: Uses `@ManyToOne` relationships to prevent unnecessary data duplication.  

### **Additional Considerations**

  + **Discounts & Coupons**: A separate table (`cart_discounts`) may track discounts applied.
  + **Auto-expiration**: Some systems clear inactive carts after a certain period.
  + **Inventory Checks**: Ensure product availability before checkout.
  + **Session-based Carts**: For guest users, the cart may be tracked via a session ID.
