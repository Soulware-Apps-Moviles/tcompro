package com.soulware.tcompro.shop.interfaces.rest.assemblers;

import com.soulware.tcompro.shop.domain.model.commands.UpdateCreditLimitCommand;
import com.soulware.tcompro.shop.interfaces.rest.resources.UpdateCreditLimitResource;

public class UpdateCreditLimitCommandFromResourceAssembler {
    public static UpdateCreditLimitCommand toCommandFromResource(UpdateCreditLimitResource resource) {
        return new  UpdateCreditLimitCommand(
                resource.customerId(),
                resource.creditLimit()
        );
    }
}
