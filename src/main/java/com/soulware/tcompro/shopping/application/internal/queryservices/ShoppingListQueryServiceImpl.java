package com.soulware.tcompro.shopping.application.internal.queryservices;

import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import com.soulware.tcompro.shopping.domain.model.aggregates.ShoppingList;
import com.soulware.tcompro.shopping.domain.model.queries.GetAllShoppingListsQuery;
import com.soulware.tcompro.shopping.domain.services.ShoppingListQueryService;
import com.soulware.tcompro.shopping.infrastructure.persistence.jpa.repositories.ShoppingListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingListQueryServiceImpl implements ShoppingListQueryService {
    private final ShoppingListRepository shoppingListRepository;
    public ShoppingListQueryServiceImpl(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    @Override
    public List<ShoppingList> handle(GetAllShoppingListsQuery query){
        return shoppingListRepository.findAllByFilters(query.customerId(), query.name());
    }
}
