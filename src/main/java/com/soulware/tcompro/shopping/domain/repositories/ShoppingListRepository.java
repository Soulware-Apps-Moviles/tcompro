package com.soulware.tcompro.shopping.domain.repositories;

import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import com.soulware.tcompro.shopping.domain.model.aggregates.ShoppingList;
import com.soulware.tcompro.shopping.domain.model.valueobjects.ShoppingListId;

import java.util.List;
import java.util.Optional;

public interface ShoppingListRepository {
    Optional<ShoppingList> findById(ShoppingListId id);
    ShoppingList save(ShoppingList shoppingList);
    Optional<List<ShoppingList>> findByCustomerId(CustomerId id);
}
