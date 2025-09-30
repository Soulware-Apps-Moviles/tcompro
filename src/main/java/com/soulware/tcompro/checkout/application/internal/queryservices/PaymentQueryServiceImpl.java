package com.soulware.tcompro.checkout.application.internal.queryservices;

import com.soulware.tcompro.checkout.domain.model.aggregates.Payment;
import com.soulware.tcompro.checkout.domain.model.queries.GetPaymentsQuery;
import com.soulware.tcompro.checkout.domain.services.PaymentQueryService;
import com.soulware.tcompro.checkout.infrastructure.persistence.jpa.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentQueryServiceImpl implements PaymentQueryService {
    private final PaymentRepository paymentRepository;

    public  PaymentQueryServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> handle(GetPaymentsQuery query){
        return paymentRepository.findPaymentsOp(query.customerId(), query.shopId());
    }

}
