package com.soulware.tcompro.iam.domain.model.queries;

import com.soulware.tcompro.iam.domain.model.entities.Role;

import java.util.UUID;

public record GetByAuthIdAndRoleQuery(UUID authId, Role role) {
}
