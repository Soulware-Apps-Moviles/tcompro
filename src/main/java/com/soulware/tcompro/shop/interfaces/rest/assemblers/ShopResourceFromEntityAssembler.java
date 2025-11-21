package com.soulware.tcompro.shop.interfaces.rest.assemblers;

import com.soulware.tcompro.sharedkernel.policies.domain.model.entities.PaymentMethod;
import com.soulware.tcompro.sharedkernel.policies.domain.model.entities.PickupMethod;
import com.soulware.tcompro.shop.domain.model.aggregates.Shop;
import com.soulware.tcompro.shop.interfaces.rest.resources.ShopResource;

import java.util.List;
import java.util.stream.Collectors;

public class ShopResourceFromEntityAssembler {
    public static ShopResource toResourceFromEntity(Shop entity) {
        List<String> paymentMethodNames = entity.getPaymentMethods()
                .stream()
                .map(PaymentMethod::getStringName)
                .collect(Collectors.toList());

        List<String> pickupMethodNames = entity.getPickupMethods()
                .stream()
                .map(PickupMethod::getStringName)
                .collect(Collectors.toList());

        return new ShopResource(
                entity.getId().getValue(),
                entity.getOwner().getValue(),
                paymentMethodNames,
                pickupMethodNames,
                entity.getMaxCreditPerCustomer().amount(),
                entity.getCoordinates().latitude(),
                entity.getCoordinates().longitude(),
                entity.getName()
        );
    }
}

