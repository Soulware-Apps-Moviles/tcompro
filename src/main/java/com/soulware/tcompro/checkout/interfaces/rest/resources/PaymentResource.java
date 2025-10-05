package com.soulware.tcompro.checkout.interfaces.rest.resources;

import java.math.BigDecimal;

public record PaymentResource(
        Long id,
        Long customerId,
        Long orderId,
        BigDecimal amount,
        Long paymentMethodId,
        Long shopId
) {
}
