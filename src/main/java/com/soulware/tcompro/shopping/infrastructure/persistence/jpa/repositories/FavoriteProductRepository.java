package com.soulware.tcompro.shopping.infrastructure.persistence.jpa.repositories;

import com.soulware.tcompro.inventory.domain.model.valueobjects.ProductId;
import com.soulware.tcompro.shared.domain.model.valueobjects.CatalogProductId;
import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import com.soulware.tcompro.shopping.domain.model.aggregates.FavoriteProduct;
import com.soulware.tcompro.shopping.domain.model.valueobjects.FavoriteProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct,Long> {
    Boolean existsByCustomerIdAndCatalogProductId(CustomerId customerId, CatalogProductId catalogProductId);
    Optional<FavoriteProduct> findById(FavoriteProductId id);
    List<FavoriteProduct> findByCustomerId(CustomerId customerId);
    Optional<FavoriteProduct> findByCustomerIdAndCatalogProductId(CustomerId customerId, CatalogProductId catalogProductId);
}
