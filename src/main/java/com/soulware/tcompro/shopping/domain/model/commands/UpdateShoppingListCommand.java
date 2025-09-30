package com.soulware.tcompro.shopping.domain.model.commands;

import com.soulware.tcompro.shared.domain.model.valueobjects.PairResource;
import jakarta.validation.constraints.Null;

public record UpdateShoppingListCommand(@Null String name, Long shoppingListId, @Null PairResource<Long, Integer> item) {
}
