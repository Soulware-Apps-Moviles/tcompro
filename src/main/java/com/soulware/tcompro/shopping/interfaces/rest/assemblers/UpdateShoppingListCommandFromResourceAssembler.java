package com.soulware.tcompro.shopping.interfaces.rest.assemblers;

import com.soulware.tcompro.shopping.domain.model.commands.UpdateShoppingListCommand;
import com.soulware.tcompro.shopping.interfaces.rest.resources.UpdateShoppingListResource;

public class UpdateShoppingListCommandFromResourceAssembler {
    public static UpdateShoppingListCommand toCommandFromResource(Long id, UpdateShoppingListResource resource){
        return new UpdateShoppingListCommand(
                resource.name(),
                id,
                resource.item()
        );
    }
}
