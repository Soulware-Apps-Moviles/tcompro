package com.soulware.tcompro.iam.application.internal;

import com.soulware.tcompro.iam.domain.model.aggregates.Profile;
import com.soulware.tcompro.iam.domain.model.queries.GetByAuthIdAndRoleQuery;
import com.soulware.tcompro.iam.domain.model.valueobjects.AuthId;
import com.soulware.tcompro.iam.domain.model.valueobjects.Roles;
import com.soulware.tcompro.iam.domain.services.ProfileQueryService;
import com.soulware.tcompro.sharedkernel.customer.infrastructure.persistence.jpa.repositories.CustomerRepository;
import com.soulware.tcompro.shop.infrastructure.persistence.jpa.repositories.OwnerRepository;
import com.soulware.tcompro.shop.infrastructure.persistence.jpa.repositories.ShopkeeperRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {
    private final CustomerRepository customerRepository;
    private final ShopkeeperRepository shopkeeperRepository;
    private final OwnerRepository ownerRepository;

    ProfileQueryServiceImpl(
            CustomerRepository customerRepository,
            ShopkeeperRepository shopkeeperRepository,
            OwnerRepository ownerRepository
    ) {
        this.customerRepository = customerRepository;
        this.shopkeeperRepository = shopkeeperRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Profile> handle(GetByAuthIdAndRoleQuery query) {
        AuthId authId = new AuthId(query.authId());
        Roles roleName = query.role();

        return switch (roleName) {
            case CUSTOMER -> customerRepository.findByAuthId(authId)
                    .map(Profile.class::cast);

            case SHOPKEEPER -> shopkeeperRepository.findByAuthId(authId)
                    .map(Profile.class::cast);

            case SHOP_OWNER -> ownerRepository.findByAuthId(authId)
                    .map(Profile.class::cast);
        };
    }
}