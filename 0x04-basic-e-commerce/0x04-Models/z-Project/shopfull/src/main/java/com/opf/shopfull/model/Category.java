package com.opf.shopfull.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "category_id", unique = true, nullable = false)
    private String categoryId;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @NotNull
    @Column(name = "level")
    private Integer level;

//    @OneToMany(mappedBy = "category")
//    private Product product;
}
