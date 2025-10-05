package com.soulware.tcompro.shopping.interfaces.rest.assemblers;

import com.soulware.tcompro.shared.domain.model.valueobjects.PairResource;
import com.soulware.tcompro.shopping.domain.model.commands.AddShoppingListCommand;
import com.soulware.tcompro.shopping.interfaces.rest.resources.CreateShoppingListResource;
import org.springframework.data.util.Pair;

import java.util.List;

public class CreateShoppingListCommandFromResourceAssembler {
    public static AddShoppingListCommand toCommandFromResource(CreateShoppingListResource resource){
        List<PairResource<Long, Integer>> items = resource.items().stream()
                .map(item -> PairResource.of(item.productCatalogId(), item.quantity()))
                .toList();
        return new  AddShoppingListCommand(
                resource.customerId(),
                resource.name(),
                items
        );
    }
}
