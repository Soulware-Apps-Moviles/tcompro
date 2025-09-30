package com.soulware.tcompro.shopping.domain.model.entities;

import com.soulware.tcompro.shared.domain.model.valueobjects.CatalogProductId;
import com.soulware.tcompro.shared.domain.model.valueobjects.Money;
import com.soulware.tcompro.shared.domain.model.valueobjects.ProductInformation;
import com.soulware.tcompro.shared.domain.model.valueobjects.Quantity;
import com.soulware.tcompro.shopping.domain.model.valueobjects.ShoppingListItemId;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shopping_list_items")
@Setter
@Getter
public class ShoppingListItem extends CatalogProductReference {

    @EmbeddedId
    private ShoppingListItemId id;

    @Embedded
    private Quantity quantity;

    protected ShoppingListItem() {}

    public ShoppingListItem(ShoppingListItemId id, CatalogProductId catalogProductId, ProductInformation information, Money price, Quantity quantity) {
        this.id = id;
        this.catalogProductId = catalogProductId;
        this.information = information;
        this.price = price;
        this.quantity = quantity;
    }
}
