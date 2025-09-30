package com.soulware.tcompro.catalog.interfaces.rest.controllers;

import com.soulware.tcompro.catalog.domain.model.aggregates.ProductCatalog;
import com.soulware.tcompro.catalog.domain.model.queries.GetAllCatalogProductsByCategoryNameQuery;
import com.soulware.tcompro.catalog.domain.model.queries.GetAllCatalogProductsQuery;
import com.soulware.tcompro.catalog.domain.model.queries.GetCatalogProductByIdQuery;
import com.soulware.tcompro.catalog.domain.services.ProductCatalogQueryService;
import com.soulware.tcompro.catalog.interfaces.rest.assemblers.ProductCatalogResourceFromEntityAssembler;
import com.soulware.tcompro.catalog.interfaces.rest.resources.ProductCatalogResource;
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
@RequestMapping(value = "/catalog-products/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Catalog Products", description = "Endpoints for catalog products")
public class ProductCatalogController {
    private final ProductCatalogQueryService  productCatalogQueryService;

    ProductCatalogController(final ProductCatalogQueryService productCatalogQueryService) {
        this.productCatalogQueryService = productCatalogQueryService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Catalog Product by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catalog product found")
    })
    public ResponseEntity<ProductCatalogResource> getCatalogProductsById(@PathVariable Long id) {
        var query = new GetCatalogProductByIdQuery(id);
        Optional<ProductCatalog> productCatalog = productCatalogQueryService
                .handle(query);
        return productCatalog
                .map(source -> new ResponseEntity<>(ProductCatalogResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    @Operation(summary = "Get all catalog products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catalog products found")
    })
    public ResponseEntity<List<ProductCatalogResource>> getAllCatalogProducts(){
        var query = new GetAllCatalogProductsQuery();
        List<ProductCatalog> productCatalogs = productCatalogQueryService
                .handle(query);
        List<ProductCatalogResource> resources = productCatalogs.stream()
                .map(ProductCatalogResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }


    @GetMapping("/by-category")
    @Operation(summary = "Get all catalog products by category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catalog products found")
    })
    public ResponseEntity<List<ProductCatalogResource>> getAllCatalogProductsByCategory(@RequestParam String category){
        var query = new GetAllCatalogProductsByCategoryNameQuery(category);
        List<ProductCatalog> productCatalogs = productCatalogQueryService
                .handle(query);
        List<ProductCatalogResource> resources = productCatalogs.stream()
                .map(ProductCatalogResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
