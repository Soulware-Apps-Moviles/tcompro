package com.soulware.tcompro.catalog.application.internal.queryservices;

import com.soulware.tcompro.catalog.domain.model.aggregates.ProductCatalog;
import com.soulware.tcompro.catalog.domain.model.queries.GetAllCatalogProductsByCategoryNameQuery;
import com.soulware.tcompro.catalog.domain.model.queries.GetAllCatalogProductsQuery;
import com.soulware.tcompro.catalog.domain.model.queries.GetCatalogProductByIdQuery;
import com.soulware.tcompro.catalog.domain.model.queries.GetProductCatalogByNameQuery;
import com.soulware.tcompro.catalog.domain.services.ProductCatalogQueryService;
import com.soulware.tcompro.catalog.infrastructure.persistence.jpa.repositories.ProductCatalogRepository;
import com.soulware.tcompro.shared.domain.model.entities.Category;
import com.soulware.tcompro.shared.domain.model.valueobjects.CatalogProductId;
import com.soulware.tcompro.shared.domain.model.valueobjects.Categories;
import com.soulware.tcompro.shared.infrastructure.persistence.jpa.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<ProductCatalog> handle(GetCatalogProductByIdQuery query){
        return Optional.ofNullable(productCatalogRepository.findById(new CatalogProductId(query.id()))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Product catalog with id " + query.id() + " does not exist"
                )));
    }

    @Override
    public List<ProductCatalog> handle(GetAllCatalogProductsQuery query){
        return productCatalogRepository.findAll();
    }

    @Override
    public List<ProductCatalog> handle(GetAllCatalogProductsByCategoryNameQuery query){
        Category category = categoryRepository.findByName(Categories.valueOf(query.category()))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Category with name " + query.category() + " does not exist"
                ));
        return productCatalogRepository.findAllByCategory(category);
    }

    @Override
    public List<ProductCatalog> handle(GetProductCatalogByNameQuery query) {
        return productCatalogRepository.findAllByNameLike(query.name());
    }
}
