package com.soulware.tcompro.shop.domain.repositories;

import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.shop.domain.model.aggregates.Shop;
import com.soulware.tcompro.shop.domain.model.valueobjects.OwnerId;

import java.util.Optional;

public interface ShopRepository {
    Optional<Shop> findById(ShopId id);
    Shop save(Shop shop);
    Optional<Shop> findByOwner(OwnerId id);
}
