package com.soulware.tcompro.shop.interfaces.rest.resources;

import java.math.BigDecimal;
import java.util.List;

public record CreateShopResource(
        Long OwnerId,
        List<String> paymentMethods,
        List<String> pickupMethods,
        BigDecimal maxCreditPerCustomer,
        double latitude,
        double longitude,
        String name
) {
}
