package com.opf.shopfull.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "product")
    private Set<CartItem> cartItems = new HashSet<>();

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "mrp_price", nullable = false)
    private double mrpPrice;

    @Column(name = "selling_price", nullable = false)
    private double sellingPrice;

    @Column(name = "discount_percent")
    private int discountPercent;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "colour")
    private String colour;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ElementCollection
    @Column(name = "sizes")
    private List<String> sizes = new ArrayList<>();

    @Column(name = "num_ratings")
    private int numRatings;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

}
