## **Helper Classes: `BankDetails` and `BusinessDetails`**  

These two classes serve as **embedded value objects** in the `Seller` entity, meaning they are not standalone tables but instead store structured data within the `Seller` table. They help keep the seller-related information well-organized and modular.  

---

### **1. `BankDetails` Class**  
#### **Purpose**  
The `BankDetails` class stores financial information related to a seller, such as their account details for receiving payments. This helps facilitate automated transactions and payouts.  

#### **Fields Explained**  

| **Field Name**       | **Data Type** | **Description**                                      |
|----------------------|--------------|------------------------------------------------------|
| `accountNumber`      | `String`      | Seller's bank account number.                        |
| `accountHolderName`  | `String`      | Name of the account holder.                         |
| `ifscCode`          | `String`      | Bank's IFSC code (for identifying the bank branch). |

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDetails {

    private String accountNumber;

    private String accountHolderName;

    private String ifscCode;
}
```

#### **Usage in `Seller` Entity**  
Since `BankDetails` is marked with `@Embedded`, its fields will be stored directly within the `seller` table instead of having a separate table.  

```java
@Embedded
private BankDetails bankDetails = new BankDetails();
```

---

### **2. `BusinessDetails` Class**  
#### **Purpose**  
The `BusinessDetails` class holds essential business-related information for a seller, such as their name, contact details, and branding materials (logo and banner).  

#### **Fields Explained**  

| **Field Name**      | **Data Type** | **Description**                                 |
|---------------------|--------------|-----------------------------------------------|
| `businessName`     | `String`      | Name of the seller’s business.               |
| `businessEmail`    | `String`      | Official email of the business.              |
| `businessMobile`   | `String`      | Contact number of the business.              |
| `businessAddress`  | `String`      | Registered business address.                 |
| `logo`            | `String`      | URL or file path to the business logo.       |
| `banner`          | `String`      | URL or file path to the business banner.     |

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDetails {

    private String businessName;

    private String businessEmail;

    private String businessMobile;

    private String businessAddress;

    private String logo;

    private String banner;
}
```

#### **Usage in `Seller` Entity**  
This class is also marked as `@Embedded` in the `Seller` entity, meaning its fields will be stored directly in the `seller` table.  

```java
@Embedded
private BusinessDetails businessDetails = new BusinessDetails();
```

---

### **Key Benefits of Using `@Embedded` Classes**  
1. **Better Organization** – Separates business logic into distinct classes instead of cluttering the `Seller` entity.  
2. **Code Reusability** – If other entities need business or banking details, they can reuse these helper classes.  
3. **Database Optimization** – No need for separate joins since all data is stored within the `seller` table.  

Would you like me to suggest improvements, such as validation annotations (`@NotNull`, `@Pattern`)? 🚀
