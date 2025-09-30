package com.soulware.tcompro.checkout.interfaces.rest.assemblers;

import com.soulware.tcompro.checkout.domain.model.aggregates.Debt;
import com.soulware.tcompro.checkout.interfaces.rest.resources.DebtResource;

public class DebtResourceFromEntityAssembler {
    public static DebtResource toResourceFromEntity(Debt entity){
        return new DebtResource(
                entity.getDebtId().getValue(),
                entity.getCustomerId().getValue(),
                entity.getOrderId().getValue(),
                entity.getAmount().amount(),
                entity.getShopId().getValue(),
                entity.getStatus().getName().name()
        );
    }
}
