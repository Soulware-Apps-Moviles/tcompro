package com.soulware.tcompro.shopping.domain.model.valueobjects;

import com.soulware.tcompro.shared.domain.model.valueobjects.BaseId;
import jakarta.persistence.Embeddable;

@Embeddable
public class ShoppingListItemId extends BaseId {
    public ShoppingListItemId(Long value){super(value);}
    public ShoppingListItemId(){}
}
