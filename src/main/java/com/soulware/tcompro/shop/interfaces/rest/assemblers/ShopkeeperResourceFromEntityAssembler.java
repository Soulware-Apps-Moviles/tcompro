package com.soulware.tcompro.shop.interfaces.rest.assemblers;

import com.soulware.tcompro.shop.domain.model.aggregates.Shopkeeper;
import com.soulware.tcompro.shop.interfaces.rest.resources.ShopkeeperResource;

public class ShopkeeperResourceFromEntityAssembler {
    public static ShopkeeperResource toResourceFromEntity(Shopkeeper entity){
        assert entity.getPhone() != null;
        return new  ShopkeeperResource(
                entity.getId().getValue(),
                entity.getShopId().getValue(),
                entity.getAuthId().authId(),
                entity.getName().firstName(),
                entity.getName().lastName(),
                entity.getEmail().email(),
                entity.getPhone().value(),
                entity.isHired()
        );
    }
}
