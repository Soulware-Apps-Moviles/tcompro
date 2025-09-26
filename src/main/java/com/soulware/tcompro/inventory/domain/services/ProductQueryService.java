package com.soulware.tcompro.inventory.domain.services;

import com.soulware.tcompro.inventory.domain.model.aggregates.Product;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;

import java.util.List;
import java.util.Optional;

public interface ProductQueryService {
    Optional<List<Product>> findAllByShopId(ShopId shopId);
}
