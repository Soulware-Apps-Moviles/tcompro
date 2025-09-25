package com.soulware.tcompro.shared.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.NonNull;

@Embeddable
public record ProductInformation(@NonNull String name, @NonNull String description, @NonNull String category) {

    @Deprecated
    public ProductInformation() {this("GENERIC NAME", "GENERIC DESCRIPTION", "OTHER");}

    public ProductInformation{
        if (name.isEmpty()) throw new IllegalArgumentException("Product name cannot be empty");
        if (description.isEmpty()) throw new IllegalArgumentException("Product description cannot be empty");
        if (category.isEmpty()) throw new IllegalArgumentException("Product category cannot be empty");
        try {
            Categories.valueOf(category.trim());

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Product category '" + category + "' is invalid. Must be one of: " + java.util.Arrays.toString(Categories.values()));
        }
    }

}
