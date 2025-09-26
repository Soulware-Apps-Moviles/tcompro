package com.soulware.tcompro.shop.domain.services;

import com.soulware.tcompro.shop.domain.model.aggregates.Shop;
import com.soulware.tcompro.shop.domain.model.aggregates.Shopkeeper;
import com.soulware.tcompro.shop.domain.model.queries.GetAllShopkeepersByShopIdQuery;
import com.soulware.tcompro.shop.domain.model.queries.GetShopkeeperByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ShopkeeperQueryService {
    Optional<Shopkeeper> handle(GetShopkeeperByIdQuery query);
    Optional<List<Shopkeeper>> handle(GetAllShopkeepersByShopIdQuery query);
}
