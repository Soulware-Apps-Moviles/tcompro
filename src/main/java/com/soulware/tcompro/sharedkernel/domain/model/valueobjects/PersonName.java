package com.soulware.tcompro.sharedkernel.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.NonNull;

@Embeddable
public record PersonName(@NonNull String firstName, @NonNull String lastName) {

    @Deprecated
    public PersonName() {
        this("John","Doe");
    }

    public PersonName {
        if (firstName.isEmpty()) {
            throw new IllegalArgumentException("firstName cannot be null or empty");
        }
        if (lastName.isEmpty()) {
            throw new IllegalArgumentException("lastName cannot be null or empty");
        }
    }
}
