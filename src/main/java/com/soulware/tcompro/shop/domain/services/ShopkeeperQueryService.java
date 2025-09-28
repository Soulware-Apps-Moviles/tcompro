package com.soulware.tcompro.shop.domain.services;

import com.soulware.tcompro.shop.domain.model.aggregates.Shopkeeper;
import com.soulware.tcompro.shop.domain.model.queries.GetAllShopkeepersByShopIdQuery;
import com.soulware.tcompro.shop.domain.model.queries.GetShopkeeperByEmailAddressQuery;

import java.util.List;
import java.util.Optional;

public interface ShopkeeperQueryService {
    Optional<Shopkeeper> handle(GetShopkeeperByEmailAddressQuery query);
    Optional<List<Shopkeeper>> handle(GetAllShopkeepersByShopIdQuery query);
}
