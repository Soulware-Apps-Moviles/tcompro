package com.soulware.tcompro.iam.application.internal;

import com.soulware.tcompro.iam.domain.model.aggregates.Profile;
import com.soulware.tcompro.iam.domain.model.commands.SignUpCommand;
import com.soulware.tcompro.iam.domain.model.entities.Role;
import com.soulware.tcompro.iam.domain.model.valueobjects.Roles;
import com.soulware.tcompro.iam.domain.services.ProfileCommandService;
import com.soulware.tcompro.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.soulware.tcompro.shared.domain.model.valueobjects.EmailAddress;
import com.soulware.tcompro.shared.infrastructure.support.IdGenerator;
import com.soulware.tcompro.sharedkernel.customer.domain.model.aggregates.Customer;
import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import com.soulware.tcompro.sharedkernel.customer.infrastructure.persistence.jpa.repositories.CustomerRepository;
import com.soulware.tcompro.shop.domain.model.aggregates.Owner;
import com.soulware.tcompro.shop.domain.model.aggregates.Shopkeeper;
import com.soulware.tcompro.shop.domain.model.valueobjects.OwnerId;
import com.soulware.tcompro.shop.domain.model.valueobjects.ShopkeeperId;
import com.soulware.tcompro.shop.infrastructure.persistence.jpa.repositories.OwnerRepository;
import com.soulware.tcompro.shop.infrastructure.persistence.jpa.repositories.ShopkeeperRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final CustomerRepository customerRepository;
    private final ShopkeeperRepository shopkeeperRepository;
    private final OwnerRepository ownerRepository;
    private final RoleRepository roleRepository;
    private final IdGenerator idGenerator;

    ProfileCommandServiceImpl(
            CustomerRepository customerRepository,
            ShopkeeperRepository shopkeeperRepository,
            OwnerRepository ownerRepository,
            RoleRepository roleRepository,
            IdGenerator idGenerator
    ){
        this.customerRepository = customerRepository;
        this.shopkeeperRepository = shopkeeperRepository;
        this.ownerRepository = ownerRepository;
        this.roleRepository = roleRepository;
        this.idGenerator = idGenerator;
    }

    @Transactional
    public Optional<Profile> handle(SignUpCommand command) {
        validateUniqueAccountData(command);

        Roles roleName = command.role().getName();
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role Not Found"));

        return switch (roleName) {
            case CUSTOMER -> {
                Customer customer = new Customer(
                        new CustomerId(idGenerator.nextCustomerId()),
                        command.authId(),
                        command.personName(),
                        command.email(),
                        command.phoneNumber()
                );
                customerRepository.save(customer);
                yield Optional.of(customer);
            }
            case SHOPKEEPER -> {
                Shopkeeper shopkeeper = new Shopkeeper(
                        new ShopkeeperId(idGenerator.nextShopkeeperId()),
                        command.authId(),
                        command.personName(),
                        command.email(),
                        command.phoneNumber(),
                        command.shopId()
                );
                shopkeeperRepository.save(shopkeeper);
                yield Optional.of(shopkeeper);
            }
            case SHOP_OWNER -> {
                Owner owner = new Owner(
                        new OwnerId(idGenerator.nextOwnerId()),
                        command.authId(),
                        command.personName(),
                        command.email(),
                        command.phoneNumber()
                );
                ownerRepository.save(owner);
                yield Optional.of(owner);
            }
        };
    }

    private void validateUniqueAccountData(SignUpCommand command){
        if (isEmailTaken(new EmailAddress(command.email().email())))
            throw new IllegalStateException("Email address is already in use");
    }

    private boolean isEmailTaken(EmailAddress email) {
        return customerRepository.findByEmail(email).isPresent() ||
                shopkeeperRepository.findByEmail(email).isPresent() ||
                ownerRepository.findByEmail(email).isPresent();
    }


}
