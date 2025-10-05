package com.soulware.tcompro.checkout.domain.model.commands;

import jakarta.validation.constraints.Null;


public record RegisterPaymentCommand(
        Long orderId,
        @Null Long debtId
) {
}
