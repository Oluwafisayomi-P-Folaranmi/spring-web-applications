## **Security Approaches**

This appears to be a list of **different ways to implement authentication and authorization** in an application. Let's break them down:

  1. **In-memory** – User credentials and roles are stored directly in memory (RAM) rather than in a database.  
     - Suitable for testing or small applications.  
  2. **JDBC** – Authentication and authorization data are stored in a **relational database (RDBMS)** and accessed using JDBC (Java Database Connectivity).  
     - Common for enterprise applications.  
     - Uses tables like `users`, `roles`, and `authorities`.  
  3. **LDAP (Lightweight Directory Access Protocol)** – Authentication and authorization data are managed in a **centralized directory service** like Active Directory (AD).  
     - Suitable for large organizations managing users across multiple systems.  
  4. **Custom / Pluggable** – Developers can define their own authentication and authorization logic.  
     - Useful when integrating with external APIs, OAuth providers, or non-standard authentication systems.  
  5. **Others…** – Covers additional authentication methods such as:
     - **OAuth 2.0 & OpenID Connect (OIDC):** Used for third-party authentication (Google, Facebook, Okta, etc.).  
     - **JWT (JSON Web Token):** Token-based authentication for stateless microservices.  
     - **SAML (Security Assertion Markup Language):** Enterprise-level Single Sign-On (SSO).  
     - **API Key Authentication:** Using API tokens for machine-to-machine authentication.  

We will cover password storage: In Memory, in DB as plain-text, and in DB as encrypted.
