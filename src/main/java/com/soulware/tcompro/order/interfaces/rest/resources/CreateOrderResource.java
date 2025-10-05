package com.soulware.tcompro.order.interfaces.rest.resources;

import com.soulware.tcompro.shared.domain.model.valueobjects.PairResource;

import java.util.List;

public record CreateOrderResource(
        List<CreateOrderlineResource> orderlines,
        Long customerId,
        Long shopId,
        String paymentMethod,
        String pickupMethod
) {
}
