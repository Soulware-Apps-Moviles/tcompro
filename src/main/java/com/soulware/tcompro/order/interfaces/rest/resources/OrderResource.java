package com.soulware.tcompro.order.interfaces.rest.resources;

import java.util.List;

public record OrderResource(
        Long id,
        List<OrderlineResource> orderlines,
        Long customerId,
        Long shopId,
        String paymentMethod,
        String pickupMethod,
        String status
) {
}
