package com.soulware.tcompro.shop.interfaces.rest.resources;

import java.util.List;

public record ProductListResource(
        List<Long> ids,
        double latitude,
        double longitude
) {
}
