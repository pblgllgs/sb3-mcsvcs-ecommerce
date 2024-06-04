package com.pblgllgs.order.entity;
/*
 *
 * @author pblgl
 * Created on 04-06-2024
 *
 */

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class OrderLine {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(
            name = "order_id"
    )
    private Order order;
    private Integer productId;
    private double quantity;
}
