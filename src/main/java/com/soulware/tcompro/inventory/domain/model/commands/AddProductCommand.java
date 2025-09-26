package com.soulware.tcompro.inventory.domain.model.commands;

import java.math.BigDecimal;

public record AddProductCommand(Long shopId, Long catalogProductId, BigDecimal price) {
}
