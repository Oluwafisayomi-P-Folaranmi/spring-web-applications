## **Spring Security Model**

**Spring Security is a comprehensive framework that provides authentication, authorization, and other security-related functionalities for Java applications, particularly those built with Spring Boot and Spring Framework**. It defines a structured approach to securing applications by offering various security mechanisms, including:  

  1. **Authentication**:
     + Verifying user identities using credentials such as usernames, passwords, OAuth2 tokens, or other authentication providers (LDAP, JWT, etc.).
     + Using Security Filters
     + Integrating modern authentication mechanisms like OAuth2 and OIDC for secure authentication with identity providers such as Google, Okta, and Keycloak.
  2. **Authorization** – Controlling user access to different parts of an application based on roles, permissions, and security policies, using Security Filters. 
  3. **Session Management** – Handling user sessions securely, including features like session timeout, concurrent session control, and automatic session invalidation on logout.  
  4. **CSRF Protection** – Preventing Cross-Site Request Forgery (CSRF) attacks by ensuring that state-changing requests are authenticated. Managing Cross-Origin Resource Sharing (CORS) policies to regulate access from different domains.
  5. **Integration with Other Spring Modules** – Seamlessly working with Spring Boot, Spring MVC, Spring Data, and other modules to create a secure, scalable application.

### **Security Approaches**

Securing an application can be achieved through two main approaches: **declarative security** and **programmatic security**. These approaches define how security policies, authentication, and authorization are implemented within an application.

### **1. Declarative Security**  

**Declarative security involves configuring security rules *externally*, typically through configuration files, annotations, or frameworks like Spring Security**. This method keeps security concerns separate from business logic, making the application more maintainable and scalable.

#### **Examples of Declarative Security:**

  - XML-based Security (Older Approach)
  - Annotation-based Security (Modern Approach)
  - Spring Security Configuration (Java-based)
  - Access Control Lists (ACLs) and Role-Based Access Control (RBAC)

#### **Advantages of Declarative Security:**

- Separation of concerns: Security logic is defined separately from business logic.
- Easier maintenance: Changes to security rules can be made without modifying code.
- Less error-prone: Standard security configurations reduce the risk of security flaws.

### **2. Programmatic Security**

***Programmatic security is implemented directly in the application code***, where security decisions are made dynamically based on conditions or logic within the application.

#### **Examples of Programmatic Security:**

- Checking User Roles in Code
- Manually Authenticating Users
- Custom Security Logic

#### **Advantages of Programmatic Security:**

- Greater flexibility: Allows dynamic, fine-grained control over security based on runtime conditions.
- Custom logic: Security rules can be based on business-specific requirements.
- Context-awareness: Security decisions can consider real-time data (e.g., user behavior, transaction limits).
