package com.soulware.tcompro.shop.domain.model.aggregates;

import com.soulware.tcompro.shared.domain.model.valueobjects.Money;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.sharedkernel.policies.domain.model.entities.PaymentMethod;
import com.soulware.tcompro.sharedkernel.policies.domain.model.entities.PickupMethod;
import com.soulware.tcompro.shop.domain.model.valueobjects.Coordinates;
import com.soulware.tcompro.shop.domain.model.valueobjects.OwnerId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shops")
@Setter
@Getter
public class Shop {

    @EmbeddedId
    private ShopId id;

    private String name;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "owner_id", nullable = false, updatable = false)
    )
    private OwnerId owner;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<PaymentMethod> paymentMethods = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<PickupMethod> pickupMethods = new ArrayList<>();

    @Embedded
    private Money maxCreditPerCustomer;

    @Embedded
    private Coordinates coordinates;

    protected Shop () {}

    public Shop(ShopId id,
                OwnerId owner,
                List<PaymentMethod> paymentMethods,
                List<PickupMethod> pickupMethods,
                Money maxCreditPerCustomer,
                Coordinates coordinates,
                String name) {
        this.id = id;
        this.owner = owner;
        this.paymentMethods = paymentMethods;
        this.pickupMethods = pickupMethods;
        this.maxCreditPerCustomer = maxCreditPerCustomer;
        this.coordinates = coordinates;
        this.name = name;
    }
}
