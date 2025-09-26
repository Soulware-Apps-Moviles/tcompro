package com.soulware.tcompro.shop.domain.services;

import com.soulware.tcompro.iam.domain.model.valueobjects.ProfileId;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.shop.domain.model.aggregates.Shop;
import com.soulware.tcompro.shop.domain.model.entities.Policy;
import com.soulware.tcompro.shop.domain.model.valueobjects.OwnerId;
import com.soulware.tcompro.shop.domain.model.valueobjects.PolicyId;
import com.soulware.tcompro.shop.domain.model.valueobjects.ShopkeeperId;

public interface ShopManagementService {
    Shop createShop(OwnerId ownerId, Policy policy);
    void hireShopkeeper(ProfileId profileId, ShopId shopId);
    void fireShopkeeper(ShopkeeperId shopkeeperId);
}
