## **CartItem Entity**

#### **2. Cart Items Table**
```sql
CREATE TABLE cart_items (
    id SERIAL PRIMARY KEY,
    cart_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES cart(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);
```
- `cart_id`: Links each item to a specific cart.
- `product_id`: References the product being added.
- `quantity`: Number of units selected.
- `price`: Stores the price at the time of adding the product.

---

### **Additional Considerations**
- **Discounts & Coupons**: A separate table (`cart_discounts`) may track discounts applied.
- **Auto-expiration**: Some systems clear inactive carts after a certain period.
- **Inventory Checks**: Ensure product availability before checkout.
- **Session-based Carts**: For guest users, the cart may be tracked via a session ID.

Would you like to explore advanced cart features, such as real-time price updates or inventory synchronization?

