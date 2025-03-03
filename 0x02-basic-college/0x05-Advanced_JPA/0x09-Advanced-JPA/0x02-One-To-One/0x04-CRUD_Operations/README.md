# CRUD Operations Bi-Directional

We may want to delete the instructor detail. Cascading makes us delete the instructor too. There will come a time when we want to delete only the instructor detail and leave the instructor. 


## Entity

The entities instructor and instructor detail remain the same here. Cascading makes us delete the instructor too when we try to delete the instructor detail. We will thus add the method to delete only the instructor detail.


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

We will want to retain the instructor even if we delete the instructor detail. To do this, we will split the `CascadeType.ALL`. 


```java
@OneToOne(mappedBy = "instructorDetail",
          cascade = {CascadeType.DETACH,
                       CascadeType.MERGE,
                       CascadeType.PERSIST,
                       CascadeType.REFRESH})
public Instructor instructor;

```

We have left out `CascadeType.REMOVE`.


## Data Access Object

We will add the method to delete instructor detail by id in the Data Access Object and implement it. 


```java
package com.opf.cruddemo.dao;

import com.opf.cruddemo.entity.*;
import org.springframework.stereotype.Repository;

@Repository
public interface AppDAO {

    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailByID(int theId);

    // delete both the instructor detail and the instructor
    void deleteInstructorDetailById(int theId);
}

```


```java
package com.opf.cruddemo.dao;

import com.opf.cruddemo.entity.Instructor;
import com.opf.cruddemo.entity.InstructorDetail;
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
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {

        // retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        // delete the instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailByID(int theId) {

        int instructorId = theId;
        return entityManager.find(InstructorDetail.class, instructorId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {

        // find the instructordetail
        InstructorDetail theInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        // remove the associated object reference
        // break the bi-directional link
        theInstructorDetail.getInstructor().setInstructorDetail(null);

        // delete the instructor detail
        entityManager.remove(theInstructorDetail);
    }
}

```

Just as we know, for the cascading, if we delete the instructor detail, we delete the instructor. So as not to delete the instructor whenever we try to delete the instructor detail, we will discard the `CascadeType.REMOVE`.

We have done some things on the method `deleteInstructorDetailById`. We will remove the associated object reference and break the bi-directional link. This will muddy the view of Hibernate, and it won't be able to reference any instructor mapped by the `instructorDetail` field in the instructor class.


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


```java
package com.opf.cruddemo;

import com.opf.cruddemo.dao.AppDAO;
import com.opf.cruddemo.entity.Instructor;
import com.opf.cruddemo.entity.InstructorDetail;
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
			String devName = "Opf";
			System.out.println("Welcome " + devName + ". Your program is running.");
			System.out.println("You are now on the command line...");
			deleteInstructorDetailById(appDAO);
		};
	}

	...

	void deleteInstructorDetailById(AppDAO appDAO) {

		int theId = 6;
		System.out.println("Deleting the instructor with the id " + theId + ".");

		appDAO.deleteInstructorDetailById(theId);

		System.out.println("Done!");
	}
}

```
