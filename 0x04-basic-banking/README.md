# README.md

### **Database Schema for the Banking Monolith Application**  

Here’s a structured schema design using **Spring Data JPA**, covering users, accounts, transactions, loans, and security logs.  

---

## **1️⃣ User Table (`users`)**
**Stores customer and admin details.**  
```sql
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) CHECK (role IN ('CUSTOMER', 'ADMIN', 'TELLER')) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
🔹 *Relationships:*  
- One-to-Many with **accounts** (A user can have multiple accounts).  
- One-to-Many with **loans** (A user can apply for multiple loans).  

**JPA Entity:**  
```java
@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // CUSTOMER, ADMIN, TELLER

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Account> accounts;
}
```
---

## **2️⃣ Account Table (`accounts`)**
**Stores different types of bank accounts.**  
```sql
CREATE TABLE accounts (
    id BIGSERIAL PRIMARY KEY,
    account_number VARCHAR(20) UNIQUE NOT NULL,
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    account_type VARCHAR(20) CHECK (account_type IN ('SAVINGS', 'CURRENT', 'LOAN')),
    balance DECIMAL(15,2) NOT NULL DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
🔹 *Relationships:*  
- Many-to-One with **users** (A user can have multiple accounts).  
- One-to-Many with **transactions** (An account can have multiple transactions).  

**JPA Entity:**  
```java
@Entity
@Table(name = "accounts")
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountType accountType; // SAVINGS, CURRENT, LOAN

    @Column(nullable = false)
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
```
---

## **3️⃣ Transactions Table (`transactions`)**
**Stores all deposits, withdrawals, and transfers.**  
```sql
CREATE TABLE transactions (
    id BIGSERIAL PRIMARY KEY,
    account_id BIGINT REFERENCES accounts(id) ON DELETE CASCADE,
    amount DECIMAL(15,2) NOT NULL,
    transaction_type VARCHAR(20) CHECK (transaction_type IN ('DEPOSIT', 'WITHDRAWAL', 'TRANSFER')),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
🔹 *Relationships:*  
- Many-to-One with **accounts** (An account can have multiple transactions).  

**JPA Entity:**  
```java
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType; // DEPOSIT, WITHDRAWAL, TRANSFER

    @Column(nullable = false)
    private BigDecimal amount;

    @CreationTimestamp
    private LocalDateTime timestamp;
}
```
---

## **4️⃣ Loans Table (`loans`)**
**Stores all loan applications and approvals.**  
```sql
CREATE TABLE loans (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    loan_amount DECIMAL(15,2) NOT NULL,
    remaining_balance DECIMAL(15,2) NOT NULL,
    status VARCHAR(20) CHECK (status IN ('PENDING', 'APPROVED', 'REJECTED')),
    start_date DATE NOT NULL,
    due_date DATE NOT NULL
);
```
🔹 *Relationships:*  
- Many-to-One with **users** (A user can have multiple loans).  

**JPA Entity:**  
```java
@Entity
@Table(name = "loans")
public class Loan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User borrower;

    @Column(nullable = false)
    private BigDecimal loanAmount;

    @Column(nullable = false)
    private BigDecimal remainingBalance;

    @Enumerated(EnumType.STRING)
    private LoanStatus status; // PENDING, APPROVED, REJECTED

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate dueDate;
}
```
---

## **5️⃣ Security Logs Table (`audit_logs`)**
**Stores security-related events for compliance.**  
```sql
CREATE TABLE audit_logs (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    event_type VARCHAR(100) NOT NULL,
    event_description TEXT NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
🔹 *Relationships:*  
- Many-to-One with **users** (An audit log is linked to a user).  

**JPA Entity:**  
```java
@Entity
@Table(name = "audit_logs")
public class AuditLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String eventType;

    @Column(nullable = false)
    private String eventDescription;

    @CreationTimestamp
    private LocalDateTime timestamp;
}
```
---

## **Relationships Diagram (ERD)**  
```
+--------------+        +-------------+        +---------------+        +------------+        +---------------+
|    users     | 1 --- n |  accounts  | 1 --- n | transactions |        |   loans    |        |  audit_logs   |
+--------------+        +-------------+        +---------------+        +------------+        +---------------+
| id (PK)      |        | id (PK)     |        | id (PK)       |        | id (PK)    |        | id (PK)       |
| username     |        | account_no  |        | account_id (FK)|        | user_id (FK)|        | user_id (FK)  |
| email        |        | user_id (FK)|        | amount        |        | loan_amount|        | event_type    |
| password     |        | type        |        | type          |        | status     |        | event_desc    |
| role         |        | balance     |        | timestamp     |        | due_date   |        | timestamp     |
+--------------+        +-------------+        +---------------+        +------------+        +---------------+
```
---

### **Next Steps?**
Would you like to:
- **Set up the database with Spring Boot and JPA?**  
- **Implement the User Registration & Authentication logic first?**  
- **Work on account creation and transactions?**  

Let me know how you’d like to proceed! 🚀
