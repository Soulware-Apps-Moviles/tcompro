package com.soulware.tcompro.order.interfaces.rest.resources;

import java.math.BigDecimal;

public record OrderlineResource(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer quantity,
        Long catalogProductId
) {
}
