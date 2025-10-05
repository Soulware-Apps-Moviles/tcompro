package com.soulware.tcompro.shopping.domain.services;

import com.soulware.tcompro.shopping.domain.model.aggregates.ShoppingList;
import com.soulware.tcompro.shopping.domain.model.queries.GetAllShoppingListsByCustomerIdQuery;

import java.util.List;

public interface ShoppingListQueryService {
    List<ShoppingList> handle(GetAllShoppingListsByCustomerIdQuery query);
}
