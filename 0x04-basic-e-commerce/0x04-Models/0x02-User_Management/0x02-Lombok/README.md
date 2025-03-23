## **Lombok**

### **Lombok `@EqualsAndHashCode` Annotation**

Lombok's `@EqualsAndHashCode` annotation is used to generate the `equals()` and `hashCode()` methods for a Java class automatically. It helps avoid boilerplate code by ensuring these methods are correctly implemented based on the fields of the class. By default, `@EqualsAndHashCode`:

  + Uses all non-static and non-transient fields to generate `equals()` and `hashCode()`.
  + Ensures that objects of the same class with the same field values are considered equal.
  + Ensures a consistent hash code based on the selected fields.

#### **When to Use `@EqualsAndHashCode`**

  + When you need proper equality checks in collections like `Set` and `Map`.
  + When you want a consistent way to compare objects without manually writing `equals()` and `hashCode()`.
  + When working with entities in frameworks like Hibernate (though caution is needed with proxies and lazy loading).

#### **Customizing `@EqualsAndHashCode`**

You can customize the behavior using optional parameters:

##### **1. Excluding Specific Fields**
If you want to exclude certain fields from `equals()` and `hashCode()`:

```java
@EqualsAndHashCode(exclude = "age")
public class Person {
    private String name;
    private int age;
}
```

Now, `age` will not be considered in equality checks.

##### **2. Using Superclass Fields**

By default, `@EqualsAndHashCode` ignores fields from the superclass. You can include them by setting `callSuper = true`:

```java
@EqualsAndHashCode(callSuper = true)
public class Employee extends Person {
    private String position;
}
```

This ensures `equals()` and `hashCode()` include superclass fields.

🚀
