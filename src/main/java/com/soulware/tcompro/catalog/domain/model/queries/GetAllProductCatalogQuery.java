package com.soulware.tcompro.catalog.domain.model.queries;

import jakarta.validation.constraints.Null;

public record GetAllProductCatalogQuery(@Null String category, @Null Long id, @Null String name) {
}
