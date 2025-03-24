# Models

### Draw the table and relationships with other tables
   1. The database model


### For Each Entity Folder, Write the Details of the Table Entity in the note:

   1. First, go through the resource and implement the entity or class (when necessary).
      1. Give it to the LLM and tell it her view on the entity or class implementation.

   2. Overview/Its work in the model: Ask the LLM a question like: **Tell me about the concept of the <>`User`> table in a database model based on the following headlines**.
      1. Purpose of the <`User`> Table
      2. Common Fields in a <`User`> Table*
      3. Relationships with Other Tables*
      4. SQL Schema*
      5. Entity Class*
      6. Other things you the LLM might want to add.

   3. There will be a need to edit the (`*`):
      1. **Common fields**, **relationships**, **SQL schema**, and **entity class** will be edited for each entity.
      2. Whatever I code from the resource will be the answer to my questions in ***2(1)***.
   
   4. In 2(2)
      1. Generate an sql code from the entity.
      2. Together with sql code, explain the fields, constraints and checks (if any). This explanation is what you will use to populate the tabular structure in the section **Common Fields in a `<table_name>` Table**; and as follow-up explanation under the section **SQL Schema**
      3. Flip the sql again to an entity. While flipping, explain the key features in the entity. This explanation is what you will use as the follow-up explanation under the section ****Entity Class****.



### The Content

  1. User Management
     1. User
     2. VerificationCode
  2. Shipping and Delivery
     1. Address
  3. Promotions
     1. Coupon
  4. Product Management
     1. Product
     2. Category
     3. Review
     4. Seller
     5. SellerReport
  5. Shopping
     1. Cart
     2. CartItem
  6. Order Management
     1. Order
     2. OrderItem
     3. PaymentOrder
     4. Transaction
     5. Wishlist



01:40:00
02:00:00
