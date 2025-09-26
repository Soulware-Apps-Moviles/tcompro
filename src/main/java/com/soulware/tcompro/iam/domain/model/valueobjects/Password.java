package com.soulware.tcompro.iam.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.NonNull;

@Embeddable
public record Password(@NonNull String value) {

    @Deprecated
    public Password() {this("GENERIC PASSWORD");}

    public Password{
        if (value.isEmpty()) throw new IllegalArgumentException("Password cannot be empty");
    }
}
