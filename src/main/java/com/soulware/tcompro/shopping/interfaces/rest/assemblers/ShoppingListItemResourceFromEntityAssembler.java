package com.soulware.tcompro.shopping.interfaces.rest.assemblers;

import com.soulware.tcompro.shopping.domain.model.entities.ShoppingListItem;
import com.soulware.tcompro.shopping.interfaces.rest.resources.ShoppingListItemResource;

public class ShoppingListItemResourceFromEntityAssembler {
    public static ShoppingListItemResource toResourceFromEntity(ShoppingListItem entity) {
        return new ShoppingListItemResource(
                entity.getId().getValue(),
                entity.catalogProductId.getValue(),
                entity.information.name(),
                entity.information.description(),
                entity.price.amount(),
                entity.getQuantity().value()
        );
    }
}
