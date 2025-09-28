package com.soulware.tcompro.shop.interfaces.rest.resources;

import java.math.BigDecimal;
import java.util.List;

public record ShopResource(
        Long id,
        Long ownerId,
        List<String> paymentMethods,
        List<String> pickupMethods,
        BigDecimal maxCreditPerCustomer
) {
}
