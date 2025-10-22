package com.soulware.tcompro.shop.domain.services;

import com.soulware.tcompro.shop.domain.model.aggregates.TrustedCustomer;
import com.soulware.tcompro.shop.domain.model.commands.AddTrustedCustomerCommand;
import com.soulware.tcompro.shop.domain.model.commands.UpdateCreditLimitCommand;

import java.util.Optional;

public interface TrustedCustomerCommandService {
    Optional<TrustedCustomer> handle(AddTrustedCustomerCommand command);
    Optional<TrustedCustomer> handle(UpdateCreditLimitCommand command);
}
