package com.pblgllgs.customer.exception;
/*
 *
 * @author pblgl
 * Created on 03-06-2024
 *
 */

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class CustomerNotFoundException extends RuntimeException {
    private final String msg;
}
