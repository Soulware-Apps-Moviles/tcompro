package com.soulware.tcompro.shop.infrastructure.persistence.jpa.repositories;

import com.soulware.tcompro.iam.domain.model.valueobjects.AuthId;
import com.soulware.tcompro.shared.domain.model.valueobjects.EmailAddress;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.shop.domain.model.aggregates.Shopkeeper;
import com.soulware.tcompro.shop.domain.model.valueobjects.ShopkeeperId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopkeeperRepository extends JpaRepository<Shopkeeper, Long> {
    Optional<Shopkeeper> findByEmail(EmailAddress email);

    Optional<Shopkeeper> findByAuthId(AuthId id);

    Optional<Shopkeeper> findById(ShopkeeperId id);

    List<Shopkeeper> findAllByShopId(ShopId shopId);

    Optional<Shopkeeper> findByEmailAndShopId(EmailAddress email, ShopId shopId);
}
