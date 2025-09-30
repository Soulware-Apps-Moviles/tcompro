package com.soulware.tcompro.shopping.interfaces.rest.resources;

import java.math.BigDecimal;

public record FavoriteProductResource(
        Long id,
        Long catalogProductId,
        String name,
        String description,
        BigDecimal price,
        Long customerId
) {
}
