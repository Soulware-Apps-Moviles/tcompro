package com.soulware.tcompro.shop.interfaces.rest.controllers;

import com.soulware.tcompro.shop.domain.model.aggregates.Shopkeeper;
import com.soulware.tcompro.shop.domain.model.commands.FireShopkeeperCommand;
import com.soulware.tcompro.shop.domain.model.commands.HireShopkeeperCommand;
import com.soulware.tcompro.shop.domain.model.queries.GetAllShopkeepersByShopIdQuery;
import com.soulware.tcompro.shop.domain.model.queries.GetShopkeeperByEmailAddressQuery;
import com.soulware.tcompro.shop.domain.services.ShopkeeperCommandService;
import com.soulware.tcompro.shop.domain.services.ShopkeeperQueryService;
import com.soulware.tcompro.shop.interfaces.rest.assemblers.ShopkeeperResourceFromEntityAssembler;
import com.soulware.tcompro.shop.interfaces.rest.resources.HireShopkeeperResource;
import com.soulware.tcompro.shop.interfaces.rest.resources.ShopkeeperResource;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/shopkeepers/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Shopkeepers", description = "Endpoints for shopkeepers")
public class ShopkeeperController {
    private final ShopkeeperCommandService shopkeeperCommandService;
    private final ShopkeeperQueryService shopkeeperQueryService;

    ShopkeeperController(ShopkeeperCommandService shopkeeperCommandService, ShopkeeperQueryService shopkeeperQueryService) {
        this.shopkeeperCommandService = shopkeeperCommandService;
        this.shopkeeperQueryService = shopkeeperQueryService;
    }

    @PatchMapping("/hire")
    @Operation(summary = "Hire a shopkeeper by his email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rehire shopkeeper successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Shopkeeper not found")
    })
    public ResponseEntity<ShopkeeperResource> rehireShopkeeper(@RequestBody HireShopkeeperResource resource) {
        Optional<Shopkeeper> shopkeeper = shopkeeperCommandService
                .handle(new HireShopkeeperCommand(resource.shopId(),  resource.email()));
        return shopkeeper
                .map(source -> new ResponseEntity<>(ShopkeeperResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/fire/{id}")
    @Operation(summary = "Fire a shopkeeper by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fire shopkeeper successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Shopkeeper not found")
    })
    public ResponseEntity<ShopkeeperResource> fireShopkeeper(@PathVariable Long id) {
        Optional<Shopkeeper> shopkeeper = shopkeeperCommandService
                .handle(new FireShopkeeperCommand(id));
        return shopkeeper
                .map(source -> new ResponseEntity<>(ShopkeeperResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    @Operation(summary = "Get shopkeeper by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shopkeeper found")
    })
    public ResponseEntity<ShopkeeperResource> getShopkeeperByEmailAddress(@RequestParam String email){
        var query = new GetShopkeeperByEmailAddressQuery(email);
        Optional<Shopkeeper> shopkeeper = shopkeeperQueryService
                .handle(query);
        return shopkeeper
                .map(source -> new ResponseEntity<>(ShopkeeperResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-shop/{id}")
    @Operation(summary = "Get shopkeeper by shop id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shopkeeper found")
    })
    public ResponseEntity<List<ShopkeeperResource>> getAllShopkeepersByShopId(@PathVariable Long id) {
        var query = new GetAllShopkeepersByShopIdQuery(id);
        List<Shopkeeper> shopkeepers = shopkeeperQueryService.handle(query);

        List<ShopkeeperResource> resources = shopkeepers.stream()
                .map(ShopkeeperResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

}
