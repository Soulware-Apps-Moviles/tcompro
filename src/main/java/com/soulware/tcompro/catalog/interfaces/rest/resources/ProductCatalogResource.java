package com.soulware.tcompro.catalog.interfaces.rest.resources;

import java.math.BigDecimal;

public record ProductCatalogResource(
        Long id,
        String name,
        String description,
        String category,
        BigDecimal price
) {
}
