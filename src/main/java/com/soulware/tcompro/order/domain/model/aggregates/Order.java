package com.soulware.tcompro.order.domain.model.aggregates;

import com.soulware.tcompro.order.domain.model.entities.OrderStatus;
import com.soulware.tcompro.order.domain.model.entities.Orderline;
import com.soulware.tcompro.shared.domain.model.valueobjects.Money;
import com.soulware.tcompro.shared.domain.model.valueobjects.OrderId;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "orders")
@Setter
@Getter
public class Order {

    @EmbeddedId
    private OrderId orderId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    private List<Orderline> orderlines;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "status_id", nullable = false)
    private OrderStatus status;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "customer_id",  nullable = false, updatable = false)
    )
    private CustomerId customerId;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "shop_id",  nullable = false, updatable = false)
    )
    private ShopId shopId;

    protected Order (){}

    public Order(List<Orderline> orderlines, CustomerId customerId, ShopId shopId) {
        this.orderlines = orderlines;
        this.customerId = customerId;
        this.shopId = shopId;
    }

    public Money getTotal() {
        return null;
    }

    public void changeStatus(OrderStatus orderStatus) {
        this.status = orderStatus;
    }
}
