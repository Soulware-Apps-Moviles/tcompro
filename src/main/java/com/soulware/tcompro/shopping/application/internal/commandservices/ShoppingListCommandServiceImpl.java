package com.soulware.tcompro.shopping.application.internal.commandservices;

import com.soulware.tcompro.catalog.domain.model.aggregates.ProductCatalog;
import com.soulware.tcompro.catalog.infrastructure.persistence.jpa.repositories.ProductCatalogRepository;
import com.soulware.tcompro.shared.domain.model.valueobjects.CatalogProductId;
import com.soulware.tcompro.shared.domain.model.valueobjects.Quantity;
import com.soulware.tcompro.shared.infrastructure.support.IdGenerator;
import com.soulware.tcompro.sharedkernel.customer.domain.model.aggregates.Customer;
import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import com.soulware.tcompro.sharedkernel.customer.infrastructure.persistence.jpa.repositories.CustomerRepository;
import com.soulware.tcompro.shopping.domain.model.aggregates.ShoppingList;
import com.soulware.tcompro.shopping.domain.model.commands.AddShoppingListCommand;
import com.soulware.tcompro.shopping.domain.model.commands.RemoveShoppingListCommand;
import com.soulware.tcompro.shopping.domain.model.commands.UpdateShoppingListCommand;
import com.soulware.tcompro.shopping.domain.model.entities.ShoppingListItem;
import com.soulware.tcompro.shopping.domain.model.valueobjects.ShoppingListId;
import com.soulware.tcompro.shopping.domain.model.valueobjects.ShoppingListItemId;
import com.soulware.tcompro.shopping.domain.services.ShoppingListCommandService;
import com.soulware.tcompro.shopping.infrastructure.persistence.jpa.repositories.ShoppingListRepository;
import com.soulware.tcompro.shared.domain.model.valueobjects.PairResource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListCommandServiceImpl implements ShoppingListCommandService {
    private final ShoppingListRepository shoppingListRepository;
    private final CustomerRepository customerRepository;
    private final ProductCatalogRepository  productCatalogRepository;
    private final IdGenerator idGenerator;

    public ShoppingListCommandServiceImpl(
            ShoppingListRepository shoppingListRepository,
            CustomerRepository customerRepository,
            ProductCatalogRepository productCatalogRepository,
            IdGenerator idGenerator
    ){
        this.shoppingListRepository = shoppingListRepository;
        this.customerRepository = customerRepository;
        this.productCatalogRepository = productCatalogRepository;
        this.idGenerator = idGenerator;
    }

    @Override
    public Optional<ShoppingList> handle(AddShoppingListCommand command){
        Customer customer = customerRepository.findById(new CustomerId(command.customerId()))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Customer with id " + command.customerId()+ " does not exits"
                ));

        List<Long> productIds = command.items().stream()
                .map(PairResource::getProductCatalogId)
                .toList();

        List<ProductCatalog> productCatalogs = productCatalogRepository.findAllByIds(productIds);

        if (productCatalogs.isEmpty()) throw new IllegalArgumentException("No product catalog found");

        String name =  command.name();

        if (name.isEmpty()) throw new IllegalArgumentException("Shopping list name is empty");

        List<ShoppingListItem> items = command.items().stream()
                .map(pair -> {
                    Long catalogId = pair.getProductCatalogId();
                    Integer qty = pair.getQuantity();

                    ProductCatalog pc = productCatalogs.stream()
                            .filter(p -> p.getId().getValue().equals(catalogId))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Catalog product " + catalogId + " not found"));

                    return new ShoppingListItem(
                            new ShoppingListItemId(idGenerator.nextShoppingListItemId()),
                            pc.getId(),
                            pc.getProductInformation(),
                            pc.getPrice(),
                            new Quantity(qty),
                            pc.getImageUrl()
                    );
                })
                .toList();

        ShoppingList shoppingList = new ShoppingList(
                new ShoppingListId(idGenerator.nextShoppingListId()),
                customer.getId(),
                name,
                items
        );

        shoppingListRepository.save(shoppingList);
        return Optional.of(shoppingList);

    }

    @Override
    public Optional<Long> handle(RemoveShoppingListCommand command){
        ShoppingList shoppingList = shoppingListRepository.findById(new ShoppingListId(command.shoppingListId()))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Shopping list with id " + command.shoppingListId() + " does not exist"
                ));
        shoppingListRepository.delete(shoppingList);
        return Optional.of(shoppingList.getId().getValue());
    }

    @Override
    public Optional<ShoppingList> handle(UpdateShoppingListCommand command){
        if (command.name() == null && command.item() == null) throw new IllegalArgumentException(
                "Shopping list id and product catalog id cannot be empty"
        );
        ShoppingList shoppingList = shoppingListRepository.findById(new ShoppingListId(command.shoppingListId()))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Shopping list with id " + command.shoppingListId() + " does not exist"
                ));

        if (command.item() != null){
            ProductCatalog productCatalog = productCatalogRepository.findById(new CatalogProductId(command.item().getProductCatalogId()))
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Catalog product with id " + command.item().getProductCatalogId() + " does not exist"
                    ));
            if (shoppingList.isInList(productCatalog.getId())){
                shoppingList.removeItem(new CatalogProductId(command.item().getProductCatalogId()));
                shoppingListRepository.save(shoppingList);
            }else {
                shoppingList.addItem(
                        new ShoppingListItem(
                                new ShoppingListItemId(idGenerator.nextShoppingListItemId()),
                                productCatalog.getId(),
                                productCatalog.getProductInformation(),
                                productCatalog.getPrice(),
                                new Quantity(command.item().getQuantity()),
                                productCatalog.getImageUrl()
                        )
                );
                shoppingListRepository.save(shoppingList);
            }
        }
        if (command.name() != null) shoppingList.renameList(command.name());
        shoppingListRepository.save(shoppingList);
        return Optional.of(shoppingList);
    }

}
