package com.pblgllgs.customer.entity;

import lombok.*;
import org.springframework.validation.annotation.Validated;

/*
 *
 * @author pblgl
 * Created on 03-06-2024
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;
}
