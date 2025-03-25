## **Product Entity** `01:53:37`

### **Purpose of the `product` Table**  

The `product` table stores details about products available for purchase. It serves as the core entity that holds information such as product name, price, stock levels, and category, enabling efficient product management, retrieval, and display.

### **Common Fields in a `product` Table**  

Here’s the revised table with only the required columns:  

### **Common Fields in a `product` Table**  

| **Column Name**  | **Data Type**            | **Description**                              |
|------------------|-------------------------|----------------------------------------------|
| `id`            | `BIGINT`                  | Unique identifier for the product.          |
| `title`         | `VARCHAR(255)`            | Name of the product.                        |
| `description`   | `TEXT`                    | Detailed description of the product.        |
| `mrp_price`     | `DECIMAL(10,2)`           | Maximum Retail Price.                       |
| `selling_price` | `DECIMAL(10,2)`           | Discounted selling price.                   |
| `discount_percent` | `INT`                  | Percentage discount applied.                |
| `quantity`      | `INT`                     | Number of units available in stock.         |
| `colour`        | `VARCHAR(100)`            | Color of the product.                       |
| `category_id`   | `BIGINT`                  | Links product to a category.                |
| `seller_id`     | `BIGINT`                  | Links product to a seller.                  |
| `created_at`    | `TIMESTAMP`               | Timestamp when the product was created.     |
| `sizes`         | `VARCHAR(255)` or `JSON`  | Available sizes, stored as a list.          |
| `num_ratings`   | `INT`                     | Total number of ratings.                    |

### **Relationships with Other Tables**  

- **Category (`category_id`)** → A product belongs to one category, but a category can have many products (**Many-to-One**).  
- **Seller (`seller_id`)** → A product is associated with a single seller, but a seller can have multiple products (**Many-to-One**).  
- **Reviews (`review.product_id`)** → A product can have multiple reviews (**One-to-Many**).  
- **Orders (`order_item.product_id`)** → A product can appear in multiple orders (**One-to-Many**).  
- **Images (`product_images.product_id`)** → A product can have multiple images (**One-to-Many**).

---

### **4. SQL Schema**  
```sql
CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    mrp_price DECIMAL(10,2) NOT NULL,
    selling_price DECIMAL(10,2) NOT NULL,
    discount_percent INT,
    quantity INT NOT NULL,
    colour VARCHAR(100),
    category_id BIGINT,
    seller_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    sizes VARCHAR(255),
    num_ratings INT DEFAULT 0,
    FOREIGN KEY (category_id) REFERENCES category(id),
    FOREIGN KEY (seller_id) REFERENCES seller(id)
);
```

---

### **5. Entity Class (Java – Spring Boot JPA)**  
```java
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private double mrpPrice;

    @Column(nullable = false)
    private double sellingPrice;

    private int discountPercent;

    @Column(nullable = false)
    private int quantity;

    private String colour;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ElementCollection
    private List<String> sizes = new ArrayList<>();

    private int numRatings;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
}
```

---

### **6. Other Things to Consider**  
- **Indexes:**  
  - Add indexes on `category_id`, `seller_id`, and `title` for faster search and filtering.
  ```sql
  CREATE INDEX idx_product_title ON product(title);
  CREATE INDEX idx_product_category ON product(category_id);
  CREATE INDEX idx_product_seller ON product(seller_id);
  ```

- **Caching Strategy:**  
  - Use Redis or an in-memory database for frequently accessed products.

- **Scalability:**  
  - If the product images or reviews grow large, consider moving them into separate microservices or NoSQL storage.

Would you like additional details, such as repository and service implementations? 🚀
