package com.soulware.tcompro.inventory.interfaces.rest.controllers;

import com.soulware.tcompro.inventory.domain.model.aggregates.Product;
import com.soulware.tcompro.inventory.domain.model.queries.GetProductsByCategoryNameAndShopIdQuery;
import com.soulware.tcompro.inventory.domain.model.queries.GetProductsByShopIdQuery;
import com.soulware.tcompro.inventory.domain.services.ProductCommandService;
import com.soulware.tcompro.inventory.domain.services.ProductQueryService;
import com.soulware.tcompro.inventory.interfaces.rest.assemblers.CreateProductCommandFromResourceAssembler;
import com.soulware.tcompro.inventory.interfaces.rest.assemblers.ProductResourceFromEntityAssembler;
import com.soulware.tcompro.inventory.interfaces.rest.assemblers.UpdateProductCommandFromResourceAssembler;
import com.soulware.tcompro.inventory.interfaces.rest.resources.CreateProductResource;
import com.soulware.tcompro.inventory.interfaces.rest.resources.ProductResource;
import com.soulware.tcompro.inventory.interfaces.rest.resources.UpdateProductResource;
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
@RequestMapping(value = "/products/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Products", description = "Endpoints for products")
public class ProductController {
    private final ProductCommandService  productCommandService;
    private final ProductQueryService  productQueryService;

    ProductController(ProductCommandService productCommandService, ProductQueryService productQueryService) {
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
    }

    @PostMapping
    @Operation(summary = "Create Product in shop inventory", description = "Links a catalog product to a shop's inventory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create product successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<ProductResource> createProduct(CreateProductResource resource) {
        Optional<Product> product = productCommandService
                .handle(CreateProductCommandFromResourceAssembler.toCommandFromResource(resource));
        return product
                .map(source -> new ResponseEntity<>(ProductResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PatchMapping
    @Operation(summary = "Update Product in shop inventory", description = "Update product price or availability")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<ProductResource> updateProduct(UpdateProductResource resource) {
        Optional<Product> product = productCommandService
                .handle(UpdateProductCommandFromResourceAssembler.toCommandFromResource(resource));
        return product
                .map(source -> new ResponseEntity<>(ProductResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/by-shop/{shopId}")
    @Operation(summary = "Get products from a shop with query params", description = "Get products based on shop, availability and/or category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products found")
    })
    public ResponseEntity<List<ProductResource>> getProductsBy(@PathVariable Long shopId, @RequestParam(required = false) Boolean isAvailable, @RequestParam(required = false) String category) {
        var query = new GetProductsByShopIdQuery(shopId, isAvailable, category);
        List<Product> products = productQueryService
                .handle(query);
        List<ProductResource> resources = products.stream()
                .map(ProductResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
