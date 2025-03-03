package com.opf.hibernate.CollegeService.Entity;

import jakarta.persistence.*;

/*
 * Fields
 * ```sql
 * CREATE TABLE `student` (
 * `id` int NOT NULL AUTO_INCREMENT,
 * `first_name`varchar(45) DEFAULT NULL,
 * `last_name` varchar(45) DEFAULT NULL,
 * `email` varchar(45) DEFAULT NULL,
 * PRIMARY KEY (`id`)
 * );
 * ```
 */
@Entity
@Table(name = "student")
public class Student {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    /*
     * The constructor
     * We did not use the `id` in the constructor
     * because it will be generated automatically
     * in that we specified `AUTO_INCREMENT` in the sql
     */

    public Student() {
    }

    public Student(
            String firstName,
            String lastName,
            String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /*
     * Getters and Setters
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    /*
     * toString
     */

    @Override
    public String toString() {
        return "Student{ " +
                "id='" + id +
                "', firstName='" + firstName +
                "', lastName='" + lastName +
                "', email='" + email +
                "' }";
    }
}
