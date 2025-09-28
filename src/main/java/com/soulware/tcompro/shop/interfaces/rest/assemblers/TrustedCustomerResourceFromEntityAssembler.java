package com.soulware.tcompro.shop.interfaces.rest.assemblers;

import com.soulware.tcompro.shop.domain.model.aggregates.TrustedCustomer;
import com.soulware.tcompro.shop.interfaces.rest.resources.TrustedCustomerResource;

public class TrustedCustomerResourceFromEntityAssembler {
    public static TrustedCustomerResource toResourceFromEntity(TrustedCustomer entity){
        return new TrustedCustomerResource(
                entity.getId().getValue(),
                entity.getCreditLimit().amount(),
                entity.getCustomerId().getValue()
        );
    }
}
