package com.soulware.tcompro.shopping.application.internal.queryservices;

import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import com.soulware.tcompro.shopping.domain.model.aggregates.FavoriteProduct;
import com.soulware.tcompro.shopping.domain.model.queries.GetAllFavoriteProductsByCustomerIdQuery;
import com.soulware.tcompro.shopping.domain.services.FavoriteProductQueryService;
import com.soulware.tcompro.shopping.infrastructure.persistence.jpa.repositories.FavoriteProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteProductQueryServiceImpl implements FavoriteProductQueryService {
    private final FavoriteProductRepository favoriteProductRepository;

    public  FavoriteProductQueryServiceImpl(FavoriteProductRepository favoriteProductRepository) {
        this.favoriteProductRepository = favoriteProductRepository;
    }

    @Override
    public List<FavoriteProduct> handle(GetAllFavoriteProductsByCustomerIdQuery query){
        return favoriteProductRepository.findByCustomerId(new CustomerId(query.customerId()));
    }
}
