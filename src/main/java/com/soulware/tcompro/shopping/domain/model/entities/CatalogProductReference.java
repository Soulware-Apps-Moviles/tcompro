package com.soulware.tcompro.shopping.domain.model.entities;

import com.soulware.tcompro.shared.domain.model.valueobjects.Money;
import com.soulware.tcompro.shared.domain.model.valueobjects.ProductInformation;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class CatalogProductReference {
    @Embedded
    public ProductInformation information;

    @Embedded
    public Money price;
}
