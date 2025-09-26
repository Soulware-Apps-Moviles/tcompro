package com.soulware.tcompro.shopping.domain.model.commands;

import com.soulware.tcompro.shopping.domain.model.entities.ShoppingListItem;
import com.soulware.tcompro.shopping.domain.model.valueobjects.ShoppingListId;

public record RemoveShoppingListCommand(ShoppingListId shoppingListId) {
}
