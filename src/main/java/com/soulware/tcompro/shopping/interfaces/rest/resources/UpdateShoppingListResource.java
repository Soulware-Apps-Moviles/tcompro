package com.soulware.tcompro.shopping.interfaces.rest.resources;

import com.soulware.tcompro.shared.domain.model.valueobjects.PairResource;
import jakarta.validation.constraints.Null;
import org.springframework.data.util.Pair;

public record UpdateShoppingListResource(@Null String name, @Null PairResource<Long, Integer> item) {
}
