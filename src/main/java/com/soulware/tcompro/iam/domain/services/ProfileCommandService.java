package com.soulware.tcompro.iam.domain.services;

import com.soulware.tcompro.iam.domain.model.aggregates.Profile;
import com.soulware.tcompro.iam.domain.model.commands.SignUpCommand;
import com.soulware.tcompro.sharedkernel.customer.domain.model.aggregates.Customer;
import com.soulware.tcompro.shop.domain.model.aggregates.Owner;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Profile> handle(SignUpCommand command);
}
