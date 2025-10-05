package com.soulware.tcompro.shop.interfaces.rest.controllers;

import com.soulware.tcompro.shop.domain.model.aggregates.Shop;
import com.soulware.tcompro.shop.domain.model.queries.GetShopByOwnerIdQuery;
import com.soulware.tcompro.shop.domain.model.queries.GetShopIdByIdQuery;
import com.soulware.tcompro.shop.domain.model.queries.GetShopsByProductsIdQuery;
import com.soulware.tcompro.shop.domain.services.ShopCommandService;
import com.soulware.tcompro.shop.domain.services.ShopQueryService;
import com.soulware.tcompro.shop.interfaces.rest.assemblers.CreateShopCommandFromResourceAssembler;
import com.soulware.tcompro.shop.interfaces.rest.assemblers.ShopResourceFromEntityAssembler;
import com.soulware.tcompro.shop.interfaces.rest.resources.CreateShopResource;
import com.soulware.tcompro.shop.interfaces.rest.resources.ProductListResource;
import com.soulware.tcompro.shop.interfaces.rest.resources.ShopResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @Operation(summary = "Create shop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create shop successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<ShopResource> createShop(@RequestBody CreateShopResource resource){
        Optional<Shop> shop = shopCommandService
                .handle(CreateShopCommandFromResourceAssembler.toCommandFromResource(resource));
        return shop
                .map(source -> new ResponseEntity<>(ShopResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get shop by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shop found")
    })
    public ResponseEntity<ShopResource> getShopById(@PathVariable Long id){
        var query = new GetShopIdByIdQuery(id);
        Optional<Shop> shop =  shopQueryService.handle(query);
        return shop
                .map(source -> new ResponseEntity<>(ShopResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-owner/{id}")
    @Operation(summary = "Get shop by owner id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shop found")
    })
    public ResponseEntity<ShopResource> getShopByOwnerId(@PathVariable Long id){
        var query = new GetShopByOwnerIdQuery(id);
        Optional<Shop> shop =  shopQueryService.handle(query);
        return shop
                .map(source -> new ResponseEntity<>(ShopResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/by-products")
    @Operation(summary = "Get shops by products", description = "Get a list of shops based on a list of products in their inventory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shop found")
    })
    public ResponseEntity<List<ShopResource>> getShopsByProducts(@RequestBody ProductListResource resource){
        var query  = new GetShopsByProductsIdQuery(resource.ids());
        List<Shop> shops = shopQueryService
                .handle(query);
        List<ShopResource> resources = shops.stream()
                .map(ShopResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
