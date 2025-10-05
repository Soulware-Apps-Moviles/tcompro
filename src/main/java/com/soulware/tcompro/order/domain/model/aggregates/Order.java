package com.soulware.tcompro.order.domain.model.aggregates;

import com.soulware.tcompro.order.domain.model.entities.OrderStatus;
import com.soulware.tcompro.order.domain.model.entities.Orderline;
import com.soulware.tcompro.order.domain.model.valueobjects.OrderStatuses;
import com.soulware.tcompro.shared.domain.model.valueobjects.Money;
import com.soulware.tcompro.shared.domain.model.valueobjects.OrderId;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import com.soulware.tcompro.sharedkernel.policies.domain.model.entities.PaymentMethod;
import com.soulware.tcompro.sharedkernel.policies.domain.model.entities.PickupMethod;
import com.soulware.tcompro.sharedkernel.policies.domain.model.valueobjects.PickupMethods;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
@Setter
@Getter
public class Order {

    @EmbeddedId
    private OrderId orderId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id", nullable = false)
    private List<Orderline> orderlines;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "status_id", nullable = false)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "pickup_method_id", nullable = false)
    private PickupMethod pickupMethod;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "customer_id", nullable = false, updatable = false)
    )
    private CustomerId customerId;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "shop_id", nullable = false, updatable = false)
    )
    private ShopId shopId;

    protected Order() {}

    public Order(OrderId id,
                 List<Orderline> orderlines,
                 CustomerId customerId,
                 ShopId shopId,
                 PaymentMethod paymentMethod,
                 PickupMethod pickupMethod,
                 OrderStatus initialStatus) {
        this.orderId = id;
        this.orderlines = orderlines;
        this.customerId = customerId;
        this.shopId = shopId;
        this.paymentMethod = paymentMethod;
        this.pickupMethod = pickupMethod;
        this.status = initialStatus;
    }

    public Money getTotal() {
        return orderlines.stream()
                .map(Orderline::getSubtotal)
                .reduce(new Money(BigDecimal.ZERO), Money::add);
    }

    public void changeStatus(OrderStatus newStatus) {
        OrderStatuses current = this.status.getName();
        OrderStatuses target = newStatus.getName();

        if (!current.canTransitionTo(target)) {
            throw new IllegalStateException(
                    "Cannot transition from " + current + " to " + target
            );
        }

        // READY → PICKED_UP , pickupMethod = IN_SHOP
        if (current == OrderStatuses.READY && target == OrderStatuses.PICKED_UP
                && this.pickupMethod.getType() != PickupMethods.SHOP_PICK_UP) {
            throw new IllegalStateException("Only IN_SHOP orders can be picked up.");
        }

        // READY → DISPATCHED , pickupMethod = DELIVERY
        if (current == OrderStatuses.READY && target == OrderStatuses.DISPATCHED
                && this.pickupMethod.getType() != PickupMethods.DELIVERY) {
            throw new IllegalStateException("Only DELIVERY orders can be dispatched.");
        }

        this.status = newStatus;
    }

    // dentro del aggregate
    public OrderStatuses nextStatus() {
        OrderStatuses current = this.status.getName();

        return switch (current) {
            case ACCEPTED -> OrderStatuses.READY;
            case READY -> {
                if (pickupMethod.getType() == PickupMethods.SHOP_PICK_UP) {
                    yield OrderStatuses.PICKED_UP;
                } else if (pickupMethod.getType() == PickupMethods.DELIVERY) {
                    yield OrderStatuses.DISPATCHED;
                }
                throw new IllegalStateException("Unsupported pickup method for READY order.");
            }
            case DISPATCHED -> OrderStatuses.DELIVERED;
            default -> throw new IllegalStateException("Order cannot advance from status " + current);
        };
    }
}

