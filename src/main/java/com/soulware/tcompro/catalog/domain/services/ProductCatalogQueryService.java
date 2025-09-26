package com.soulware.tcompro.catalog.domain.services;

import com.soulware.tcompro.catalog.domain.model.aggregates.ProductCatalog;
import com.soulware.tcompro.catalog.domain.model.queries.GetAllCatalogProductsQuery;
import com.soulware.tcompro.catalog.domain.model.queries.GetCatalogProductByIdQuery;
import com.soulware.tcompro.shared.domain.model.valueobjects.CatalogProductId;

import java.util.List;
import java.util.Optional;

public interface ProductCatalogQueryService {
    Optional<ProductCatalog> handle(GetCatalogProductByIdQuery query);
    Optional<List<ProductCatalog>> handle(GetAllCatalogProductsQuery query);
}
