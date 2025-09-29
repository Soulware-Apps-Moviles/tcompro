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
    public ResponseEntity<ProductResource> createProduct(CreateProductResource resource) {
        Optional<Product> product = productCommandService
                .handle(CreateProductCommandFromResourceAssembler.toCommandFromResource(resource));
        return product
                .map(source -> new ResponseEntity<>(ProductResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PatchMapping
    public ResponseEntity<ProductResource> updateProduct(UpdateProductResource resource) {
        Optional<Product> product = productCommandService
                .handle(UpdateProductCommandFromResourceAssembler.toCommandFromResource(resource));
        return product
                .map(source -> new ResponseEntity<>(ProductResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/by-shop/{id}")
    public ResponseEntity<List<ProductResource>> getProductsBy(@PathVariable Long id, @RequestParam(required = false) Boolean isAvailable, @RequestParam(required = false) String category) {
        var query = new GetProductsByShopIdQuery(id, isAvailable, category);
        List<Product> products = productQueryService
                .handle(query);
        List<ProductResource> resources = products.stream()
                .map(ProductResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
