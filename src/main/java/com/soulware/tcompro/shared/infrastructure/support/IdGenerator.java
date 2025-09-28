package com.soulware.tcompro.shared.infrastructure.support;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class IdGenerator {

    @PersistenceContext
    private EntityManager entityManager;

    private Long next(String sequenceName) {
        return ((Number) entityManager
                .createNativeQuery("SELECT nextval('" + sequenceName + "')")
                .getSingleResult()).longValue();
    }

    public Long nextDebtId() { return next("debt_id_seq"); }
    public Long nextPaymentId() { return next("payment_id_seq"); }
    public Long nextProductId() { return next("product_id_seq"); }
    public Long nextOrderlineId() { return next("orderline_id_seq"); }
    public Long nextCatalogProductId() { return next("catalog_product_id_seq"); }
    public Long nextOrderId() { return next("order_id_seq"); }
    public Long nextShopId() { return next("shop_id_seq"); }
    public Long nextCustomerId() { return next("customer_id_seq"); }
    public Long nextOwnerId() { return next("owner_id_seq"); }
    public Long nextPolicyId() { return next("policy_id_seq"); }
    public Long nextShopkeeperId() { return next("shopkeeper_id_seq"); }
    public Long nextTrustedCustomerId() { return next("trusted_customer_id_seq"); }
    public Long nextFavoriteProductId() { return next("favorite_product_id_seq"); }
    public Long nextShoppingBagItemId() { return next("shopping_bag_item_id_seq"); }
    public Long nextShoppingListId() { return next("shopping_list_id_seq"); }
    public Long nextShoppingListItemId() { return next("shopping_list_item_id_seq"); }
}
