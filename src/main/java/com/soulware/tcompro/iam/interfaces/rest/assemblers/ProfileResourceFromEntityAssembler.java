package com.soulware.tcompro.iam.interfaces.rest.assemblers;

import com.soulware.tcompro.iam.domain.model.aggregates.Profile;
import com.soulware.tcompro.iam.interfaces.rest.resources.ProfileResource;
import com.soulware.tcompro.sharedkernel.customer.domain.model.aggregates.Customer;
import com.soulware.tcompro.shop.domain.model.aggregates.Owner;
import com.soulware.tcompro.shop.domain.model.aggregates.Shopkeeper;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity) {
        if (entity instanceof Customer customer) {
            assert customer.getPhone() != null;
            return new ProfileResource(
                    customer.getId().getValue(),
                    customer.getAuthId().authId(),
                    customer.getName().firstName(),
                    customer.getName().lastName(),
                    customer.getEmail().email(),
                    customer.getPhone().value(),
                    "CUSTOMER"
            );
        }
        if (entity instanceof Owner owner) {
            assert owner.getPhone() != null;
            return new ProfileResource(
                    owner.getId().getValue(),
                    owner.getAuthId().authId(),
                    owner.getName().firstName(),
                    owner.getName().lastName(),
                    owner.getEmail().email(),
                    owner.getPhone().value(),
                    "SHOP_OWNER"
            );
        }
        if (entity instanceof Shopkeeper shopkeeper) {
            assert shopkeeper.getPhone() != null;
            return new ProfileResource(
                    shopkeeper.getId().getValue(),
                    shopkeeper.getAuthId().authId(),
                    shopkeeper.getName().firstName(),
                    shopkeeper.getName().lastName(),
                    shopkeeper.getEmail().email(),
                    shopkeeper.getPhone().value(),
                    "SHOPKEEPER"
            );
        }
        return null;
    }
}
