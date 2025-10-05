package com.soulware.tcompro.inventory.interfaces.rest.resources;

import java.math.BigDecimal;

public record ProductResource(
        Long id,
        Long shopId,
        Long catalogProductId,
        BigDecimal price,
        Boolean isAvailable
) {
}
