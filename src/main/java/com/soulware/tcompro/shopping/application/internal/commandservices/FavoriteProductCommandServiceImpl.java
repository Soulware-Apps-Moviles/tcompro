package com.soulware.tcompro.shopping.application.internal.commandservices;

import com.soulware.tcompro.catalog.domain.model.aggregates.ProductCatalog;
import com.soulware.tcompro.catalog.infrastructure.persistence.jpa.repositories.ProductCatalogRepository;
import com.soulware.tcompro.shared.domain.model.valueobjects.CatalogProductId;
import com.soulware.tcompro.shared.infrastructure.support.IdGenerator;
import com.soulware.tcompro.sharedkernel.customer.domain.model.aggregates.Customer;
import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import com.soulware.tcompro.sharedkernel.customer.infrastructure.persistence.jpa.repositories.CustomerRepository;
import com.soulware.tcompro.shopping.domain.model.aggregates.FavoriteProduct;
import com.soulware.tcompro.shopping.domain.model.commands.AddFavoriteProductCommand;
import com.soulware.tcompro.shopping.domain.model.commands.RemoveFavoriteProductCommand;
import com.soulware.tcompro.shopping.domain.model.valueobjects.FavoriteProductId;
import com.soulware.tcompro.shopping.domain.services.FavoriteProductCommandService;
import com.soulware.tcompro.shopping.infrastructure.persistence.jpa.repositories.FavoriteProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoriteProductCommandServiceImpl implements FavoriteProductCommandService {
    private final FavoriteProductRepository favoriteProductRepository;
    private final CustomerRepository customerRepository;
    private final ProductCatalogRepository productCatalogRepository;
    private final IdGenerator idGenerator;

    public FavoriteProductCommandServiceImpl(
            FavoriteProductRepository favoriteProductRepository,
            ProductCatalogRepository productCatalogRepository,
            CustomerRepository customerRepository,
            IdGenerator idGenerator
    ){
        this.favoriteProductRepository = favoriteProductRepository;
        this.productCatalogRepository = productCatalogRepository;
        this.customerRepository = customerRepository;
        this.idGenerator = idGenerator;
    }

    @Override
    public Optional<FavoriteProduct> handle(AddFavoriteProductCommand command){

        if (favoriteProductRepository.existsByCustomerIdAndCatalogProductId(new CustomerId(command.customerId()), new CatalogProductId(command.catalogProductId())))
            throw new IllegalArgumentException(
                    "A catalog product with id " + command.catalogProductId() + " has already vinculate with customer with id " + command.customerId()
            );

        ProductCatalog productCatalog = productCatalogRepository.findById(new CatalogProductId(command.catalogProductId()))
                .orElseThrow(() -> new RuntimeException("Catalog product with id " +  command.catalogProductId() + " not found"));

        Customer customer = customerRepository.findById(new CustomerId(command.customerId()))
                .orElseThrow(() -> new RuntimeException("Customer with id " +  command.customerId() + " not found"));

        FavoriteProduct favoriteProduct = new FavoriteProduct(
                new FavoriteProductId(idGenerator.nextFavoriteProductId()),
                productCatalog.getId(),
                productCatalog.getProductInformation(),
                productCatalog.getPrice(),
                customer.getId()
        );

        favoriteProductRepository.save(favoriteProduct);
        return Optional.of(favoriteProduct);
    }

    @Override
    public Optional<Long> handle(RemoveFavoriteProductCommand command){
        FavoriteProduct favoriteProduct = favoriteProductRepository.findById(new FavoriteProductId(command.favoriteProductId()))
                .orElseThrow(() -> new RuntimeException("Favorite product with id " + command.favoriteProductId() + " not found"));
        favoriteProductRepository.delete(favoriteProduct);
        return Optional.of(favoriteProduct.getId().getValue());
    }
}
