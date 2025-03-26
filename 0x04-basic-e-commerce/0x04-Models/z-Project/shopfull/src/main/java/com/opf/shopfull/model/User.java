package com.opf.shopfull.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opf.shopfull.domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String password;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "role")
    private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses = new HashSet<>();

    @ManyToMany(mappedBy = "usedByUsers")
    @JsonIgnore
    private Set<Coupon> usedCoupons = new HashSet<>();

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @OneToMany(mappedBy = "user")
    @Column(name = "reviews")
    private List<Review> reviews = new ArrayList<>();
}
