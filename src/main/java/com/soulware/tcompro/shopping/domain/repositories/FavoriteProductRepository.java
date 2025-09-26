package com.soulware.tcompro.shopping.domain.repositories;

import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import com.soulware.tcompro.shopping.domain.model.aggregates.FavoriteProduct;
import com.soulware.tcompro.shopping.domain.model.valueobjects.FavoriteProductId;

import java.util.List;
import java.util.Optional;

public interface FavoriteProductRepository {
    Optional<FavoriteProduct> findById(FavoriteProductId id);
    FavoriteProduct save(FavoriteProduct product);
    Optional<List<FavoriteProduct>> findByCustomerId(CustomerId id);
}
