package com.soulware.tcompro.shopping.interfaces.rest.resources;

import java.math.BigDecimal;

public record ShoppingListItemResource(
        Long id,
        Long catalogProductId,
        String name,
        String description,
        BigDecimal price,
        Integer quantity,
        String imageUrl
) {
}
