package com.soulware.tcompro.shop.interfaces.rest.resources;

import java.util.UUID;

public record ShopkeeperResource(
        Long id,
        Long shopId,
        UUID authId,
        String firstName,
        String lastName,
        String email,
        String phone,
        Boolean isHired
) {
}
