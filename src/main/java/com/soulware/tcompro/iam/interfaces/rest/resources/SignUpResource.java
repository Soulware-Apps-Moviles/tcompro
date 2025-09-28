package com.soulware.tcompro.iam.interfaces.rest.resources;

import jakarta.annotation.Nullable;

import java.util.UUID;

public record SignUpResource(
        UUID authId,
        String firstName,
        String lastName,
        String email,
        @Nullable String phone,
        @Nullable Long shopId,
        String role
) {
}
