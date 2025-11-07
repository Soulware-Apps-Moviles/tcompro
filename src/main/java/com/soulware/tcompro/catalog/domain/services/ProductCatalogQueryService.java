package com.soulware.tcompro.catalog.domain.services;

import com.soulware.tcompro.catalog.domain.model.aggregates.ProductCatalog;
import com.soulware.tcompro.catalog.domain.model.queries.GetAllProductCatalogQuery;

import java.util.List;

public interface ProductCatalogQueryService {
    List<ProductCatalog> handle(GetAllProductCatalogQuery query);
}
