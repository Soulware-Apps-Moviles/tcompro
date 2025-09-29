package com.soulware.tcompro.inventory.interfaces.rest.assemblers;

import com.soulware.tcompro.inventory.domain.model.commands.UpdateProductCommand;
import com.soulware.tcompro.inventory.interfaces.rest.resources.UpdateProductResource;

public class UpdateProductCommandFromResourceAssembler {
    public static UpdateProductCommand toCommandFromResource(UpdateProductResource resource) {
        return new  UpdateProductCommand(
                resource.productId(),
                resource.price(),
                resource.isAvailable()
        );
    }
}
