package com.opf.shopfull.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Address {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "locality")
    private String locality;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "pin_code")
    private String pinCode;

    @Column(name = "mobile")
    private String mobile;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
}
