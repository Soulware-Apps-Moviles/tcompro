package com.soulware.tcompro.shop.interfaces.rest.assemblers;

import com.soulware.tcompro.shop.domain.model.commands.AddShopCommand;
import com.soulware.tcompro.shop.interfaces.rest.resources.CreateShopResource;

public class CreateShopCommandFromResourceAssembler {
    public static AddShopCommand toCommandFromResource(CreateShopResource resource){
        return new AddShopCommand(
                resource.OwnerId(),
                resource.paymentMethods(),
                resource.pickupMethods(),
                resource.maxCreditPerCustomer(),
                resource.latitude(),
                resource.longitude(),
                resource.name()
        );
    }
}
