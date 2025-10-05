package com.soulware.tcompro.inventory.domain.model.queries;

import jakarta.validation.constraints.Null;

public record GetProductsByShopIdQuery(Long shopId, @Null Boolean isAvailable, @Null String category) {
}
