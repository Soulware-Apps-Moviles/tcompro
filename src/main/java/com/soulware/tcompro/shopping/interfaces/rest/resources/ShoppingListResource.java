package com.soulware.tcompro.shopping.interfaces.rest.resources;

import java.util.List;

public record ShoppingListResource(
        Long id,
        Long customerId,
        String name,
        List<ShoppingListItemResource> items
) {
}
