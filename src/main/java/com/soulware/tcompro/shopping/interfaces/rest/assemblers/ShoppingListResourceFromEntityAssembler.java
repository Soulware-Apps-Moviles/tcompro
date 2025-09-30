package com.soulware.tcompro.shopping.interfaces.rest.assemblers;

import com.soulware.tcompro.shopping.domain.model.aggregates.ShoppingList;
import com.soulware.tcompro.shopping.interfaces.rest.resources.ShoppingListItemResource;
import com.soulware.tcompro.shopping.interfaces.rest.resources.ShoppingListResource;

import java.util.List;

public class ShoppingListResourceFromEntityAssembler {
    public static ShoppingListResource toResourceFromEntity(ShoppingList entity){
        List<ShoppingListItemResource> items = entity.getItems().stream()
                .map(ShoppingListItemResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return new ShoppingListResource(
                entity.getId().getValue(),
                entity.getCustomerId().getValue(),
                entity.getName(),
                items
        );
    }
}
