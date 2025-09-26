package com.soulware.tcompro.order.domain.model.entities;

import com.soulware.tcompro.order.domain.model.valueobjects.OrderlineId;
import com.soulware.tcompro.shared.domain.model.valueobjects.CatalogProductId;
import com.soulware.tcompro.shared.domain.model.valueobjects.Money;
import com.soulware.tcompro.shared.domain.model.valueobjects.ProductInformation;
import com.soulware.tcompro.shopping.domain.model.valueobjects.Quantity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "orderlines")
@Setter
@Getter
public class Orderline {

    @EmbeddedId
    private OrderlineId id;

    @Embedded
    private ProductInformation information;

    @Embedded
    private Money price;

    @Embedded
    private Quantity quantity;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "catalog_product_id", nullable = false)
    )
    private CatalogProductId  catalogProductId;

    protected Orderline() {}

    public Orderline(ProductInformation information, Money price, Quantity quantity, CatalogProductId catalogProductId) {
        this.information = information;
        this.price = price;
        this.quantity = quantity;
        this.catalogProductId = catalogProductId;
    }

    public Money getSubtotal(){
        return new Money(this.price.amount().multiply(BigDecimal.valueOf(this.quantity.value())));
    }
}
