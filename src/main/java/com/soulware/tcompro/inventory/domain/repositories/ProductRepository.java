package com.soulware.tcompro.inventory.domain.repositories;

import com.soulware.tcompro.inventory.domain.model.aggregates.Product;
import com.soulware.tcompro.inventory.domain.model.valueobjects.ProductId;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(ProductId productId);
    Optional<List<Product>> findByShopId(ShopId shopId);
    Product save(Product product);
    Product update(Product product);
}
