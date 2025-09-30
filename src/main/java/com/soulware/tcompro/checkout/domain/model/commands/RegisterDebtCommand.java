package com.soulware.tcompro.checkout.domain.model.commands;

import java.math.BigDecimal;

public record RegisterDebtCommand(
        Long orderId
) {
}
