package com.soulware.tcompro.shop.interfaces.rest.resources;

import java.math.BigDecimal;

public record TrustedCustomerResource(
        Long id,
        BigDecimal creditLimit,
        Long customerId
) {
}
