package com.opf.shopfull.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>(); // Not resolved yet

    @Column(name = "total_selling_price", nullable = false)
    private double totalSellingPrice;

    @Column(name = "total_item", nullable = false)
    private int totalItem;

    @Column(name = "total_mrp_price", nullable = false)
    private int totalMrpPrice;

    @Column(name = "discount", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int discount;

    @Column(name = "coupon_code", length = 50)
    private String couponCode;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
