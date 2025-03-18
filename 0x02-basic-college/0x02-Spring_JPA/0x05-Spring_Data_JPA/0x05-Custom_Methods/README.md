## **Custom Method**

In Spring Data JPA, defining custom methods allows you to implement more complex data access logic that cannot be achieved using the standard repository methods or JPQL. Here’s a step-by-step guide on how to define custom methods in Spring Data JPA:

1. **Create a Custom Repository Interface**:
   Define an interface for the custom repository methods. This interface should not extend any of the Spring Data repository interfaces.

   ```java
   public interface UserRepositoryCustom {
       List<User> findUsersByCustomCriteria(String criteria);
   }
   ```

2. **Implement the Custom Repository Interface**:
   Create a class that implements the custom repository interface. This class should contain the actual implementation of the custom methods.

   ```java
   import javax.persistence.EntityManager;
   import javax.persistence.PersistenceContext;
   import javax.persistence.TypedQuery;
   import java.util.List;

   public class UserRepositoryCustomImpl implements UserRepositoryCustom {

       @PersistenceContext
       private EntityManager entityManager;

       @Override
       public List<User> findUsersByCustomCriteria(String criteria) {
           String jpql = "SELECT u FROM User u WHERE u.criteria = :criteria";
           TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
           query.setParameter("criteria", criteria);
           return query.getResultList();
       }
   }
   ```

   In this example, the `UserRepositoryCustomImpl` class contains the implementation of the custom method `findUsersByCustomCriteria`.

3. **Extend the Custom Repository Interface in the Main Repository**:
   Modify your main repository interface to extend the custom repository interface. This allows you to use the custom methods alongside the standard repository methods.

   ```java
   import org.springframework.data.jpa.repository.JpaRepository;

   public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
   }
   ```

4. **Use the Custom Methods**:
   Now you can use the custom methods in your service or controller classes just like any other repository method.

   ```java
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.stereotype.Service;

   import java.util.List;

   @Service
   public class UserService {

       @Autowired
       private UserRepository userRepository;

       public void someMethod() {
           List<User> users = userRepository.findUsersByCustomCriteria("someCriteria");
           // Do something with the result
       }
   }
   ```

By following these steps, you can define and implement custom repository methods that provide more granular control over your data access logic. This approach allows you to handle complex queries, custom data processing, and any other specific requirements that may not be covered by the standard repository methods.

If you have any further questions or need additional examples, feel free to ask!
