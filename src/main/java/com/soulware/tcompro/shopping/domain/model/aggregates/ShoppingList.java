package com.soulware.tcompro.shopping.domain.model.aggregates;

import com.soulware.tcompro.shared.domain.model.valueobjects.CatalogProductId;
import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import com.soulware.tcompro.shopping.domain.model.entities.ShoppingListItem;
import com.soulware.tcompro.shopping.domain.model.valueobjects.ShoppingListId;
import com.soulware.tcompro.shopping.domain.model.valueobjects.ShoppingListItemId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "shopping_lists")
@Setter
@Getter
public class ShoppingList {
    @EmbeddedId
    private ShoppingListId id;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "customer_id", nullable = false, updatable = false)
    )
    private CustomerId customerId;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "shopping_list_id", nullable = false)
    private List<ShoppingListItem> items;

    protected ShoppingList() {}

    public ShoppingList(ShoppingListId id, CustomerId customerId, String name, List<ShoppingListItem> items) {
        this.id = id;
        this.customerId = customerId;
        this.name = name;
        this.items = items;
    }

    public Boolean isInList(CatalogProductId catalogProductId) {
        return items.stream()
                .anyMatch(item -> item.catalogProductId.equals(catalogProductId));
    }

    public void addItem(ShoppingListItem item) {
        this.items.add(item);
    }

    public void removeItem(CatalogProductId catalogProductId) {
        this.items.removeIf(i -> i.catalogProductId.equals(catalogProductId));
    }


    public void renameList(String name){
        this.name = name;
    }
}
