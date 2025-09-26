package com.soulware.tcompro.shopping.domain.model.commands;

import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import com.soulware.tcompro.shopping.domain.model.entities.ShoppingListItem;

import java.util.List;

public record AddShoppingListCommand(CustomerId customerId, String name, List<ShoppingListItem> items) {
}
