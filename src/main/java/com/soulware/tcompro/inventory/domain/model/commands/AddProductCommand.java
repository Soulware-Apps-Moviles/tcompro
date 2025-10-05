package com.soulware.tcompro.inventory.domain.model.commands;

import jakarta.validation.constraints.Null;

import java.math.BigDecimal;

public record AddProductCommand(Long shopId, @Null BigDecimal price, Long catalogProductId) {
}
