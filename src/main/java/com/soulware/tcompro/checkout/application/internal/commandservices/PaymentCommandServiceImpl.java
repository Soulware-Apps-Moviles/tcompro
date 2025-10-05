package com.soulware.tcompro.checkout.application.internal.commandservices;

import com.soulware.tcompro.checkout.domain.model.aggregates.Debt;
import com.soulware.tcompro.checkout.domain.model.aggregates.Payment;
import com.soulware.tcompro.checkout.domain.model.commands.RegisterPaymentCommand;
import com.soulware.tcompro.checkout.domain.model.valueobjects.DebtId;
import com.soulware.tcompro.checkout.domain.model.valueobjects.PaymentId;
import com.soulware.tcompro.checkout.domain.services.PaymentCommandService;
import com.soulware.tcompro.checkout.infrastructure.persistence.jpa.repositories.DebtRepository;
import com.soulware.tcompro.checkout.infrastructure.persistence.jpa.repositories.PaymentRepository;
import com.soulware.tcompro.order.domain.model.aggregates.Order;
import com.soulware.tcompro.order.infrastructure.persistence.jpa.repositories.OrderRepository;
import com.soulware.tcompro.shared.domain.model.valueobjects.OrderId;
import com.soulware.tcompro.shared.infrastructure.support.IdGenerator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final DebtRepository debtRepository;
    private final IdGenerator idGenerator;

    public PaymentCommandServiceImpl(
            PaymentRepository paymentRepository,
            OrderRepository orderRepository,
            DebtRepository debtRepository,
            IdGenerator idGenerator
    ){
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.debtRepository = debtRepository;
        this.idGenerator = idGenerator;
    }

    @Override
    public Optional<Payment> handle(RegisterPaymentCommand command){
        Order order = orderRepository.findByOrderId(new OrderId(command.orderId()))
                .orElseThrow(() -> new RuntimeException("Order with id " + command.orderId() + " not found"));

        Payment payment = new Payment(
                new PaymentId(idGenerator.nextPaymentId()),
                order.getCustomerId(),
                order.getOrderId(),
                order.getTotal(),
                order.getPaymentMethod().getId(),
                order.getShopId()
        );

        if (command.debtId() != null) {
            Debt debt = debtRepository.findByDebtId(new DebtId(command.debtId()))
                    .orElseThrow(() -> new RuntimeException("Debt with id " + command.debtId() + " not found"));
            payment.setDebtId(debt.getDebtId());
        }

        paymentRepository.save(payment);
        return Optional.of(payment);
    }

}
