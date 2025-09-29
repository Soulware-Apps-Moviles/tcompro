package com.soulware.tcompro.inventory.application.internal.commandservices;

import com.soulware.tcompro.catalog.domain.model.aggregates.ProductCatalog;
import com.soulware.tcompro.catalog.infrastructure.persistence.jpa.repositories.ProductCatalogRepository;
import com.soulware.tcompro.inventory.domain.model.aggregates.Product;
import com.soulware.tcompro.inventory.domain.model.commands.AddProductCommand;
import com.soulware.tcompro.inventory.domain.model.commands.UpdateProductCommand;
import com.soulware.tcompro.inventory.domain.model.valueobjects.ProductId;
import com.soulware.tcompro.inventory.domain.services.ProductCommandService;
import com.soulware.tcompro.inventory.infrastructure.persistence.jpa.repositories.ProductRepository;
import com.soulware.tcompro.shared.domain.model.valueobjects.CatalogProductId;
import com.soulware.tcompro.shared.domain.model.valueobjects.Money;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.shared.infrastructure.support.IdGenerator;
import com.soulware.tcompro.shop.domain.model.aggregates.Shop;
import com.soulware.tcompro.shop.infrastructure.persistence.jpa.repositories.ShopRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {
    private final ShopRepository shopRepository;
    private final ProductCatalogRepository productCatalogRepository;
    private final ProductRepository productRepository;
    private final IdGenerator  idGenerator;

    public ProductCommandServiceImpl(
            ShopRepository shopRepository,
            ProductCatalogRepository productCatalogRepository,
            ProductRepository productRepository,
            IdGenerator  idGenerator
    ) {
        this.shopRepository = shopRepository;
        this.productCatalogRepository = productCatalogRepository;
        this.productRepository = productRepository;
        this.idGenerator = idGenerator;
    }

    @Override
    public Optional<Product> handle(AddProductCommand command) {

        Shop shop = shopRepository.findById(new ShopId(command.shopId()))
                .orElseThrow(() -> new RuntimeException("Shop with id " + command.shopId() + " not found"));

        ProductCatalog productCatalog = productCatalogRepository.findById(new CatalogProductId(command.catalogProductId()))
                .orElseThrow(() -> new RuntimeException("Product catalog with id " + command.catalogProductId() + " not found"));

        if(productRepository.existsByCatalogProductIdAndShopId(productCatalog.getId(), shop.getId()))
            throw new IllegalArgumentException("A product with catalog product id " + command.catalogProductId() + " and shop " + shop.getId().getValue() + " already exists");

        BigDecimal price = productCatalog.getPrice().amount();

        if (command.price() != null) price = command.price();

        Product product = new Product(
                new ProductId(idGenerator.nextProductId()),
                shop.getId(),
                productCatalog.getId(),
                new Money(price)
        );
        productRepository.save(product);
        return Optional.of(product);
    }

    @Override
    public Optional<Product> handle(UpdateProductCommand command) {
        Product product = productRepository.findById(new ProductId(command.productId()))
                .orElseThrow(() -> new RuntimeException("Product with id " + command.productId() + " not found"));

        if (command.price() == null && command.isAvailable() == null)
            throw new RuntimeException("Price and Availability are both null");

        if (command.price() != null) {
            product.changePrice(new Money(command.price()));
        }

        if (command.isAvailable() != null) {
            if (command.isAvailable()) {
                product.markAvailable();
            } else {
                product.markUnavailable();
            }
        }

        productRepository.save(product);
        return Optional.of(product);

    }
}
