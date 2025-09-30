package com.soulware.tcompro.shopping.domain.model.commands;

import org.springframework.data.util.Pair;

import java.util.List;


public record AddShoppingListCommand(Long customerId, String name, List<Pair<Long, Integer>> items) {
}
