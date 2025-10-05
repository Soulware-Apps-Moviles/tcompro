package com.soulware.tcompro.shopping.domain.services;

import com.soulware.tcompro.shopping.domain.model.aggregates.FavoriteProduct;
import com.soulware.tcompro.shopping.domain.model.queries.GetAllFavoriteProductsByCustomerIdQuery;

import java.util.List;

public interface FavoriteProductQueryService {
    List<FavoriteProduct> handle(GetAllFavoriteProductsByCustomerIdQuery query);
}
