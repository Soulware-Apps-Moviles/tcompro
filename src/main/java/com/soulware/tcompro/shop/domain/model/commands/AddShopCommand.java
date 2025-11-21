package com.soulware.tcompro.shop.domain.model.commands;

import java.math.BigDecimal;
import java.util.List;

public record AddShopCommand(Long OwnerId,
                             List<String> paymentMethods,
                             List<String> pickupMethods,
                             BigDecimal maxCreditPerCustomer,
                             double latitude,
                             double longitude,
                             String name) {
}
