## **Spring Security with Servlet Filter**

**Servlet filters are Java components that intercept and process HTTP requests and responses before they reach a servlet or after a servlet has processed them**. They play a crucial role in security by enforcing access controls, authentication, and request modifications.  

### **Servlet Filters are used to pre-process / post-process web requests**

Servlet filters allow developers to manipulate HTTP requests **before** they reach the application’s business logic (pre-processing) and modify HTTP responses **after** the request is handled (post-processing).  

**Examples:**

- **Pre-processing:** Checking authentication tokens, validating user sessions, logging requests.  
- **Post-processing:** Modifying response headers, applying security headers, logging responses.  

### **Servlet Filters can route web requests based on security logic**

Filters can act as **gatekeepers** by redirecting or blocking requests based on security conditions. They determine whether a request should be allowed, denied, or forwarded to another endpoint.  

**Use Cases:**

- Redirecting unauthenticated users to a login page.  
- Blocking requests from suspicious IPs.  
- Enforcing HTTPS redirection for security.  

### **Spring provides a bulk of security functionality with servlet filters** 

Spring Security heavily relies on a chain of **servlet filters** to handle authentication and authorization. These filters automatically secure an application by applying security mechanisms at different stages of request processing.  

**Key Filters in Spring Security:**

- **`UsernamePasswordAuthenticationFilter`** – Handles login authentication.  
- **`BasicAuthenticationFilter`** – Supports HTTP Basic Authentication.  
- **`OAuth2LoginAuthenticationFilter`** – Manages OAuth2 authentication.  
- **`CsrfFilter`** – Protects against Cross-Site Request Forgery (CSRF).  
- **`SecurityContextPersistenceFilter`** – Maintains user security context across requests.  

Spring Security operates as a flexible and extensible framework, allowing developers to customize security configurations to meet the specific needs of their applications while ensuring industry-standard security practices.
