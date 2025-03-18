# Overview of Fetch Types - Eager vs Lazy Loading

When we load or retrieve data, a key question arises: should we retrieve everything upfront?

  + **Eager**:
    - retrieve all related data at once, while 
  + **Lazy**:
    - retrieves data only when it's specifically requested.


### Eager Loading

Imagine we have an instructor who has a list of courses. The choice of loading type determines when and how this data is loaded from the database by Hibernate. This decision has significant implications for the performance of your application.

With eager loading, all dependent entities, such as the instructor and their courses, are loaded together in one quick operation. This might not be a problem with a small amount of data, but if there is a large dataset, it could negatively impact your application's performance.

Another example is: consider a course with many students. If eager loading is used, loading a course will also load all the students enrolled in that course. This can quickly become a performance issue, especially if the course has thousands of students. If you're just searching for courses by a keyword, eager loading will still retrieve all associated students, which is unnecessary and inefficient. In this case, you only want the course titles or descriptions, not the entire student list. Therefore, the industry best practice is to load data only when absolutely necessary, favoring lazy loading over eager loading. Of course, there are exceptions and special cases, but generally, lazy loading is recommended.


### Lazy Loading

With lazy loading, the main entity is loaded first, and any dependent entities are loaded only when needed. For instance, a course would be loaded initially, and the list of students would be fetched from the database only when required. This approach helps ensure that your application performs efficiently, making lazy loading the preferred choice in most situations.

Imagine we have a educational website, which provides a list of instructors. Users can search for instructors by name. In this scenario, we only want a high-level list of instructors (that is, only the list of instructors). Then there might be an action link that allows users to drill down and view detailed information about a specific instructor.

In our use case, we utilize lazy loading in the master view. The master view is where we display the list of instructors without retrieving their associated courses. We focus solely on the instructors themselves, without any unnecessary data. This results in a faster query on our database. If users want to see more details, like the courses an instructor teaches, they can click the "view details" link, which takes them to a detail view. In the detail view, we retrieve both the instructor and their dependent entities, such as their courses. For example, if we're viewing the details of an instructor named John Doe, we retrieve his email address and a list of the courses he teaches. This approach works well because we're only dealing with one instructor and their specific courses, not thousands of instructors and their courses. It's a great example of how to efficiently load data in a master-detail view setup.

Consequently, when you use lazy loading, data is retrieved only on demand, but this requires an open Hibernate session, meaning you need a connection to the database when retrieving data later. If the Hibernate session is closed and you attempt to retrieve lazy data, Hibernate will throw an exception.


## Fetch Types

Now, let's discuss fetch types. When defining the mapping relationship between two entities or classes, you can specify the fetch type as either `EAGER` or `LAZY`. For example, consider an instructor who has a list of courses. We can define this relationship using the `@OneToMany` annotation and set the fetch type to `FetchType.LAZY`.


```java
@OneToMany(fetch = FetcType.LAZY, mappedBy = "instructor")
private List<Course> courses;

```

This means that the instructor is loaded first, and the courses are only loaded when needed, in a lazy fashion.


## Default Fetch Types

You might wonder what happens if we don't specify a fetch type. In that case, the default fetch type is used. For example, in a 

  + One-to-one: eager
  + One-to-many: lazy
  + Many-to-one: eager 
  + Many-to-many: lazy.


## Overriding Default Fetch Types

You can override by explicitly setting the fetch type. This straightforward approach allows you to control how data is loaded in your application.
