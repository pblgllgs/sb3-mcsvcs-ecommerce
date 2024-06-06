package com.pblgllgs.order.exception;
/*
 *
 * @author pblgl
 * Created on 03-06-2024
 *
 */

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
