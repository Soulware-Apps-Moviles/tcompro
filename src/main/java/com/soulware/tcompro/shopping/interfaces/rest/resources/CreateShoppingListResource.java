package com.soulware.tcompro.shopping.interfaces.rest.resources;

import com.soulware.tcompro.shared.domain.model.valueobjects.PairResource;

import java.util.List;

public record CreateShoppingListResource(Long customerId, String name, List<CreateShoppingListItemResource> items) {
}
