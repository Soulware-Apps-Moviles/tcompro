package com.soulware.tcompro.catalog.infrastructure.persistence.jpa.repositories;

import com.soulware.tcompro.catalog.domain.model.aggregates.ProductCatalog;
import com.soulware.tcompro.inventory.domain.model.aggregates.Product;
import com.soulware.tcompro.shared.domain.model.entities.Category;
import com.soulware.tcompro.shared.domain.model.valueobjects.CatalogProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCatalogRepository extends JpaRepository<ProductCatalog, Long> {


    Optional<ProductCatalog> findById(CatalogProductId id);

    List<ProductCatalog> findAllByCategory(Category category);
}
