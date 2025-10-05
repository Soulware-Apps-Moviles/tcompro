package com.soulware.tcompro.checkout.domain.model.queries;

import jakarta.validation.constraints.Null;

public record GetPaymentsQuery(@Null Long customerId, @Null Long shopId) {
}
