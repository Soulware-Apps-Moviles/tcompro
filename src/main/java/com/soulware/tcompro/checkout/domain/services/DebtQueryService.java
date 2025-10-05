package com.soulware.tcompro.checkout.domain.services;

import com.soulware.tcompro.checkout.domain.model.aggregates.Debt;
import com.soulware.tcompro.checkout.domain.model.queries.GetDebtsQuery;

import java.util.List;

public interface DebtQueryService {
    List<Debt> handle(GetDebtsQuery query);
}
