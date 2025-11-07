package com.soulware.tcompro.catalog.infrastructure.persistence.jpa.repositories;

import com.soulware.tcompro.catalog.domain.model.aggregates.ProductCatalog;
import com.soulware.tcompro.shared.domain.model.entities.Category;
import com.soulware.tcompro.shared.domain.model.valueobjects.CatalogProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCatalogRepository extends JpaRepository<ProductCatalog, Long> {
    Optional<ProductCatalog> findById(CatalogProductId id);

    @Query("SELECT pc FROM ProductCatalog pc WHERE pc.id.value IN :ids")
    List<ProductCatalog> findAllByIds(@Param("ids") List<Long> ids);

    @Query(
            value = """
        SELECT cp.* FROM catalog_products cp
        JOIN category c ON c.id = cp.category_id
        WHERE (:id IS NULL OR cp.id = :id)
          AND (:category IS NULL OR LOWER(c.name) = LOWER(:category))
          AND (:name IS NULL OR LOWER(cp.name) LIKE LOWER(CONCAT('%', :name, '%')))
    """,
            nativeQuery = true
    )
    List<ProductCatalog> findAllByFilters(
            @Param("id") Long id,
            @Param("category") String category,
            @Param("name") String name
    );



}
