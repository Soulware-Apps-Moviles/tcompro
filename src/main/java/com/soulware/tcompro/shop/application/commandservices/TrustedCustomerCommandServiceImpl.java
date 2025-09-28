package com.soulware.tcompro.shop.application.commandservices;

import com.soulware.tcompro.shared.domain.model.valueobjects.Money;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.shared.infrastructure.support.IdGenerator;
import com.soulware.tcompro.sharedkernel.customer.domain.model.aggregates.Customer;
import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import com.soulware.tcompro.sharedkernel.customer.infrastructure.persistence.jpa.repositories.CustomerRepository;
import com.soulware.tcompro.shop.domain.model.aggregates.Shop;
import com.soulware.tcompro.shop.domain.model.aggregates.TrustedCustomer;
import com.soulware.tcompro.shop.domain.model.commands.AddTrustedCustomerCommand;
import com.soulware.tcompro.shop.domain.model.valueobjects.TrustedCustomerId;
import com.soulware.tcompro.shop.domain.services.TrustedCustomerCommandService;
import com.soulware.tcompro.shop.infrastructure.persistence.jpa.repositories.ShopRepository;
import com.soulware.tcompro.shop.infrastructure.persistence.jpa.repositories.TrustedCustomerRepository;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TrustedCustomerCommandServiceImpl implements TrustedCustomerCommandService {
    private final CustomerRepository customerRepository;
    private final ShopRepository shopRepository;
    private final TrustedCustomerRepository  trustedCustomerRepository;
    private final IdGenerator idGenerator;

    public TrustedCustomerCommandServiceImpl(
            CustomerRepository customerRepository,
            ShopRepository shopRepository,
            TrustedCustomerRepository trustedCustomerRepository,
            IdGenerator idGenerator
    ){
        this.customerRepository = customerRepository;
        this.shopRepository = shopRepository;
        this.trustedCustomerRepository = trustedCustomerRepository;
        this.idGenerator = idGenerator;
    }

    @Override
    public Optional<TrustedCustomer> handle(AddTrustedCustomerCommand command){
        Customer customer = customerRepository.findById(new CustomerId(command.customerId()))
                .orElseThrow(() -> new RuntimeException("Customer with id " + command.customerId() + " not found"));

        Shop shop = shopRepository.findById(new ShopId(command.shopId()))
                .orElseThrow(() -> new RuntimeException("Shop with id " + command.shopId() + " not found"));

        BigDecimal creditLimit = shop.getMaxCreditPerCustomer().amount();

        if (command.creditLimit() != null) creditLimit = command.creditLimit();

        TrustedCustomer trustedCustomer = new TrustedCustomer(
                new TrustedCustomerId(idGenerator.nextTrustedCustomerId()),
                new Money(creditLimit),
                customer.getId(),
                shop.getId()
        );
        trustedCustomerRepository.save(trustedCustomer);
        return Optional.of(trustedCustomer);
    }
}
