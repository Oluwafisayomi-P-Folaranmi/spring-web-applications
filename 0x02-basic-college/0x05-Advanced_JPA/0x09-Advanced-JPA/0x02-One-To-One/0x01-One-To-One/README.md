# One-to-One Mapping

An instructor can have an instructor detail. Then we have the instructor table and the instructor detail table. That's a one-to-one relationship. We can model this using two separate tables. 

The example here will be unidirectional. Starting with the instructor and moving in the direction of the instructor detail, we will have a one-way relationship with the instructor detail.


## The Command Line Application

```java CruddemoApplication
package com.opf.cruddemo;

import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(String[] args) {
		return runner -> {
			String devName = "Opf";
			System.out.println("Welcome " + devName + ". Your program is running.");
			System.out.println("You are now on the command line...");
		};
	}

}

```


## Entity

Following are the sql code for the tables that are to be mapped as entities.


```sql instructor_detail
CREATE TABLE `instructor_detail` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`youtube_channel` VARCHAR(128) DEFAULT NULL,
	`hobby` VARCHAR(45) DEFAULT NULL,
	PRIMARY KEY (`id`));

```


```sql instructor
CREATE TABLE `instructor` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`first_name` VARCHAR(50) DEFAULT NULL,
	`last_name` VARCHAR(50) DEFAULT NULL,
	`email` VARCHAR(50) DEFAULT NULL,
	`instructor_detail_id` INT(11) DEFAULT NULL,
	PRIMARY KEY (`id`),
	CONTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_detail_id`) REFERENCES instructor_detail(`id`));

```

   1. In the instructor table, we have a field, `instructor_detail_id` that will help us link to the other table. We can do this by making use of foreign keys. Basically a field in one table is going to refer to primary key in another table.
   2. To set the relationship with the field `instructor_detail_id`, we will add a new field called `CONSTRAINT` which is a `FOREIGN KEY` and it's going to map the `instructor_detail_id` and it's going to reference the `instructor_detail` table's `id`.

That's how you set the foreign key. It's main purpose is to preserve the relationship between tables. This is called referential integrity contraint. It also ensures that valid data is inserted into the foreign key column. That is, it can only contain valid reference to primary key in other table.

So, we already have these tables created for us. Now we need to create the actual Java classes and map the classes to the corresponding tables. Below is an `InstructorDetail` class.


```java InstructorDetail
package com.opf.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="instructor_detail")
public class InstructorDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="youtube_channel")
    private String youtubeChannel;

    @Column(name="hobby")
    private String hobby;

    public InstructorDetail() {

    }

    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "InstructorDetail{" +
                "id=" + id +
                ", youtubeChannel='" + youtubeChannel + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}

```

Now we go ahead to create the `Instructor` class and also map it to the `instructor` table.


```java Instructor
package com.opf.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    public Instructor() {

    }

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", instructorDetail=" + instructorDetail +
                '}';
    }
}

