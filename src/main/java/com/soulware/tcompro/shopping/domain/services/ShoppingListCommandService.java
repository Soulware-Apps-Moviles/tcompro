package com.soulware.tcompro.shopping.domain.services;

import com.soulware.tcompro.shopping.domain.model.aggregates.ShoppingList;
import com.soulware.tcompro.shopping.domain.model.commands.AddShoppingListCommand;
import com.soulware.tcompro.shopping.domain.model.commands.RemoveShoppingListCommand;
import com.soulware.tcompro.shopping.domain.model.commands.UpdateShoppingListCommand;

import java.util.Optional;

public interface ShoppingListCommandService {
    Optional<ShoppingList> handle(AddShoppingListCommand command);
    Optional<Long> handle(RemoveShoppingListCommand command);
    Optional<ShoppingList> handle(UpdateShoppingListCommand command);
}
