package com.soulware.tcompro.shopping.interfaces.rest.assemblers;

import com.soulware.tcompro.shopping.domain.model.commands.RemoveShoppingListCommand;

public class DeleteShoppingListCommandFromResourceAssembler {
    public static RemoveShoppingListCommand toCommandFromResource(Long id){
        return new RemoveShoppingListCommand(
                id
        );
    }
}
