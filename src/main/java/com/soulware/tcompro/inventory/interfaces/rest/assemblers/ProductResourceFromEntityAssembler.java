package com.soulware.tcompro.inventory.interfaces.rest.assemblers;

import com.soulware.tcompro.inventory.domain.model.aggregates.Product;
import com.soulware.tcompro.inventory.interfaces.rest.resources.ProductResource;

public class ProductResourceFromEntityAssembler {
    public static ProductResource toResourceFromEntity(Product entity) {
        return new ProductResource(
                entity.getId().getValue(),
                entity.getShopId().getValue(),
                entity.getCatalogProductId().getValue(),
                entity.getPrice().amount(),
                entity.getInformation().name(),
                entity.getInformation().description(),
                entity.isAvailable()
        );
    }
}
