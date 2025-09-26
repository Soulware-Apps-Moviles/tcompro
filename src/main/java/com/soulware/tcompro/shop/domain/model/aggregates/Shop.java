package com.soulware.tcompro.shop.domain.model.aggregates;

import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.shop.domain.model.entities.Policy;
import com.soulware.tcompro.shop.domain.model.valueobjects.OwnerId;
import com.soulware.tcompro.shop.domain.model.valueobjects.PolicyId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shops")
@Setter
@Getter
public class Shop {

    @EmbeddedId
    private ShopId id;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "owner_id", nullable = false, updatable = false)
    )
    private OwnerId owner;

    @OneToOne(cascade = CascadeType.ALL)
    private Policy policy;

    protected Shop () {}

    public Shop(OwnerId owner,  Policy policy) {
        this.owner = owner;
        this.policy = policy;
    }

}
