package com.soulware.tcompro.checkout.interfaces.rest.resources;

import java.math.BigDecimal;

public record DebtResource(
        Long id,
        Long customerId,
        Long orderId,
        BigDecimal amount,
        Long shopId,
        String status
) {
}
