package com.soulware.tcompro.checkout.domain.services;

import com.soulware.tcompro.checkout.domain.model.aggregates.Payment;
import com.soulware.tcompro.checkout.domain.model.queries.GetPaymentsQuery;

import java.util.List;

public interface PaymentQueryService {
    List<Payment> handle(GetPaymentsQuery query);
}
