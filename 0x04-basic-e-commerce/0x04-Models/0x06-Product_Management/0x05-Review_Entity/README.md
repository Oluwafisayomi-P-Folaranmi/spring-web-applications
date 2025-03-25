## **Review Entity**

### **1. Purpose of the `review` Table**  
The `review` table stores customer feedback on products. It allows users to share their experiences, provide ratings, and upload images, helping other customers make informed purchasing decisions. Reviews also contribute to product rankings and recommendations.

---

### **2. Common Fields in a `review` Table**  

| **Column Name**  | **Data Type**            | **Description**                              |
|------------------|-------------------------|----------------------------------------------|
| `id`            | `BIGINT`                  | Unique identifier for the review.           |
| `review_text`   | `TEXT`                    | Text content of the review.                 |
| `rating`        | `DECIMAL(2,1)`            | Star rating given by the user (e.g., 4.5).  |
| `product_id`    | `BIGINT`                  | References the product being reviewed.      |
| `user_id`       | `BIGINT`                  | References the user who wrote the review.   |
| `created_at`    | `TIMESTAMP`               | Timestamp when the review was created.      |

---

### **3. Relationships with Other Tables**  

- **Product (`product_id`)** → A review belongs to a single product, but a product can have multiple reviews (**Many-to-One**).  
- **User (`user_id`)** → A review belongs to a user, but a user can write multiple reviews (**Many-to-One**).  

---

### **4. SQL Schema**  
```sql
CREATE TABLE review (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    review_text TEXT NOT NULL,
    rating DECIMAL(2,1) NOT NULL CHECK (rating >= 0 AND rating <= 5),
    product_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);
```

---

### **5. Entity Class (Java – Spring Boot JPA)**  
```java
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String reviewText;

    @Column(nullable = false)
    private double rating;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
```

---

### **6. Other Things to Consider**  

- **Indexes:**  
  - Adding indexes on `product_id` and `user_id` improves query performance.  
  ```sql
  CREATE INDEX idx_review_product ON review(product_id);
  CREATE INDEX idx_review_user ON review(user_id);
  ```

- **Aggregated Rating Calculation:**  
  - Store and update the average rating in the `product` table to improve performance.  

- **Review Moderation:**  
  - Implement a flagging system for inappropriate reviews.  

Would you like a repository and service layer for this? 🚀
