package com.soulware.tcompro.checkout.domain.model.queries;

import jakarta.validation.constraints.Null;

public record GetDebtsQuery(@Null Long customerId, @Null Long shopId, @Null String status) {
}
