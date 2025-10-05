package com.soulware.tcompro.shop.application.commandservices;

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

    public ShopkeeperCommandServiceImpl(
            ShopkeeperRepository shopkeeperRepository
    ){
        this.shopkeeperRepository = shopkeeperRepository;
    }

    @Override
    public Optional<Shopkeeper> handle(HireShopkeeperCommand command){

        Shopkeeper shopkeeper = shopkeeperRepository.findById(new ShopkeeperId(command.shopkeeperId()))
                .orElseThrow(() -> new IllegalArgumentException("Shopkeeper with id " + command.shopkeeperId() + " not found"));

        if (shopkeeper.isHired()) throw new  IllegalArgumentException("Shopkeeper with id " + command.shopkeeperId() + " is already hired");

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
