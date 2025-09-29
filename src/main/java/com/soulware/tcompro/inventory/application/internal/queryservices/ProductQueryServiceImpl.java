package com.soulware.tcompro.inventory.application.internal.queryservices;

import com.soulware.tcompro.inventory.domain.model.aggregates.Product;
import com.soulware.tcompro.inventory.domain.model.queries.GetProductsByCategoryNameAndShopIdQuery;
import com.soulware.tcompro.inventory.domain.model.queries.GetProductsByShopIdQuery;
import com.soulware.tcompro.inventory.domain.services.ProductQueryService;
import com.soulware.tcompro.inventory.infrastructure.persistence.jpa.repositories.ProductRepository;
import com.soulware.tcompro.shared.domain.model.valueobjects.Categories;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {
    private final ProductRepository productRepository;

    public ProductQueryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> handle(GetProductsByShopIdQuery query) {
        return productRepository.findAllByShopIdAndFilters(query.shopId(),query.category(),query.isAvailable());
    }


}
