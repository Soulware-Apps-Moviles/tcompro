package com.soulware.tcompro.catalog.domain.model.aggregates;

import com.soulware.tcompro.shared.domain.model.valueobjects.CatalogProductId;
import com.soulware.tcompro.shared.domain.model.valueobjects.Money;
import com.soulware.tcompro.shared.domain.model.valueobjects.ProductInformation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "catalog_products")
public class ProductCatalog {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private CatalogProductId id;

    @Embedded
    private ProductInformation productInformation;

    @Embedded
    private Money price;
}
