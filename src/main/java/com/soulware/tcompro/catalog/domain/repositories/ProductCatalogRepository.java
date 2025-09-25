package com.soulware.tcompro.catalog.domain.repositories;

import com.soulware.tcompro.catalog.domain.model.aggregates.ProductCatalog;
import com.soulware.tcompro.shared.domain.model.valueobjects.CatalogProductId;

import java.util.List;
import java.util.Optional;

public interface ProductCatalogRepository {
    void save (ProductCatalog productCatalog);
    Optional<ProductCatalog> findById (CatalogProductId id);
    Optional<List<ProductCatalog>> findAll (CatalogProductId id);
}
