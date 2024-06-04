package com.pblgllgs.order.exception;
/*
 *
 * @author pblgl
 * Created on 04-06-2024
 *
 */

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {
    private final String msg;
}
