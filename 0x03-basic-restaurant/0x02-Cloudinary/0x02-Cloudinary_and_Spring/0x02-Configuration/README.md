## **Configuration**

The **Spring Container** uses a custom class, say `CloudinaryConfig` class as a **configuration class** to instantiate and manage the `Cloudinary` object as a **Spring Bean**. Here’s how it works:  

The `@Configuration` annotation marks it as a Configuration class.

```java
@Configuration
public class CloudinaryConfig {
}
```

The `@Configuration` annotation tells Spring that this class contains bean definitions that should be **managed by the Spring IoC container**.

The `@Bean` annotation registers the Cloudinary Object

```java
@Configuration
public class CloudinaryConfig {

   @Bean
   public Cloudinary cloudinary() {
      return new Cloudinary(ObjectUtils.asMap(
           "cloud_name", cloudName,
           "api_key", apiKey,
           "api_secret", apiSecret
      ));
   }
}
```

The `@Bean` annotation tells Spring to **instantiate and manage** a `Cloudinary` object. When Spring initializes the application, it creates and stores a single instance (Singleton Scope by default) of the `Cloudinary` bean. This instance is stored in the **Spring Application Context**.

When the application starts, **Spring Boot scans** for `@Configuration` classes. It processes the `@Bean` method inside `CloudinaryConfig`, creating a `Cloudinary` instance. The `Cloudinary` instance is **stored in the Spring Application Context**. Any class needing `Cloudinary` (like a service class, for example `CloudinaryService`) gets it **automatically injected**.

This **eliminates manual object creation**, making the application modular and maintainable. 🚀

### **Step 1: Get Cloudinary API Credentials**

- Sign up at [Cloudinary](https://cloudinary.com/)
- Retrieve your `Cloud Name`, `API Key`, and `API Secret` from the dashboard

### **Step 4: Add Cloudinary Configuration**

In `application.properties`:

```properties
cloudinary.cloud-name=your_cloud_name
cloudinary.api-key=your_api_key
cloudinary.api-secret=your_api_secret
```

For production, you should save the keys - `your_api_key` and `your_api_secret` - as environment variables.

Create a configuration class:

```java
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    @Value("${cloudinary.api-key}")
    private String apiKey;

    @Value("${cloudinary.api-secret}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", cloudName,
            "api_key", apiKey,
            "api_secret", apiSecret
        ));
    }
}
```
