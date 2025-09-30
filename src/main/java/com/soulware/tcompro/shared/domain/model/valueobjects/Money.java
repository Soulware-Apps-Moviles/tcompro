package com.soulware.tcompro.shared.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public record Money(BigDecimal amount) {
    public Money(BigDecimal amount) {
        this.amount = amount;
    }
    public Money add(Money other) {
        return new Money(this.amount.add(other.amount));
    }

}
