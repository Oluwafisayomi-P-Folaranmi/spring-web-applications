package com.opf.shopfull.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    @JsonIgnore
    private Cart cart;

    private Product product;

    @Column(name = "size", length = 50)
    private String size;

    @Column(name = "quantity", nullable = false, columnDefinition = "INT DEFAULT 1 CHECK (quantity > 0)")
    private int quantity = 1;

    @Column(name = "mrp_price", columnDefinition = "INT CHECK (mrp_price >= 0)")
    private Integer mrpPrice;

    @Column(name = "selling_price", columnDefinition = "INT CHECK (selling_price >= 0)")
    private Integer sellingPrice;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
