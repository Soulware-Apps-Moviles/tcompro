package com.soulware.tcompro.shop.infrastructure.persistence.jpa.repositories;

import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.shop.domain.model.aggregates.Shop;
import com.soulware.tcompro.shop.domain.model.valueobjects.OwnerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    Optional<Shop> findByOwner(OwnerId id);
    Optional<Shop> findById(ShopId id);

    @Query(value = """
    SELECT s.*
    FROM shops s
    WHERE s.id IN (
        SELECT p.shop_id
        FROM products p
        WHERE p.catalog_product_id IN (:catalogProductIds)
          AND p.is_available = true
        GROUP BY p.shop_id
        HAVING COUNT(DISTINCT p.catalog_product_id) = :size
    )
    """, nativeQuery = true)
    List<Shop> findShopsWithAllCatalogProducts(@Param("catalogProductIds") List<Long> catalogProductIds,
                                               @Param("size") int size);

}
