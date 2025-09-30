package com.soulware.tcompro.shared.domain.model.valueobjects;

import org.springframework.util.Assert;

public final class PairResource<Long, Quantity> {
    private final Long productCatalogId;
    private final Quantity quantity;

    private PairResource(Long productCatalogId, Quantity quantity) {
        Assert.notNull(productCatalogId, "First must not be null");
        Assert.notNull(quantity, "Second must not be null");
        this.productCatalogId = productCatalogId;
        this.quantity = quantity;
    }

    public static <S, T> PairResource<S, T> of(S first, T second) {
        return new PairResource<S, T>(first, second);
    }


    public Long getProductCatalogId() {
        return this.productCatalogId;
    }

    public Quantity getQuantity() {
        return this.quantity;
    }
}
