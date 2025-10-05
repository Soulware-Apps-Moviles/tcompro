package com.soulware.tcompro.shopping.interfaces.rest.assemblers;

import com.soulware.tcompro.shopping.domain.model.commands.AddFavoriteProductCommand;
import com.soulware.tcompro.shopping.interfaces.rest.resources.CreateFavoriteProductResource;

public class CreateFavoriteProductCommandFromResourceAssembler {
    public static AddFavoriteProductCommand toCommandFromResource(CreateFavoriteProductResource resource){
        return new  AddFavoriteProductCommand(
                resource.catalogProductId(),
                resource.customerId()
        );
    }
}
