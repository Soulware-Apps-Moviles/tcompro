package com.soulware.tcompro.inventory.interfaces.rest.assemblers;

import com.soulware.tcompro.inventory.domain.model.commands.AddProductCommand;
import com.soulware.tcompro.inventory.interfaces.rest.resources.CreateProductResource;

public class CreateProductCommandFromResourceAssembler {
    public static AddProductCommand toCommandFromResource(CreateProductResource resource) {
        return new AddProductCommand(
                resource.shopId(),
                resource.price(),
                resource.catalogProductId()
        );
    }
}
