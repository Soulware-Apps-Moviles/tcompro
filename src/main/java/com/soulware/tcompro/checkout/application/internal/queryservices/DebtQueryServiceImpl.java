package com.soulware.tcompro.checkout.application.internal.queryservices;

import com.soulware.tcompro.checkout.domain.model.aggregates.Debt;
import com.soulware.tcompro.checkout.domain.model.queries.GetDebtsQuery;
import com.soulware.tcompro.checkout.domain.model.valueobjects.DebtStatuses;
import com.soulware.tcompro.checkout.domain.services.DebtQueryService;
import com.soulware.tcompro.checkout.infrastructure.persistence.jpa.repositories.DebtRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebtQueryServiceImpl implements DebtQueryService {
    private final DebtRepository debtRepository;

    public DebtQueryServiceImpl(DebtRepository debtRepository) {
        this.debtRepository = debtRepository;
    }

    @Override
    public List<Debt> handle(GetDebtsQuery query){
        return debtRepository.findDebtsOp(query.customerId(),query.shopId(), DebtStatuses.valueOf(query.status()));
    }
}
