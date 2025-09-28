package com.soulware.tcompro.iam.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record AuthId(UUID authId) {
}
