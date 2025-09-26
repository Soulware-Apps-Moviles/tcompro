package com.soulware.tcompro.shop.domain.services;

import com.soulware.tcompro.shop.domain.model.aggregates.Shop;
import com.soulware.tcompro.shop.domain.model.commands.AddShopCommand;

import java.util.Optional;

public interface ShopCommandService {
    Optional<Shop> handle(AddShopCommand command);
}
