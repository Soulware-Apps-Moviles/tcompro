package com.soulware.tcompro.shared.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CatalogProductId extends BaseId implements Serializable {
    public CatalogProductId() {}
    public CatalogProductId(Long value) { super(value); }
}
