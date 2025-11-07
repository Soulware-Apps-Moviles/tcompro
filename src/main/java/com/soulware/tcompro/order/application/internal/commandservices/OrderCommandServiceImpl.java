package com.soulware.tcompro.order.application.internal.commandservices;

import com.soulware.tcompro.catalog.domain.model.aggregates.ProductCatalog;
import com.soulware.tcompro.catalog.infrastructure.persistence.jpa.repositories.ProductCatalogRepository;
import com.soulware.tcompro.checkout.domain.model.aggregates.Debt;
import com.soulware.tcompro.checkout.domain.model.aggregates.Payment;
import com.soulware.tcompro.checkout.domain.model.commands.RegisterDebtCommand;
import com.soulware.tcompro.checkout.domain.model.commands.RegisterPaymentCommand;
import com.soulware.tcompro.checkout.domain.services.DebtCommandService;
import com.soulware.tcompro.checkout.domain.services.PaymentCommandService;
import com.soulware.tcompro.order.interfaces.websocket.websocket.resources.OrderStatusNotificationResource;
import com.soulware.tcompro.order.domain.model.aggregates.Order;
import com.soulware.tcompro.order.domain.model.commands.*;
import com.soulware.tcompro.order.domain.model.entities.OrderStatus;
import com.soulware.tcompro.order.domain.model.entities.Orderline;
import com.soulware.tcompro.order.domain.model.valueobjects.OrderStatuses;
import com.soulware.tcompro.order.domain.model.valueobjects.OrderlineId;
import com.soulware.tcompro.order.domain.services.OrderCommandService;
import com.soulware.tcompro.order.infrastructure.persistence.jpa.repositories.OrderRepository;
import com.soulware.tcompro.order.infrastructure.persistence.jpa.repositories.OrderStatusRepository;
import com.soulware.tcompro.shared.domain.model.valueobjects.OrderId;
import com.soulware.tcompro.shared.domain.model.valueobjects.PairResource;
import com.soulware.tcompro.shared.domain.model.valueobjects.Quantity;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.shared.infrastructure.support.IdGenerator;
import com.soulware.tcompro.sharedkernel.customer.domain.model.aggregates.Customer;
import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import com.soulware.tcompro.sharedkernel.customer.infrastructure.persistence.jpa.repositories.CustomerRepository;
import com.soulware.tcompro.sharedkernel.policies.domain.model.entities.PaymentMethod;
import com.soulware.tcompro.sharedkernel.policies.domain.model.entities.PickupMethod;
import com.soulware.tcompro.sharedkernel.policies.domain.model.valueobjects.PaymentMethods;
import com.soulware.tcompro.sharedkernel.policies.domain.model.valueobjects.PickupMethods;
import com.soulware.tcompro.sharedkernel.policies.infrastructure.persistence.jpa.repositories.PaymentMethodRepository;
import com.soulware.tcompro.sharedkernel.policies.infrastructure.persistence.jpa.repositories.PickupMethodRepository;
import com.soulware.tcompro.shop.domain.model.aggregates.Shop;
import com.soulware.tcompro.shop.infrastructure.persistence.jpa.repositories.ShopRepository;
import jakarta.validation.constraints.Null;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderCommandServiceImpl implements OrderCommandService {
    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final CustomerRepository customerRepository;
    private final ShopRepository shopRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final PickupMethodRepository pickupMethodRepository;
    private final ProductCatalogRepository productCatalogRepository;
    private final PaymentCommandService paymentCommandService;
    private final DebtCommandService debtCommandService;
    private final IdGenerator idGenerator;
    private final SimpMessagingTemplate messagingTemplate;

    public OrderCommandServiceImpl(
            OrderRepository orderRepository,
            OrderStatusRepository orderStatusRepository,
            CustomerRepository customerRepository,
            ShopRepository shopRepository,
            PaymentMethodRepository paymentMethodRepository,
            PickupMethodRepository pickupMethodRepository,
            ProductCatalogRepository productCatalogRepository,
            PaymentCommandService paymentCommandService,
            DebtCommandService debtCommandService,
            IdGenerator idGenerator,
            SimpMessagingTemplate messagingTemplate
    ){
        this.orderRepository = orderRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.customerRepository = customerRepository;
        this.shopRepository = shopRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.pickupMethodRepository = pickupMethodRepository;
        this.productCatalogRepository = productCatalogRepository;
        this.paymentCommandService = paymentCommandService;
        this.debtCommandService = debtCommandService;
        this.idGenerator = idGenerator;
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public Optional<Order> handle(CreateOrderCommand command) {
        OrderStatus placedStatus = orderStatusRepository.findByName(OrderStatuses.PLACED)
                .orElseThrow(() -> new IllegalStateException("PLACED status not found"));

        PaymentMethod paymentMethod = paymentMethodRepository.findByName(PaymentMethods.valueOf(command.paymentMethod()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid payment method"));

        PickupMethod pickupMethod = pickupMethodRepository.findByType(PickupMethods.valueOf(command.pickupMethod()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid pickup method"));

        List<Long> productIds = command.orderlines().stream()
                .map(PairResource::getProductCatalogId)
                .toList();

        Customer customer = customerRepository.findById(new CustomerId(command.customerId()))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Customer with id " + command.customerId()+ " does not exits"
                ));

        Shop shop = shopRepository.findById(new ShopId(command.shopId()))
                .orElseThrow(() -> new IllegalArgumentException("Shop with id " + command.shopId() + " not found"));

        List<ProductCatalog> productCatalogs = productCatalogRepository.findAllByIds(productIds);

        List<Orderline> items = command.orderlines().stream()
                .map(pair -> {
                    Long catalogId = pair.getProductCatalogId();
                    Integer qty = pair.getQuantity();

                    ProductCatalog pc = productCatalogs.stream()
                            .filter(p -> p.getId().getValue().equals(catalogId))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Catalog product " + catalogId + " not found"));

                    return new Orderline(
                            new OrderlineId(idGenerator.nextOrderlineId()),
                            pc.getProductInformation(),
                            pc.getPrice(),
                            new Quantity(qty),
                            pc.getId(),
                            pc.getImageUrl()
                    );
                })
                .toList();

        Order order = new Order(
                new OrderId(idGenerator.nextOrderId()),
                items,
                customer.getId(),
                shop.getId(),
                paymentMethod,
                pickupMethod,
                placedStatus
        );

        orderRepository.save(order);
        return Optional.of(order);
    }

    @Override
    public Optional<Order> handle(AcceptOrderCommand command) {
        Order order = orderRepository.findByOrderId(new OrderId(command.orderId()))
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        OrderStatus accepted = orderStatusRepository.findByName(OrderStatuses.ACCEPTED)
                .orElseThrow(() -> new IllegalStateException("ACCEPTED status not found"));

        order.changeStatus(accepted);
        orderRepository.save(order);

        notifyOrderStatusChange(order);

        return Optional.of(order);
    }

    @Override
    public Optional<Order> handle(RejectOrderCommand command) {
        Order order = orderRepository.findByOrderId(new OrderId(command.orderId()))
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        OrderStatus rejected = orderStatusRepository.findByName(OrderStatuses.REJECTED)
                .orElseThrow(() -> new IllegalStateException("REJECTED status not found"));

        order.changeStatus(rejected);
        orderRepository.save(order);

        notifyOrderStatusChange(order);

        return Optional.of(order);
    }

    @Override
    public Optional<Order> handle(CancelOrderCommand command) {
        Order order = orderRepository.findByOrderId(new OrderId(command.orderId()))
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        OrderStatus canceled = orderStatusRepository.findByName(OrderStatuses.CANCELED)
                .orElseThrow(() -> new IllegalStateException("CANCELED status not found"));

        order.changeStatus(canceled);
        orderRepository.save(order);

        notifyOrderStatusChange(order);

        return Optional.of(order);
    }

    @Override
    public Optional<Order> handle(AdvanceOrderCommand command) {
        Order order = orderRepository.findByOrderId(new OrderId(command.orderId()))
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        OrderStatuses next = order.nextStatus();

        OrderStatus nextStatus = orderStatusRepository.findByName(next)
                .orElseThrow(() -> new IllegalStateException("Missing status " + next));

        order.changeStatus(nextStatus);
        orderRepository.save(order);

        notifyOrderStatusChange(order);

        if (order.getStatus().getName().equals(OrderStatuses.DELIVERED) || order.getStatus().getName().equals(OrderStatuses.PICKED_UP)) {
            if(order.getPaymentMethod().getName().equals(PaymentMethods.CASH) || order.getPaymentMethod().getName().equals(PaymentMethods.VIRTUAL)) {
                RegisterPaymentCommand  registerPaymentCommand = new RegisterPaymentCommand(order.getOrderId().getValue(), null);
                Optional<Payment> payment = paymentCommandService
                        .handle(registerPaymentCommand);
            }
            RegisterDebtCommand registerDebtCommand = new RegisterDebtCommand(order.getOrderId().getValue());
            Optional<Debt> debt = debtCommandService
                    .handle(registerDebtCommand);
        }

        return Optional.of(order);
    }

    private void notifyOrderStatusChange(Order order) {
        Long orderId = order.getOrderId().getValue();
        String newStatus = order.getStatus().getName().name();
        Long shopId = order.getShopId().getValue();

        OrderStatusNotificationResource notification =
                new OrderStatusNotificationResource(orderId, newStatus, shopId);

        // Example: /topic/orders/123/status
        String topicOrden = "/topic/orders/" + orderId + "/status";
        messagingTemplate.convertAndSend(topicOrden, notification);

        // Example: /topic/shop/456/orders
        String topicTienda = "/topic/shop/" + shopId + "/orders";
        messagingTemplate.convertAndSend(topicTienda, notification);

        System.out.println(">>> Changing status of order with id  " + orderId + " to " + newStatus);
    }
}
