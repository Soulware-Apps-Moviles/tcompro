package com.soulware.tcompro.shop.application.queryservices;

import com.soulware.tcompro.shared.domain.model.valueobjects.EmailAddress;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.shop.domain.model.aggregates.Shopkeeper;
import com.soulware.tcompro.shop.domain.model.queries.GetAllShopkeepersByShopIdQuery;
import com.soulware.tcompro.shop.domain.model.queries.GetShopkeeperByEmailAddressQuery;
import com.soulware.tcompro.shop.domain.services.ShopkeeperQueryService;
import com.soulware.tcompro.shop.infrastructure.persistence.jpa.repositories.ShopkeeperRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopkeeperQueryServiceImpl implements ShopkeeperQueryService {
    private final ShopkeeperRepository shopkeeperRepository;

    public  ShopkeeperQueryServiceImpl(ShopkeeperRepository shopkeeperRepository) {
        this.shopkeeperRepository = shopkeeperRepository;
    }

    @Override
    public Optional<Shopkeeper> handle(GetShopkeeperByEmailAddressQuery query){
        return Optional.ofNullable(shopkeeperRepository.findByEmail(new EmailAddress(query.email()))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Shopkeeper with email " + query.email() + "does not exists"
                )));
    }

    @Override
    public Optional<List<Shopkeeper>> handle(GetAllShopkeepersByShopIdQuery query){
        return shopkeeperRepository.findAllByShopId(new ShopId(query.shopId()));
    }
}
