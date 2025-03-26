# **Shopping**

The **Cart** table in a database model is commonly used in e-commerce applications to track items that a user intends to purchase before completing a transaction. It acts as a temporary storage for selected products, allowing users to review, update, or remove items before placing an order.

### **Key Concepts of the Cart Table**

1. **User Association**  
   - Each cart is usually linked to a specific user, either through a `user_id` in a relational database or a session ID for anonymous users.
   
2. **Products in the Cart**  
   - The cart holds multiple products, each represented as a row in the `cart_item` table if normalized.

3. **Temporary vs Persistent Storage**  
   - Some carts are session-based and are cleared after checkout or after a certain inactivity period.
   - Others persist across user sessions, allowing users to return and complete purchases later.

---

### **Database Schema Example**

A typical e-commerce database would have two related tables:
1. **Cart Table (Holds general cart metadata)**
2. **Cart Items Table (Holds individual product details in the cart)**
