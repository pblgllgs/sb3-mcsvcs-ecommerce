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

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NotNull
@Builder
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private double availableQuantity;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(
            name = "category_id"
    )
    private Category category;
}
