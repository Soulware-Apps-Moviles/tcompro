package com.soulware.tcompro.shopping.domain.model.valueobjects;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.soulware.tcompro.shared.domain.model.valueobjects.BaseId;
import com.soulware.tcompro.shopping.domain.model.aggregates.ShoppingList;
import jakarta.persistence.Embeddable;

@Embeddable
public class ShoppingListId extends BaseId {
    public ShoppingListId() {}
    public ShoppingListId(Long value){super(value);}
}
