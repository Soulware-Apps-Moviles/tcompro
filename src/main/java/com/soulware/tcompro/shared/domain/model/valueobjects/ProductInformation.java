package com.soulware.tcompro.shared.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.NonNull;

@Embeddable
public record ProductInformation(@NonNull String name, @NonNull String description) {

    @Deprecated
    public ProductInformation() {this("GENERIC NAME", "GENERIC DESCRIPTION");}

    public ProductInformation{
        if (name.isEmpty()) throw new IllegalArgumentException("Product name cannot be empty");
        if (description.isEmpty()) throw new IllegalArgumentException("Product description cannot be empty");
    }

}
