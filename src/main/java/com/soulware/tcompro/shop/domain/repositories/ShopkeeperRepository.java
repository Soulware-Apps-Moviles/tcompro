package com.soulware.tcompro.shop.domain.repositories;

import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.shop.domain.model.aggregates.Shopkeeper;
import com.soulware.tcompro.shop.domain.model.valueobjects.OwnerId;
import com.soulware.tcompro.shop.domain.model.valueobjects.ShopkeeperId;

import java.util.List;
import java.util.Optional;

public interface ShopkeeperRepository {
    Optional<Shopkeeper> findById(ShopkeeperId id);
    Optional<List<Shopkeeper>> findByShopId(ShopId id);
    Shopkeeper save(Shopkeeper shopkeeper);
}
