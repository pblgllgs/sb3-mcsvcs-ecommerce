package com.pblgllgs.product.entity;
/*
 *
 * @author pblgl
 * Created on 03-06-2024
 *
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NotNull
@Builder
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.REMOVE
    )
    private List<Product> products;
}
