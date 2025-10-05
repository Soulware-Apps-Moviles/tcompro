package com.soulware.tcompro.order.interfaces.rest.assemblers;

import com.soulware.tcompro.order.domain.model.commands.CreateOrderCommand;
import com.soulware.tcompro.order.interfaces.rest.resources.CreateOrderResource;
import com.soulware.tcompro.shared.domain.model.valueobjects.PairResource;

import java.util.List;

public class CreateOrderCommandFromResourceAssembler {
    public static CreateOrderCommand toCommandFromResource(CreateOrderResource resource){
        List<PairResource<Long, Integer>> orderlines = resource.orderlines().stream()
                .map(item -> PairResource.of(item.productCatalogId(), item.quantity()))
                .toList();
        return new CreateOrderCommand(
                orderlines,
                resource.customerId(),
                resource.shopId(),
                resource.paymentMethod(),
                resource.pickupMethod()
        );
    }
}
