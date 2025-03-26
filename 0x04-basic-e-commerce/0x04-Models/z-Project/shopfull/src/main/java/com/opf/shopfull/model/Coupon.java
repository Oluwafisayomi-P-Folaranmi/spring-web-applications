package com.opf.shopfull.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "coupon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Coupon {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "discount_percentage")
    private double discountPercentage;

    @Column(name = "validity_start_date")
    private LocalDateTime validityStartDate;

    @Column(name = "validity_end_date")
    private LocalDateTime validityEndDate;

    @Column(name = "minimum_order_value")
    private double minimumOrderValue;

    @Column(name = "active")
    private boolean active = true;

    @ManyToMany()
    @JoinTable(
            name = "user_coupon",
            joinColumns = @JoinColumn(name = "coupon_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> usedByUsers = new HashSet<>();
}
