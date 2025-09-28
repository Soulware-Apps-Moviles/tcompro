package com.soulware.tcompro.shop.interfaces.rest.assemblers;

import com.soulware.tcompro.shop.domain.model.aggregates.Owner;
import com.soulware.tcompro.shop.interfaces.rest.resources.OwnerResource;

public class OwnerResourceFromEntityAssembler {
    public static OwnerResource toResourceFromEntity(Owner entity){
        assert entity.getPhone() != null;
        return new OwnerResource(
                entity.getId().getValue(),
                entity.getAuthId().authId(),
                entity.getName().firstName(),
                entity.getName().lastName(),
                entity.getEmail().email(),
                entity.getPhone().value()
        );
    }
}
