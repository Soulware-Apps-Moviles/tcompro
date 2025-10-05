package com.soulware.tcompro.shop.application.commandservices;

import com.soulware.tcompro.shared.domain.model.valueobjects.Money;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.shared.infrastructure.support.IdGenerator;
import com.soulware.tcompro.sharedkernel.policies.domain.model.entities.PaymentMethod;
import com.soulware.tcompro.sharedkernel.policies.domain.model.entities.PickupMethod;
import com.soulware.tcompro.sharedkernel.policies.domain.model.valueobjects.PaymentMethods;
import com.soulware.tcompro.sharedkernel.policies.domain.model.valueobjects.PickupMethods;
import com.soulware.tcompro.sharedkernel.policies.infrastructure.persistence.jpa.repositories.PaymentMethodRepository;
import com.soulware.tcompro.sharedkernel.policies.infrastructure.persistence.jpa.repositories.PickupMethodRepository;
import com.soulware.tcompro.shop.domain.model.aggregates.Owner;
import com.soulware.tcompro.shop.domain.model.aggregates.Shop;
import com.soulware.tcompro.shop.domain.model.commands.AddShopCommand;
import com.soulware.tcompro.shop.domain.model.valueobjects.OwnerId;
import com.soulware.tcompro.shop.domain.services.ShopCommandService;
import com.soulware.tcompro.shop.infrastructure.persistence.jpa.repositories.OwnerRepository;
import com.soulware.tcompro.shop.infrastructure.persistence.jpa.repositories.ShopRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopCommandServiceImpl implements ShopCommandService {

    private final ShopRepository shopRepository;
    private final OwnerRepository ownerRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final PickupMethodRepository  pickupMethodRepository;
    private final IdGenerator  idGenerator;

    public ShopCommandServiceImpl(
            ShopRepository shopRepository,
            OwnerRepository ownerRepository,
            PaymentMethodRepository paymentMethodRepository,
            PickupMethodRepository pickupMethodRepository,
            IdGenerator idGenerator
    ){
        this.shopRepository = shopRepository;
        this.ownerRepository = ownerRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.pickupMethodRepository = pickupMethodRepository;
        this.idGenerator = idGenerator;
    }


    //
    @Override
    public Optional<Shop> handle(AddShopCommand command){
        if (command == null)
            throw new IllegalArgumentException("Shop command cannot be null");

        if (shopRepository.findByOwner(new OwnerId(command.OwnerId())).isPresent())
            throw new IllegalArgumentException("Owner with id " +  command.OwnerId() + " already has a shop");

        Owner owner = ownerRepository.findById(new OwnerId(command.OwnerId()))
                .orElseThrow(() -> new IllegalArgumentException("Owner with id " + command.OwnerId() + " not found"));


        List<String> paymentMethodNames = command.paymentMethods();
        List<PaymentMethod> paymentMethods = new ArrayList<>();

        for (String name : paymentMethodNames) {
            PaymentMethod method = paymentMethodRepository.findByName(PaymentMethods.valueOf(name))
                    .orElseThrow(() -> new IllegalArgumentException("Payment method with name " + name + " not found"));
            paymentMethods.add(method);
        }

        List<String> pickupMethodNames = command.pickupMethods();
        List<PickupMethod> pickupMethods = new ArrayList<>();

        for (String name : pickupMethodNames) {
            PickupMethod method = pickupMethodRepository.findByType(PickupMethods.valueOf(name))
                    .orElseThrow(() -> new IllegalArgumentException("Pickup method with name " + name + " not found"));
            pickupMethods.add(method);
        }

        BigDecimal maxCredit = command.maxCreditPerCustomer();
        if (maxCredit == null)
            maxCredit = BigDecimal.ZERO; //IT IS NECESSARY TO VALIDATE THAT IT IS NOT NEGATIVE

        Shop shop = new Shop(
                new ShopId(idGenerator.nextShopId()),
                owner.getId(),
                paymentMethods,
                pickupMethods,
                new Money(maxCredit)
        );

        shopRepository.save(shop);
        return Optional.of(shop);
    }

}
