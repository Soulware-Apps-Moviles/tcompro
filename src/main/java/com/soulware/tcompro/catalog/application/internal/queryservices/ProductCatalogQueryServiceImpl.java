package com.soulware.tcompro.catalog.application.internal.queryservices;

import com.soulware.tcompro.catalog.domain.model.aggregates.ProductCatalog;
import com.soulware.tcompro.catalog.domain.model.queries.GetAllProductCatalogQuery;
import com.soulware.tcompro.catalog.domain.services.ProductCatalogQueryService;
import com.soulware.tcompro.catalog.infrastructure.persistence.jpa.repositories.ProductCatalogRepository;
import com.soulware.tcompro.shared.domain.model.valueobjects.Categories;
import com.soulware.tcompro.shared.infrastructure.persistence.jpa.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCatalogQueryServiceImpl implements ProductCatalogQueryService {
    private final ProductCatalogRepository  productCatalogRepository;
    private final CategoryRepository categoryRepository;

    public ProductCatalogQueryServiceImpl(ProductCatalogRepository productCatalogRepository,
                                          CategoryRepository categoryRepository) {
        this.productCatalogRepository = productCatalogRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductCatalog> handle(GetAllProductCatalogQuery query) {
        return productCatalogRepository.findAllByFilters(query.id(), query.category(), query.name());
    }
}
