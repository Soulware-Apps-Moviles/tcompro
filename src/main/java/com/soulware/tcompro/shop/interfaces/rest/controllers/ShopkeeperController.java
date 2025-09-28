package com.soulware.tcompro.shop.interfaces.rest.controllers;

import com.soulware.tcompro.shop.domain.model.aggregates.Shopkeeper;
import com.soulware.tcompro.shop.domain.model.commands.FireShopkeeperCommand;
import com.soulware.tcompro.shop.domain.model.commands.HireShopkeeperCommand;
import com.soulware.tcompro.shop.domain.model.queries.GetAllShopkeepersByShopIdQuery;
import com.soulware.tcompro.shop.domain.model.queries.GetShopkeeperByEmailAddressQuery;
import com.soulware.tcompro.shop.domain.services.ShopkeeperCommandService;
import com.soulware.tcompro.shop.domain.services.ShopkeeperQueryService;
import com.soulware.tcompro.shop.interfaces.rest.assemblers.ShopkeeperResourceFromEntityAssembler;
import com.soulware.tcompro.shop.interfaces.rest.resources.ShopkeeperResource;
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

    @PatchMapping("/rehire/{id}")
    public ResponseEntity<ShopkeeperResource> rehireShopkeeper(@PathVariable Long id) {
        Optional<Shopkeeper> shopkeeper = shopkeeperCommandService
                .handle(new HireShopkeeperCommand(id));
        return shopkeeper
                .map(source -> new ResponseEntity<>(ShopkeeperResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/fire/{id}")
    public ResponseEntity<ShopkeeperResource> fireShopkeeper(@PathVariable Long id) {
        Optional<Shopkeeper> shopkeeper = shopkeeperCommandService
                .handle(new FireShopkeeperCommand(id));
        return shopkeeper
                .map(source -> new ResponseEntity<>(ShopkeeperResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<ShopkeeperResource> getShopkeeperByEmailAddress(@RequestParam String email){
        var query = new GetShopkeeperByEmailAddressQuery(email);
        Optional<Shopkeeper> shopkeeper = shopkeeperQueryService
                .handle(query);
        return shopkeeper
                .map(source -> new ResponseEntity<>(ShopkeeperResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-shop/{id}")
    public ResponseEntity<List<ShopkeeperResource>> getAllShopkeepersByShopId(@PathVariable Long id) {
        var query = new GetAllShopkeepersByShopIdQuery(id);
        List<Shopkeeper> shopkeepers = shopkeeperQueryService.handle(query);

        List<ShopkeeperResource> resources = shopkeepers.stream()
                .map(ShopkeeperResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

}
