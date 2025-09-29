package com.soulware.tcompro.catalog.interfaces.rest.controllers;

import com.soulware.tcompro.catalog.domain.model.aggregates.ProductCatalog;
import com.soulware.tcompro.catalog.domain.model.queries.GetAllCatalogProductsByCategoryNameQuery;
import com.soulware.tcompro.catalog.domain.model.queries.GetAllCatalogProductsQuery;
import com.soulware.tcompro.catalog.domain.model.queries.GetCatalogProductByIdQuery;
import com.soulware.tcompro.catalog.domain.services.ProductCatalogQueryService;
import com.soulware.tcompro.catalog.interfaces.rest.assemblers.ProductCatalogResourceFromEntityAssembler;
import com.soulware.tcompro.catalog.interfaces.rest.resources.ProductCatalogResource;
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
    public ResponseEntity<ProductCatalogResource> getCatalogProductsById(@PathVariable Long id) {
        var query = new GetCatalogProductByIdQuery(id);
        Optional<ProductCatalog> productCatalog = productCatalogQueryService
                .handle(query);
        return productCatalog
                .map(source -> new ResponseEntity<>(ProductCatalogResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
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
