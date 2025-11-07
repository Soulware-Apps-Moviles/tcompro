package com.soulware.tcompro.shopping.domain.model.entities;

import com.soulware.tcompro.shared.domain.model.valueobjects.CatalogProductId;
import com.soulware.tcompro.shared.domain.model.valueobjects.Money;
import com.soulware.tcompro.shared.domain.model.valueobjects.ProductInformation;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class CatalogProductReference {
    @Embedded
    public ProductInformation information;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "catalog_product_id", nullable = false)
    )
    public CatalogProductId catalogProductId;

    @Embedded
    public Money price;

    public String imageUrl;
}
