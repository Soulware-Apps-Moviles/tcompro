package com.soulware.tcompro.shop.interfaces.rest.controllers;

import com.soulware.tcompro.shop.domain.model.aggregates.Shop;
import com.soulware.tcompro.shop.domain.model.queries.GetShopByOwnerIdQuery;
import com.soulware.tcompro.shop.domain.model.queries.GetShopIdByIdQuery;
import com.soulware.tcompro.shop.domain.services.ShopCommandService;
import com.soulware.tcompro.shop.domain.services.ShopQueryService;
import com.soulware.tcompro.shop.interfaces.rest.assemblers.CreateShopCommandFromResourceAssembler;
import com.soulware.tcompro.shop.interfaces.rest.assemblers.ShopResourceFromEntityAssembler;
import com.soulware.tcompro.shop.interfaces.rest.resources.CreateShopResource;
import com.soulware.tcompro.shop.interfaces.rest.resources.ShopResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/shops/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Shops", description = "Endpoints for shops")
public class ShopController {
    private final ShopCommandService  shopCommandService;
    private final ShopQueryService  shopQueryService;

    ShopController(ShopCommandService shopCommandService, ShopQueryService shopQueryService) {
        this.shopCommandService = shopCommandService;
        this.shopQueryService = shopQueryService;
    }

    @PostMapping
    public ResponseEntity<ShopResource> createShop(@RequestBody CreateShopResource resource){
        Optional<Shop> shop = shopCommandService
                .handle(CreateShopCommandFromResourceAssembler.toCommandFromResource(resource));
        return shop
                .map(source -> new ResponseEntity<>(ShopResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopResource> getShopById(@PathVariable Long id){
        var query = new GetShopIdByIdQuery(id);
        Optional<Shop> shop =  shopQueryService.handle(query);
        return shop
                .map(source -> new ResponseEntity<>(ShopResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-owner/{id}")
    public ResponseEntity<ShopResource> getShopByOwnerId(@PathVariable Long id){
        var query = new GetShopByOwnerIdQuery(id);
        Optional<Shop> shop =  shopQueryService.handle(query);
        return shop
                .map(source -> new ResponseEntity<>(ShopResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
