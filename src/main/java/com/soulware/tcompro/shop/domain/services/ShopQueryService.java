package com.soulware.tcompro.shop.domain.services;

import com.soulware.tcompro.shop.domain.model.aggregates.Shop;
import com.soulware.tcompro.shop.domain.model.queries.GetShopByOwnerIdQuery;
import com.soulware.tcompro.shop.domain.model.queries.GetShopIdByIdQuery;

import java.util.Optional;

public interface ShopQueryService {
    Optional<Shop> handle(GetShopIdByIdQuery query);
    Optional<Shop> handle(GetShopByOwnerIdQuery query);
}
