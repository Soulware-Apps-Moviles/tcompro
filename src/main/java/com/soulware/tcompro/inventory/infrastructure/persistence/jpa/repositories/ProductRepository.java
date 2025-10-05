package com.soulware.tcompro.inventory.infrastructure.persistence.jpa.repositories;

import com.soulware.tcompro.inventory.domain.model.aggregates.Product;
import com.soulware.tcompro.inventory.domain.model.valueobjects.ProductId;
import com.soulware.tcompro.shared.domain.model.valueobjects.CatalogProductId;
import com.soulware.tcompro.shared.domain.model.valueobjects.Categories;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Boolean existsByCatalogProductIdAndShopId(CatalogProductId catalogProductId, ShopId shopId);

    Optional<Product> findById(ProductId id);

    @Query(value = """
    SELECT p.*
    FROM products p
    INNER JOIN catalog_products cp ON p.catalog_product_id = cp.id
    INNER JOIN category c ON cp.category_id = c.id
    WHERE p.shop_id = :shopId
      AND (:category IS NULL OR c.name = :category)
      AND (:isAvailable IS NULL OR p.is_available = :isAvailable)
    """, nativeQuery = true)
    List<Product> findAllByShopIdAndFilters(@Param("shopId") Long shopId,
                                            @Param("category") String category,
                                            @Param("isAvailable") Boolean isAvailable);
}
