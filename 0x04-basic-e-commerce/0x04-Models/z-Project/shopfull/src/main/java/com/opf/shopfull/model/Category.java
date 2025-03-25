package com.opf.shopfull.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @NotNull
    @Column(unique = true, nullable = false)
    private String categoryId;

    @ManyToOne
    private Category parentCategory;

    @NotNull
    private Integer level;
}
