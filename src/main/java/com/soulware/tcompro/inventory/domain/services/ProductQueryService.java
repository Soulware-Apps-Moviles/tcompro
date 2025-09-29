package com.soulware.tcompro.inventory.domain.services;

import com.soulware.tcompro.inventory.domain.model.aggregates.Product;
import com.soulware.tcompro.inventory.domain.model.queries.GetProductsByCategoryNameAndShopIdQuery;
import com.soulware.tcompro.inventory.domain.model.queries.GetProductsByShopIdQuery;

import java.util.List;

public interface ProductQueryService {
    List<Product> handle(GetProductsByShopIdQuery query);
}
