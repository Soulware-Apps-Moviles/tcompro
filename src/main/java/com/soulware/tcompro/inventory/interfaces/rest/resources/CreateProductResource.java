package com.soulware.tcompro.inventory.interfaces.rest.resources;

import jakarta.validation.constraints.Null;

import java.math.BigDecimal;

public record CreateProductResource(Long shopId, @Null BigDecimal price, Long catalogProductId) {
}
