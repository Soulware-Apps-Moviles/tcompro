package com.soulware.tcompro.shop.application.commandservices;

import com.soulware.tcompro.shared.domain.model.valueobjects.EmailAddress;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.shop.domain.model.aggregates.Shop;
import com.soulware.tcompro.shop.domain.model.aggregates.Shopkeeper;
import com.soulware.tcompro.shop.domain.model.aggregates.TrustedCustomer;
import com.soulware.tcompro.shop.domain.model.commands.FireShopkeeperCommand;
import com.soulware.tcompro.shop.domain.model.commands.HireShopkeeperCommand;
import com.soulware.tcompro.shop.domain.model.valueobjects.ShopkeeperId;
import com.soulware.tcompro.shop.domain.services.ShopkeeperCommandService;
import com.soulware.tcompro.shop.infrastructure.persistence.jpa.repositories.ShopRepository;
import com.soulware.tcompro.shop.infrastructure.persistence.jpa.repositories.ShopkeeperRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShopkeeperCommandServiceImpl implements ShopkeeperCommandService {
    private final ShopkeeperRepository shopkeeperRepository;
    private final ShopRepository shopRepository;

    public ShopkeeperCommandServiceImpl(
            ShopkeeperRepository shopkeeperRepository,
            ShopRepository shopRepository
    ){
        this.shopkeeperRepository = shopkeeperRepository;
        this.shopRepository = shopRepository;
    }

    @Override
    public Optional<Shopkeeper> handle(HireShopkeeperCommand command){
        Shop shop = shopRepository.findById(new ShopId(command.shopId()))
                .orElseThrow(() -> new IllegalArgumentException("Shop not found"));

        Shopkeeper shopkeeper = shopkeeperRepository.findByEmail(new EmailAddress(command.email()))
                .orElseThrow(() -> new IllegalArgumentException("Shopkeeper with email " + command.email() + " not found"));

        if (!shopkeeper.isHired() && shopkeeper.getShopId().getValue().equals(99999L))
            shopkeeper.setShopId(shop.getId());

        if (shopkeeper.isHired() && !shopkeeper.getShopId().getValue().equals(99999L))
            throw new IllegalArgumentException("Shopkeeper with email " + command.email() + " has already hired");

        shopkeeper.hire();
        shopkeeperRepository.save(shopkeeper);
        return Optional.of(shopkeeper);
    }

    @Override
    public Optional<Shopkeeper> handle(FireShopkeeperCommand command){
        Shopkeeper shopkeeper = shopkeeperRepository.findById(new ShopkeeperId(command.shopkeeperId()))
                .orElseThrow(() -> new IllegalArgumentException("Shopkeeper with id " + command.shopkeeperId() + " not found"));

        shopkeeper.fire();
        shopkeeperRepository.save(shopkeeper);
        return Optional.of(shopkeeper);
    }
}
