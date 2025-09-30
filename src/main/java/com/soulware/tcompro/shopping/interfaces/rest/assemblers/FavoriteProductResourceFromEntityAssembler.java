package com.soulware.tcompro.shopping.interfaces.rest.assemblers;

import com.soulware.tcompro.shopping.domain.model.aggregates.FavoriteProduct;
import com.soulware.tcompro.shopping.interfaces.rest.resources.FavoriteProductResource;

public class FavoriteProductResourceFromEntityAssembler {
    public static FavoriteProductResource toResourceFromEntity(FavoriteProduct entity){
        return new FavoriteProductResource(
                entity.getId().getValue(),
                entity.catalogProductId.getValue(),
                entity.information.name(),
                entity.information.description(),
                entity.price.amount(),
                entity.getCustomerId().getValue()
        );
    }
}
