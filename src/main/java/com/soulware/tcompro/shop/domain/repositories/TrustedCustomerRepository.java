package com.soulware.tcompro.shop.domain.repositories;

import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.shop.domain.model.aggregates.Shopkeeper;
import com.soulware.tcompro.shop.domain.model.aggregates.TrustedCustomer;

import java.util.List;
import java.util.Optional;

public interface TrustedCustomerRepository {
    Optional<TrustedCustomer> findById(ShopId id);
    Optional<List<TrustedCustomer>> findByShopId(ShopId id);
    TrustedCustomer save(Shopkeeper shopkeeper);
}
