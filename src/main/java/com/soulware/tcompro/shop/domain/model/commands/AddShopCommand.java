package com.soulware.tcompro.shop.domain.model.commands;

import com.soulware.tcompro.shop.domain.model.entities.Policy;

public record AddShopCommand(Long OwnerId, Policy policy) {
}
