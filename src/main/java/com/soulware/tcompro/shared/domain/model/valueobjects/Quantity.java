package com.soulware.tcompro.shared.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.NonNull;

@Embeddable
public record Quantity(@NonNull Integer value) {
    public Quantity(Integer value) {
        if (value < 1) throw new IllegalArgumentException("Quantity value cannot be less than 1");
        this.value = value;
    }
}
