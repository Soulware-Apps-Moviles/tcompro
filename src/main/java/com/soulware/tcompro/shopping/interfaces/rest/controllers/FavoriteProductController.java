package com.soulware.tcompro.shopping.interfaces.rest.controllers;

import com.soulware.tcompro.shopping.domain.model.aggregates.FavoriteProduct;
import com.soulware.tcompro.shopping.domain.model.queries.GetAllFavoriteProductsByCustomerIdQuery;
import com.soulware.tcompro.shopping.domain.services.FavoriteProductCommandService;
import com.soulware.tcompro.shopping.domain.services.FavoriteProductQueryService;
import com.soulware.tcompro.shopping.interfaces.rest.assemblers.CreateFavoriteProductCommandFromResourceAssembler;
import com.soulware.tcompro.shopping.interfaces.rest.assemblers.DeleteFavoriteProductCommandFromResourceAssembler;
import com.soulware.tcompro.shopping.interfaces.rest.assemblers.FavoriteProductResourceFromEntityAssembler;
import com.soulware.tcompro.shopping.interfaces.rest.resources.CreateFavoriteProductResource;
import com.soulware.tcompro.shopping.interfaces.rest.resources.DeleteFavoriteProductResource;
import com.soulware.tcompro.shopping.interfaces.rest.resources.FavoriteProductResource;
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
@RequestMapping(value = "/favorite-products/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Favorite Products", description = "Endpoints for favorite products")
public class FavoriteProductController {
    private final FavoriteProductQueryService favoriteProductQueryService;
    private final FavoriteProductCommandService favoriteProductCommandService;

    FavoriteProductController(FavoriteProductQueryService favoriteProductQueryService, FavoriteProductCommandService favoriteProductCommandService) {
        this.favoriteProductQueryService = favoriteProductQueryService;
        this.favoriteProductCommandService = favoriteProductCommandService;
    }

    @PostMapping
    @Operation(summary = "Create favorite product", description = "Links a catalog product with a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create Favorite product successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<FavoriteProductResource> createFavoriteProduct(@RequestBody CreateFavoriteProductResource resource){
        Optional<FavoriteProduct> favoriteProduct = favoriteProductCommandService
                .handle(CreateFavoriteProductCommandFromResourceAssembler.toCommandFromResource(resource));
        return favoriteProduct
                .map(source -> new ResponseEntity<>(FavoriteProductResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete Favorite product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete Favorite Product successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Favorite product not found")
    })
    public ResponseEntity<Long> deleteFavoriteProduct(@PathVariable Long id){
        Optional<Long> favoriteProductId = favoriteProductCommandService
                .handle(DeleteFavoriteProductCommandFromResourceAssembler.toCommandFromResource(id));
        return favoriteProductId
                .map(source -> new ResponseEntity<>(source, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/by-customer/{id}")
    @Operation(summary = "Get Favorite products by customer id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite products found")
    })
    public ResponseEntity<List<FavoriteProductResource>> getAllFavoriteProductsByCustomer(@PathVariable Long id){
        var query = new GetAllFavoriteProductsByCustomerIdQuery(id);
        List<FavoriteProduct> favoriteProducts =  favoriteProductQueryService
                .handle(query);
        List<FavoriteProductResource> resources = favoriteProducts.stream()
                .map(FavoriteProductResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }
}
