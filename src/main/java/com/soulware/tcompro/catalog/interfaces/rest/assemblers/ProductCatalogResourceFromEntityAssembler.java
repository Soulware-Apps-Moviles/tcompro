package com.soulware.tcompro.catalog.interfaces.rest.assemblers;

import com.soulware.tcompro.catalog.domain.model.aggregates.ProductCatalog;
import com.soulware.tcompro.catalog.interfaces.rest.resources.ProductCatalogResource;

public class ProductCatalogResourceFromEntityAssembler {
    public static ProductCatalogResource toResourceFromEntity(ProductCatalog entity){
        return new  ProductCatalogResource(
                entity.getId().getValue(),
                entity.getProductInformation().name(),
                entity.getProductInformation().description(),
                entity.getCategory().getName().name(),
                entity.getPrice().amount()
        );
    }
}
