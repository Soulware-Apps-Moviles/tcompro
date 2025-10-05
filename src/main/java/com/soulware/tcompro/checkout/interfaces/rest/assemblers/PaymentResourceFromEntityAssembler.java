package com.soulware.tcompro.checkout.interfaces.rest.assemblers;

import com.soulware.tcompro.checkout.domain.model.aggregates.Payment;
import com.soulware.tcompro.checkout.interfaces.rest.resources.PaymentResource;

public class PaymentResourceFromEntityAssembler {
    public static PaymentResource toResourceFromEntity(Payment entity){
        return new PaymentResource(
                entity.getId().getValue(),
                entity.getCustomerId().getValue(),
                entity.getOrderId().getValue(),
                entity.getAmount().amount(),
                entity.getPaymentMethodId().getValue(),
                entity.getShopId().getValue()
        );
    }
}
