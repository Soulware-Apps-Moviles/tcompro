package com.soulware.tcompro.shop.domain.services;

import com.soulware.tcompro.shop.domain.model.aggregates.TrustedCustomer;
import com.soulware.tcompro.shop.domain.model.commands.FireShopkeeperCommand;
import com.soulware.tcompro.shop.domain.model.commands.HireShopkeeperCommand;
import java.util.Optional;

public interface ShopkeeperCommandService {
    Optional<TrustedCustomer> handle(HireShopkeeperCommand command);
    Optional<TrustedCustomer> handle(FireShopkeeperCommand command);
}
