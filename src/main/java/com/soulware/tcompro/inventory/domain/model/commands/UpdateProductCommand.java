package com.soulware.tcompro.inventory.domain.model.commands;

import jakarta.validation.constraints.Null;

import java.math.BigDecimal;

public record UpdateProductCommand(Long productId, @Null BigDecimal price, @Null Boolean isAvailable) {
}
