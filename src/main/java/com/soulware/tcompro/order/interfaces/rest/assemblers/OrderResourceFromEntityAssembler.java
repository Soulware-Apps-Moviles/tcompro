package com.soulware.tcompro.order.interfaces.rest.assemblers;

import com.soulware.tcompro.order.domain.model.aggregates.Order;
import com.soulware.tcompro.order.interfaces.rest.resources.OrderResource;
import com.soulware.tcompro.order.interfaces.rest.resources.OrderlineResource;

import java.util.List;

public class OrderResourceFromEntityAssembler {
    public static OrderResource toResourceFromEntity(Order entity) {
        List<OrderlineResource> orderlines = entity.getOrderlines().stream()
                .map(OrderlineResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return new OrderResource(
                entity.getOrderId().getValue(),
                orderlines,
                entity.getCustomerId().getValue(),
                entity.getShopId().getValue(),
                entity.getPaymentMethod().getName().name(),
                entity.getPickupMethod().getType().name(),
                entity.getStatus().getName().name()
        );
    }
}
