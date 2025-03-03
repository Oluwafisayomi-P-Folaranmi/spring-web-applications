package com.opf.office.entity;

import jakarta.persistence.*;

/*
 * CREATE TABLE `employee` (
 * `id` int NOT NULL AUTO_INCREMENT,
 * `first_name` varchar(45) DEFAULT NULL,
 * `last_name` varchar(45) DEFAULT NULL,
 * `email` varchar(45) DEFAULT NULL,
 * PRIMARY KEY (`id`)
)
 */
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "email")
    String email;

    /*
     * Constructors
     */
    public Employee() {
    }

    public Employee(String firstName, String lastName, String email) {
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
        return "Employee{ " + "id='" + id + "', firstName='" + firstName +
                "', lastName='" + lastName + "', email='" + email + "' }";
    }
}