```

We have added the `@OneToOne` annotation, this helps us to map the instructor detail to the instructor. The `@JoinColumn` annotation tells hibernate to reference the reference to the `instructor_detail` table, which is the `instructor_detail_id`.

Using this information, hibernate (or even you while using sql) can use the foreign key, find the instructor detail record, and load the data accordingly. We need to learn about cascading type, but before then let's go through entity lifecycle. 


### Entity Lifecycle 

The entity lifecycle is basically a set of states that a Hibernate entity can go through when using it in your application. We have the following operations and their descriptions. 

   + Detach:
     - If entity is detached, it is not associated with a Hibernate session.
   + Merge:
     - If an instance is detached from session, then merge will reattach to session.
   + Persist:
     - Transitions new instances to managed state. Next, flush/commit will save in db.
   + Remove:
     - Transitions managed entity to be removed. Next, flush/commit will delete from db.
   + Refresh:
     - Reload/sync object with data from db. Prevents stale data.


### Entity Lifecycle - Session Method Calls

To pull these operations together, we have this state, New/Transient where an object is a new object, we call a new keyword or it's transient. Then, what we will do is **save/persist** it, and at that point it is like a Persistent object or Managed object. If we wanted to, we can do a **rollback/create a new** object, and it's in the New/Transient state again. If we wanted to sync the object with information from the database, then we can do a **refresh**.

Next, if we have a persistent object, we can do a **commit/rollback/close**, and that will actually make that object detached. So it is not associated with a given Hibernate session. If we would like to reattach the object to the Hibernate session, then we simply get a reference to that object, and we simply do a **merge** on it.

If we do a **delete/remove**, then the object is now in the removed state. We could also do a **rollback/persist** to actually make it available, and make it back into a managed state. Also, we can do a **commit** on a removed object so it's now removed from the database and now it's just in transient state. And then we could say **rollback**, so that'll basically take the object and move it back to the detached state. 

These are some transition states discussed. 


### Cascading

You can cascade operations. It means you are going to be performing/applying the same operations to related entities. So for example, we have an instructor and the intructor detail. So if we save an instructor we will also save the intructor detail. We can specify the actual cascade types that we want. Here is a list of the cascade types.

   + `PERSIST`
     - If an entity is persisted/saved, related entity will be persisted
   + `REMOVE`
     - If an entity is removed/deleted, related entity will be deleted
   + `REFRESH`
     - If an entity is refreshed, related entity will be refreshed
   + `DETACH`
     - If an entity is detached (not associated with a session), the related entity will also be detached
   + `MERGE`
     - If an entity is merged, related entity will be merged
   + `ALL`
     - All of the above cascade types.

To configure a cascade type, we will do it with the `@OneToOne` annotation. By default, no cascade type is applied. 


```java
...
@OneToOne(cascade=CascadeType.All)
...

```

To configure multiple cascade types (you might not need all), you can itemise the cascade types in a comma-delimited list.


```java
...
@OneToOne(cascade={CascadeType.DETACH,
								CascadeType.MERGE,
								CascadeType.PERSIST,
								CascadeType.REFRESH,
								CascadeType.REMOVE})
...

```


## Data Access Object

Now we want to create the data access objects.


```java AppDAO
package com.opf.cruddemo.dao;

import com.opf.cruddemo.entity.*;
import org.springframework.stereotype.Repository;

@Repository
public interface AppDAO {

    void save(Instructor theInstructor);

}

```


```java AppDAOImpl
package com.opf.cruddemo.dao;

import com.opf.cruddemo.entity.Instructor;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AppDAOImpl implements AppDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }
}

```


## Database

Just as we've defined the instructor and instructor detail tables while defining the entities, we have our sql scripts for the tables.


```sql instructor_detail
CREATE TABLE `instructor_detail` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`youtube_channel` VARCHAR(128) DEFAULT NULL,
	`hobby` VARCHAR(45) DEFAULT NULL,
	PRIMARY KEY (`id`));

```


```sql instructor
CREATE TABLE `instructor` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`first_name` VARCHAR(50) DEFAULT NULL,
	`last_name` VARCHAR(50) DEFAULT NULL,
	`email` VARCHAR(50) DEFAULT NULL,
	`instructor_detail_id` INT(11) DEFAULT NULL,
	PRIMARY KEY (`id`),
	CONTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_detail_id`) REFERENCES instructor_detail(`id`));

```

Then, we execute the codes in our DBMS.


## Running the Application


```java CruddemoApplication
package com.opf.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {

		return runner -> {
			createInstructor(appDAO);
		};
	}

	private void createInstructor(AppDAO appDAO) {

		/*
		// create the instructor
		Instructor tempInstructor =
				new Instructor("Chad", "Darby", "darby@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.luv2code.com/youtube",
						"Luv 2 code!!!");
		*/

		// create the instructor
		Instructor tempInstructor =
				new Instructor("Madhu", "Patel", "madhu@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.luv2code.com/youtube",
						"Guitar");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor
		//
		// NOTE: this will ALSO save the details object
		// because of CascadeType.ALL
		//
		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}
}

```
