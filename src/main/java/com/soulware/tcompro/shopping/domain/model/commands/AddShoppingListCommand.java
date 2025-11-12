package com.soulware.tcompro.shopping.domain.model.commands;

import com.soulware.tcompro.shared.domain.model.valueobjects.PairResource;
import jakarta.validation.constraints.Null;

import java.util.List;


public record AddShoppingListCommand(Long customerId, String name, @Null List<PairResource<Long, Integer>> items) {
}
