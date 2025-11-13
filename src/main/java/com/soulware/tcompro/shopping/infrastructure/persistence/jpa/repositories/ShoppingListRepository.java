package com.soulware.tcompro.shopping.infrastructure.persistence.jpa.repositories;

import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import com.soulware.tcompro.shopping.domain.model.aggregates.ShoppingList;
import com.soulware.tcompro.shopping.domain.model.valueobjects.ShoppingListId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
    Optional<ShoppingList> findById(ShoppingListId id);
    List<ShoppingList> findByCustomerId(CustomerId customerId);


    @Query(
            value = """
        SELECT * FROM shopping_lists s
        WHERE s.customer_id = :customerId
          AND (:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%')))
        """,
            nativeQuery = true
    )
    List<ShoppingList> findAllByFilters(
            @Param("customerId") Long customerId,
            @Param("name") String name
    );


}
