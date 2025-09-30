package com.soulware.tcompro.order.interfaces.rest.resources;

public record CreateOrderlineResource(Long productCatalogId,
                                      Integer quantity) {
}
