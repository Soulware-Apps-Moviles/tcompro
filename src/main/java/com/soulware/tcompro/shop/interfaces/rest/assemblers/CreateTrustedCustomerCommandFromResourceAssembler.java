package com.soulware.tcompro.shop.interfaces.rest.assemblers;

import com.soulware.tcompro.shop.domain.model.commands.AddTrustedCustomerCommand;
import com.soulware.tcompro.shop.interfaces.rest.resources.CreateTrustedCustomerResource;

public class CreateTrustedCustomerCommandFromResourceAssembler {
    public static AddTrustedCustomerCommand toCommandFromResource(CreateTrustedCustomerResource resource){
        return new  AddTrustedCustomerCommand(
                resource.customerId(),
                resource.shopId(),
                resource.creditLimit()
        );
    }
}
