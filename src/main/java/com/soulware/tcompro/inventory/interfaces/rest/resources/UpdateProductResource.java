package com.soulware.tcompro.inventory.interfaces.rest.resources;

import jakarta.validation.constraints.Null;

import java.math.BigDecimal;

public record UpdateProductResource(Long productId, @Null BigDecimal price, @Null Boolean isAvailable) {
}
