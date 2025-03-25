## **Category Entity**

### **1. Purpose of the `category` Table**  
The `category` table in an e-commerce database model organizes products into hierarchical classifications. It enables efficient product filtering, navigation, and retrieval by grouping similar items together, making it easier for customers to browse and search for products.

---

### **2. Common Fields in a `category` Table**  

| **Column Name**   | **Data Type**            | **Description**                              |
|-------------------|-------------------------|----------------------------------------------|
| `id`             | `BIGINT`                  | Unique identifier for the category.         |
| `name`           | `VARCHAR(255)`            | Name of the category (e.g., Electronics).   |
| `category_id`    | `VARCHAR(100)`            | Unique identifier for the category.         |
| `parent_category_id` | `BIGINT`              | Links to a parent category (if applicable).|
| `level`          | `INT`                     | Category hierarchy level (e.g., 1 for root).|

---

### **3. Relationships with Other Tables**  

- **Parent-Child Relationship (`parent_category_id`)** → A category can have subcategories (**Self-Referencing Many-to-One**).  
- **Product (`product.category_id`)** → A category can have multiple products, but a product belongs to only one category (**One-to-Many**).  
- **Category Tree** → Used for multi-level category structures in e-commerce applications.

---

### **4. SQL Schema**  
```sql
CREATE TABLE category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category_id VARCHAR(100) UNIQUE NOT NULL,
    parent_category_id BIGINT,
    level INT NOT NULL,
    FOREIGN KEY (parent_category_id) REFERENCES category(id)
);
```

---

### **5. Entity Class (Java – Spring Boot JPA)**  
```java
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(unique = true, nullable = false)
    private String categoryId;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @NotNull
    private Integer level;
}
```

---

### **6. Other Things to Consider**  

- **Indexing:**  
  - Add an index on `category_id` and `parent_category_id` for faster lookup.  
  ```sql
  CREATE INDEX idx_category_parent ON category(parent_category_id);
  ```

- **Category Hierarchy Representation:**  
  - Use **adjacency list**, **nested set model**, or **closure table** to efficiently manage hierarchical categories.

Would you like assistance with category-related queries or service implementation? 🚀
