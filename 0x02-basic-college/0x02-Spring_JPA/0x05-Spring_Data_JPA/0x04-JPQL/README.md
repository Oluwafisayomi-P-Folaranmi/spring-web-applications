## JPQL

In Spring Data JPA, extending and adding custom queries with JPQL (Java Persistence Query Language) allows you to define more complex queries that go beyond the basic CRUD operations provided by the repository interfaces. Here's a step-by-step guide on how to do this:

1. **Define a Repository Interface**:
   Create a repository interface for your entity that extends the `JpaRepository` interface or any other relevant repository interface provided by Spring Data JPA.

   ```java
   import org.springframework.data.jpa.repository.JpaRepository;
   import org.springframework.data.jpa.repository.Query;
   import org.springframework.data.repository.query.Param;
   import java.util.List;

   public interface UserRepository extends JpaRepository<User, Long> {
       
       @Query("SELECT u FROM User u WHERE u.lastName = :lastName")
       List<User> findByLastName(@Param("lastName") String lastName);
   }
   ```

2. **Write a JPQL Query**:
   Use the `@Query` annotation to write a JPQL query. The example above demonstrates how to write a query that selects users based on their last name.

3. **Use Query Parameters**:
   In the `@Query` annotation, you can use named parameters by prefixing them with a colon (`:`). Use the `@Param` annotation to bind method parameters to query parameters.

4. **Execute the Query**:
   Once you've defined your custom query in the repository interface, you can call it like any other method provided by the repository.

   ```java
   @Autowired
   private UserRepository userRepository;

   public void someMethod() {
       List<User> users = userRepository.findByLastName("Smith");
       // Do something with the result
   }
   ```

By using JPQL in this way, you can create custom queries that take advantage of the full power of the Java Persistence API. You can write more complex queries, join multiple entities, and perform aggregations.

Is there a specific query you're trying to create, or do you have any further questions about using JPQL with Spring Data JPA?
