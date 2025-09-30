package com.soulware.tcompro.shopping.interfaces.rest.assemblers;

import com.soulware.tcompro.shopping.domain.model.commands.RemoveFavoriteProductCommand;
import com.soulware.tcompro.shopping.interfaces.rest.resources.DeleteFavoriteProductResource;
import com.soulware.tcompro.shopping.interfaces.rest.resources.FavoriteProductResource;

public class DeleteFavoriteProductCommandFromResourceAssembler {
    public static RemoveFavoriteProductCommand toCommandFromResource(DeleteFavoriteProductResource resource){
        return new RemoveFavoriteProductCommand(
                resource.favoriteProductId()
        );
    }
}
