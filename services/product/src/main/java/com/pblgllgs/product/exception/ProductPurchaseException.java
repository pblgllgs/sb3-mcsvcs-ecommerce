package com.pblgllgs.product.exception;
/*
 *
 * @author pblgl
 * Created on 04-06-2024
 *
 */

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ProductPurchaseException extends RuntimeException {
    private final String msg;
}
