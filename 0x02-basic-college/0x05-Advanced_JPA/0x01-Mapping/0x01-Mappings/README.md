## **Mappings**

### **One-to-One**

An **`Instructor`** may have an **`InstructorDetail`** entity which is similar to saying an **instructor's profile**.

So in the database, we have an `instructor` table and then you have the actual `Instructor_detail` table. The `instructor` table may contain the instructor's `first_name`, `last_name`, and `email`.

The instructor detail may have just more profile-specific information like:

  + **Their YouTube channel**,
  + **Hobby**,
  + **LinkedIn profile**,
  + **Twitter handle**
  
The key here is that this information is in two separate tables.

### **One-to-Many**

An **instructor** can have many **courses**. So we have the `instructor` table, and the `course` table. So, we have an instructor and multiple courses out here that are authored by the instructor.

*Let's assume that in our school, a course can not be authored by many intructors (a course cannot have many instructors). This depicts a classic one-to-many mapping*.

The actual opposite/inverse of this is the Many-to-One mapping. Here, we have many courses that can map to an instructor. 

### **Many-to-Many**

A course can have many students, and a student can have many courses.
