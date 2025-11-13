package com.soulware.tcompro.shopping.domain.services;

import com.soulware.tcompro.shopping.domain.model.aggregates.ShoppingList;
import com.soulware.tcompro.shopping.domain.model.queries.GetAllShoppingListsQuery;

import java.util.List;

public interface ShoppingListQueryService {
    List<ShoppingList> handle(GetAllShoppingListsQuery query);
}
