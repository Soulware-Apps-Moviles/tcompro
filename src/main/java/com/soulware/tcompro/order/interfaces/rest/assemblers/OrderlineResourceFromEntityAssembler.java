package com.soulware.tcompro.order.interfaces.rest.assemblers;

import com.soulware.tcompro.order.domain.model.entities.Orderline;
import com.soulware.tcompro.order.interfaces.rest.resources.OrderlineResource;

public class OrderlineResourceFromEntityAssembler {
    public static OrderlineResource toResourceFromEntity(Orderline entity) {
        return new OrderlineResource(
                entity.getId().getValue(),
                entity.getInformation().name(),
                entity.getInformation().description(),
                entity.getPrice().amount(),
                entity.getQuantity().value(),
                entity.getCatalogProductId().getValue()
        );
    }
}
