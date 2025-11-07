package com.soulware.tcompro.inventory.domain.model.aggregates;

import com.soulware.tcompro.inventory.domain.model.valueobjects.ProductId;
import com.soulware.tcompro.shared.domain.model.valueobjects.CatalogProductId;
import com.soulware.tcompro.shared.domain.model.valueobjects.Money;
import com.soulware.tcompro.shared.domain.model.valueobjects.ProductInformation;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "products")
public class Product {

    @EmbeddedId
    private ProductId id;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "shop_id", nullable = false)
    )
    private ShopId shopId;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "catalog_product_id", nullable = false)
    )
    private CatalogProductId  catalogProductId;

    @Embedded
    private Money price;

    @Embedded
    private ProductInformation information;

    private boolean isAvailable;

    private String imageUrl;

    protected Product() {}

    public Product(
            ProductId productId,
            ShopId shopId,
            CatalogProductId catalogProductId,
            Money price,
            ProductInformation productInformation,
            String imageUrl
    ){
        this.id = productId;
        this.shopId = shopId;
        this.catalogProductId = catalogProductId;
        this.price = price;
        this.information = productInformation;
        this.isAvailable = true;
        this.imageUrl = imageUrl;
    }

    public void changePrice(Money price){
        if(price.amount().intValue() > 0){
            this.price = price;
        }
    }

    public void markUnavailable(){
        this.isAvailable = false;
    }

    public void markAvailable(){
        this.isAvailable = true;
    }
}
