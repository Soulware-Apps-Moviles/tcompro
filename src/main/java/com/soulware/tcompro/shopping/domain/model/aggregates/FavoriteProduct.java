package com.soulware.tcompro.shopping.domain.model.aggregates;

import com.soulware.tcompro.shared.domain.model.valueobjects.Money;
import com.soulware.tcompro.shared.domain.model.valueobjects.ProductInformation;
import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import com.soulware.tcompro.shopping.domain.model.entities.CatalogProductReference;
import com.soulware.tcompro.shopping.domain.model.valueobjects.FavoriteProductId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "favorite_products")
@Setter
@Getter
public class FavoriteProduct extends CatalogProductReference{

    @EmbeddedId
    private FavoriteProductId id;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "customer_id", nullable = false, updatable = false)
    )
    private CustomerId  customerId;

    protected FavoriteProduct() {}

    public FavoriteProduct(ProductInformation information, Money price, CustomerId customerId) {
        this.information = information;
        this.price = price;
        this.customerId = customerId;
    }
}
