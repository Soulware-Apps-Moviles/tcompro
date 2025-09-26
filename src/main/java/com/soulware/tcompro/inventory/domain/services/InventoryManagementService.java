package com.soulware.tcompro.inventory.domain.services;

import com.soulware.tcompro.inventory.domain.model.aggregates.Product;
import com.soulware.tcompro.inventory.domain.model.valueobjects.ProductId;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;

import java.util.List;

public interface InventoryManagementService {
    List<Product> findAvailableProductsByShop(ShopId shopId);
    boolean isProductAvailableInShop(ShopId shopId, ProductId productId);
}
