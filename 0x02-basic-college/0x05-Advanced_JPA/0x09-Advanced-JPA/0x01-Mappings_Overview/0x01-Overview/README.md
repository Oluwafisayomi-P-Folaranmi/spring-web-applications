# Mappings Overview

We will look at three different types of mappings:

   + One-to-One
   + One-to-Many, Many-to-One
   + Many-to-Many

Let's look at them one after the other.


## Types of Mappings


### One-to-One

An instructor may have an "instructor detail" entity which is similar to saying an instructor's profile. So, we have an instructor table and then you have the actual instructor-detail table. The instructor table may contain the instructor's first name, last name, and email. The instructor detail may have just more profile-specific information like: their YouTube channel, hobby, LinkedIn profile, Twitter handle. The key here is that this information is in two separate tables.


### One-to-Many

An instructor can have many courses. So we have the instructor table, and the course table. So, we have an instructor and multiple courses out here that are authored by the instructor. This depicts a classic one-to-many mapping. 

The actual opposite/inverse of this is the Many-to-One mapping. Here, we have many courses that can map to an instructor. 


### Many-to-Many

A course can have many students, and a student can have many courses.


## Important Database Concepts

To take advantage of the concept we have defined from above, we need to review some database concepts. We need to review primary and foreign keys, and then cascade. 


### Primary Key

The primary key identifies a unique row in a table. 


### Foreign Key

The foreign key link tables together. It is a field in one table that refers to the primary key in another table. You have to *register* the field so as to behave like a foreign key.


## Fetch Types: Eager vs Lazy Loading

When we fetch/retrieve data, should we retrieve everything? 

   + Eager will retrieve everything
   + Lazy will retrieve on request


## Relationships/Cardinality


### Unidirectional

Here is an example of making use of a unidirectional relationship. You have an instructor and then you have the instructor detail. You load the instructor, and then from there you can access the instructor detail. So, it is a one-way relationship.


### Bi-Directional

We also have the idea of bi-directional. Here, we have the instructor and the instructor detail. We can also go the other way. We can load the instructor detail and add/have a reference to the given instructor.
