package com.soulware.tcompro.inventory.domain.model.valueobjects;

import com.soulware.tcompro.shared.domain.model.valueobjects.BaseId;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProductId extends BaseId {
    public ProductId(Long value) { super(value); }
    public ProductId() {}
}
