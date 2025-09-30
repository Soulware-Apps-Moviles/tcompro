package com.soulware.tcompro.checkout.domain.services;

import com.soulware.tcompro.checkout.domain.model.aggregates.Payment;
import com.soulware.tcompro.checkout.domain.model.commands.RegisterPaymentCommand;

import java.util.Optional;

public interface PaymentCommandService {
    Optional<Payment> handle(RegisterPaymentCommand command);
}
