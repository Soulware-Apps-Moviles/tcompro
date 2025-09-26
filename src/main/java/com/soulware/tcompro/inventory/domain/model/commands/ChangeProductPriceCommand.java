package com.soulware.tcompro.inventory.domain.model.commands;

import java.math.BigDecimal;

public record ChangeProductPriceCommand(Long productId, BigDecimal price) {
}
