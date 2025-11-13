package com.soulware.tcompro.shopping.domain.model.queries;

import jakarta.validation.constraints.Null;

public record GetAllShoppingListsQuery(Long customerId, @Null String name) {
}
