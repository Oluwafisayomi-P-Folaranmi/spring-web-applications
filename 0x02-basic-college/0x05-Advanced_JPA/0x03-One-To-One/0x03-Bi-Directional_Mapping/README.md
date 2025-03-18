# Bi-Directional Mapping

We have a unidirectional relationship between the instructor and the instructor detail. What if you want to load the instructor detail object and want to get the associated instructor? This cannot be accomplished with our current unidirectional relationship. At the moment, we can only start from the instructor then load the instructor detail, but we can't go the other way. The solution to this is using the bi-directional relationship.

Using the bi-directional relationship we can start with the instructor detail then make it back to the instructor. To implement the bi-directional relationship you must have the unidirectional relationship in place. For Hibernate, we will update the Java code. Sensically, we need to add a new field as a *link* inside the instructor detail class which will point to the instructor class. The instructor detail class with the bi-directional mapping will look as thus.


```java InstructorDetail
package com.opf.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="instructor_detail")
public class InstructorDetail {

	...

	@OneToOne(mappedBy="instructorDetail", cascade=CascadeType.ALL)
	public Instructor instructor;

	...

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructorDetail = instructorDetail;
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

The `@OneToOne(mappedBy="instructorDetail")` refers to the `instructorDetail` field/property in the instructor class. The `mappedBy` tells Hibernate to look at the `instructorDetail` property in the `Instructor` class `JoinColumn` and look use the reference to find the instructor with the `id`.

As for the cascading, the `cascade=CascadeType.ALL` will cascade all operations to the associated instructor. 

Now we can create a method in the DAO to source for an instructor's detail. For the DAO implementation.


```java
package com.opf.cruddemo.dao;

...

@Repository
public class AppDAOImpl implements AppDAO {

    // define field for entity manager
    private EntityManager entityManager;

    ...

    @Transactional
    public InstructorDetail findInstructorDetailById(int theId) {
        
        return entityManager.find(InstructorDetail.class, theId);
    }
}

```

The method `entityManager.find(InstructorDetail.class, theId)` retrieves the InstructorDetail object and then the Instructor object. Since the the default fetch method for the one-to-one in Spring is `EAGER`.


## Entity

Following is the `InstructorDetail` class implementing the One-to-One bi-directional mapping.


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

    @OneToOne(mappedBy = "instructorDetail", cascade = CascadeType.ALL)
    private Instructor instructor;

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

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Instructor getInstructor() {
        return instructor;
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


## Data Access Object

Now we want to create the data access objects.


```java AppDAO
package com.opf.cruddemo.dao;

import com.opf.cruddemo.entity.*;
import org.springframework.stereotype.Repository;

@Repository
public interface AppDAO {

    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailByID(int theId);

}

```

We have added a new method, `findInstructorDetailByID`. We will implement the new method below.


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

    @Override
    public InstructorDetail findInstructorDetailByID(int theId) {

        int instructorId = theId;
        return entityManager.find(InstructorDetail.class, instructorId);
    }    
}

```


## Database

Just as we've defined the instructor and instructor detail tables still remain as the same as from before.


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
            findInstructorDetail(appDAO);
        };
    }

    public void findInstructorDetail(AppDAO appDAO) {

        // find instructor detail
        int theId = 1;
        InstructorDetail instructorDetail1 = appDAO.findInstructorDetailByID(theId);

        // print out the instructor detail
        System.out.println("The instructor detail is: " + instructorDetail1);

        // print out the instructor
        System.out.println("The instructor is: " + instructorDetail1.getInstructor());

    }
}

```
