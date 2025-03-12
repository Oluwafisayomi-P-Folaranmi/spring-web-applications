# Cloudinary and Spring


## 2. Setting Up the Project
### Step 1: Create a Spring Boot Project
Use [Spring Initializr](https://start.spring.io/) to create a new project with the following dependencies:
- Spring Web
- Spring Boot DevTools
- Lombok
- Spring Data JPA (for optional database storage)

### Step 2: Add Cloudinary Dependency
Add the Cloudinary Java SDK to your `pom.xml`:
```xml
<dependency>
    <groupId>com.cloudinary</groupId>
    <artifactId>cloudinary</artifactId>
    <version>1.33.0</version>
</dependency>
```

For Gradle, add:
```gradle
implementation 'com.cloudinary:cloudinary:1.33.0'
```

## 3. Configuring Cloudinary in Spring Boot
### Step 3: Get Cloudinary API Credentials
- Sign up at [Cloudinary](https://cloudinary.com/)
- Retrieve your `Cloud Name`, `API Key`, and `API Secret` from the dashboard

### Step 4: Add Cloudinary Configuration
In `application.properties`:
```properties
cloudinary.cloud-name=your_cloud_name
cloudinary.api-key=your_api_key
cloudinary.api-secret=your_api_secret
```

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

## 4. Implementing Image Upload
### Step 5: Create a Service to Handle Uploads
```java
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadImage(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return uploadResult.get("url").toString();
    }
}
```

### Step 6: Create a Controller to Handle Requests
```java
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/cloudinary")
public class CloudinaryController {
    private final CloudinaryService cloudinaryService;

    public CloudinaryController(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = cloudinaryService.uploadImage(file);
            return ResponseEntity.ok(imageUrl);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error uploading file: " + e.getMessage());
        }
    }
}
```

### Step 7: Test the API
Run the application and test using **Postman** or **cURL**:
```bash
curl -X POST http://localhost:8080/api/cloudinary/upload \
     -F "file=@path/to/image.jpg"
```

Would you like to include a **frontend (React/Angular)** to enhance the file upload experience? 🚀
