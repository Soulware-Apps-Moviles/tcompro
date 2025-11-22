package com.soulware.tcompro.iam.domain.services;

import com.soulware.tcompro.iam.domain.model.aggregates.Profile;
import com.soulware.tcompro.iam.domain.model.queries.GetByAuthIdAndRoleQuery;

import java.util.Optional;

public interface ProfileQueryService {

    Optional<Profile> handle(GetByAuthIdAndRoleQuery query);

}
