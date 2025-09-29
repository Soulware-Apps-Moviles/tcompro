package com.soulware.tcompro.shop.domain.model.queries;

import java.util.List;

public record GetShopsByProductsIdQuery(List<Long> productIds) {
}
