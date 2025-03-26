package com.opf.shopfull.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "cart_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "size", length = 50, columnDefinition = "TEXT")
    private String size;

    @Column(name = "quantity")
    private int quantity = 1;

    @Column(name = "mrp_price")
    private Integer mrpPrice;

    @Column(name = "selling_price")
    private Integer sellingPrice;

    @Column(name = "user_id")
    private Long userId;
}
