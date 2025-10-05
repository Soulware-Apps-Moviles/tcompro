package com.soulware.tcompro.shop.interfaces.rest.resources;

import java.util.UUID;

public record OwnerResource(Long id,
                            UUID authId,
                            String firstName,
                            String lastName,
                            String email,
                            String phone) {
}
