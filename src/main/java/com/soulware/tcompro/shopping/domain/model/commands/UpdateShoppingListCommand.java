package com.soulware.tcompro.shopping.domain.model.commands;

import jakarta.validation.constraints.Null;
import org.springframework.data.util.Pair;

public record UpdateShoppingListCommand(@Null String name, Long shoppingListId, @Null Pair<Long, Integer> item) {
}
