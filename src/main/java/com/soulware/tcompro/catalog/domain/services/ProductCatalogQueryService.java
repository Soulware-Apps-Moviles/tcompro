package com.soulware.tcompro.catalog.domain.services;

import com.soulware.tcompro.catalog.domain.model.aggregates.ProductCatalog;
import com.soulware.tcompro.catalog.domain.model.queries.GetAllCatalogProductsByCategoryNameQuery;
import com.soulware.tcompro.catalog.domain.model.queries.GetAllCatalogProductsQuery;
import com.soulware.tcompro.catalog.domain.model.queries.GetCatalogProductByIdQuery;
import com.soulware.tcompro.catalog.domain.model.queries.GetProductCatalogByNameQuery;

import java.util.List;
import java.util.Optional;

public interface ProductCatalogQueryService {
    Optional<ProductCatalog> handle(GetCatalogProductByIdQuery query);
    List<ProductCatalog> handle(GetAllCatalogProductsQuery query);
    List<ProductCatalog> handle(GetAllCatalogProductsByCategoryNameQuery query);
    List<ProductCatalog> handle(GetProductCatalogByNameQuery query);
}
