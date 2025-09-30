package com.soulware.tcompro.checkout.application.internal.commandservices;

import com.soulware.tcompro.checkout.domain.model.aggregates.Debt;
import com.soulware.tcompro.checkout.domain.model.commands.MarkDebtAsPaidCommand;
import com.soulware.tcompro.checkout.domain.model.commands.RegisterDebtCommand;
import com.soulware.tcompro.checkout.domain.model.entities.DebtStatus;
import com.soulware.tcompro.checkout.domain.model.valueobjects.DebtId;
import com.soulware.tcompro.checkout.domain.model.valueobjects.DebtStatuses;
import com.soulware.tcompro.checkout.domain.services.DebtCommandService;
import com.soulware.tcompro.checkout.infrastructure.persistence.jpa.repositories.DebtRepository;
import com.soulware.tcompro.checkout.infrastructure.persistence.jpa.repositories.DebtStatusRepository;
import com.soulware.tcompro.order.domain.model.aggregates.Order;
import com.soulware.tcompro.order.infrastructure.persistence.jpa.repositories.OrderRepository;
import com.soulware.tcompro.shared.domain.model.valueobjects.OrderId;
import com.soulware.tcompro.shared.infrastructure.support.IdGenerator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DebtCommandServiceImpl implements DebtCommandService {
    private final DebtRepository debtRepository;
    private final DebtStatusRepository debtStatusRepository;
    private final OrderRepository orderRepository;
    private final IdGenerator idGenerator;

    public DebtCommandServiceImpl(
            DebtRepository debtRepository,
            DebtStatusRepository debtStatusRepository,
            OrderRepository orderRepository,
            IdGenerator idGenerator
    ){
        this.debtRepository = debtRepository;
        this.debtStatusRepository = debtStatusRepository;
        this.orderRepository = orderRepository;
        this.idGenerator = idGenerator;
    }

    @Override
    public Optional<Debt> handle(RegisterDebtCommand command){
        Order order = orderRepository.findByOrderId(new OrderId(command.orderId()))
                .orElseThrow(() -> new RuntimeException("Order with id " + command.orderId() + " not found"));

        DebtStatus status = debtStatusRepository.findByName(DebtStatuses.PENDING)
                .orElseThrow(() -> new RuntimeException("Debt status " + DebtStatuses.PENDING + " not found"));

        Debt debt = new Debt(
                new DebtId(idGenerator.nextDebtId()),
                order.getCustomerId(),
                order.getOrderId(),
                order.getTotal(),
                order.getShopId(),
                status
        );

        debtRepository.save(debt);
        return Optional.of(debt);
    }

    @Override
    public Optional<Debt> handle(MarkDebtAsPaidCommand command){
        Debt debt = debtRepository.findByDebtId(new DebtId(command.debtId()))
                .orElseThrow(() -> new RuntimeException("Debt with id " + command.debtId() + " not found"));

        DebtStatus status = debtStatusRepository.findByName(DebtStatuses.PAID)
                .orElseThrow(() -> new RuntimeException("Debt status " + DebtStatuses.PAID + " not found"));

        debt.setStatus(status);
        debtRepository.save(debt);
        return Optional.of(debt);
    }
}
