package com.soulware.tcompro.catalog.interfaces.rest.controllers;

import com.soulware.tcompro.catalog.domain.model.aggregates.ProductCatalog;
import com.soulware.tcompro.catalog.domain.model.queries.GetAllProductCatalogQuery;
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

    @GetMapping()
    @Operation(summary = "Get all catalog products by filters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catalog products found")
    })
    public ResponseEntity<List<ProductCatalogResource>> getAllProductCatalog(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String name) {
        var query = new GetAllProductCatalogQuery(category, id,  name);
        List<ProductCatalog> productCatalogs = productCatalogQueryService
                .handle(query);
        List<ProductCatalogResource> resources = productCatalogs.stream()
                .map(ProductCatalogResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
