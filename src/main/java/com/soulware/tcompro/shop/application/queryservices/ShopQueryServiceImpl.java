package com.soulware.tcompro.shop.application.queryservices;

import com.soulware.tcompro.inventory.infrastructure.persistence.jpa.repositories.ProductRepository;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.shop.domain.model.aggregates.Shop;
import com.soulware.tcompro.shop.domain.model.queries.GetShopByOwnerIdQuery;
import com.soulware.tcompro.shop.domain.model.queries.GetShopIdByIdQuery;
import com.soulware.tcompro.shop.domain.model.queries.GetShopsByProductsIdQuery;
import com.soulware.tcompro.shop.domain.model.valueobjects.OwnerId;
import com.soulware.tcompro.shop.domain.services.ShopQueryService;
import com.soulware.tcompro.shop.infrastructure.persistence.jpa.repositories.ShopRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopQueryServiceImpl implements ShopQueryService {
    private final ShopRepository  shopRepository;

    public ShopQueryServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public Optional<Shop> handle(GetShopIdByIdQuery query){
        return Optional.ofNullable(shopRepository.findById(new ShopId(query.shopId()))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Shop with id " + query.shopId() + " does not exist"
                )));
    }

    @Override
    public Optional<Shop> handle(GetShopByOwnerIdQuery query){
        return Optional.ofNullable(shopRepository.findByOwner(new OwnerId(query.ownerId()))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Shop with owner id " + query.ownerId() + " does not exist"
                )));
    }

    @Override
    public List<Shop> handle(GetShopsByProductsIdQuery query){
        return shopRepository.findNearbyShopsWithProducts(query.productIds(),query.productIds().size(), query.latitude(), query.longitude(), 1);
    }
}
