package com.soulware.tcompro.shop.interfaces.rest.controllers;

import com.soulware.tcompro.shop.domain.model.aggregates.TrustedCustomer;
import com.soulware.tcompro.shop.domain.model.queries.GetAllTrustedCustomersByShopIdQuery;
import com.soulware.tcompro.shop.domain.model.queries.GetTrustedCustomerByCustomerIdQuery;
import com.soulware.tcompro.shop.domain.services.TrustedCustomerCommandService;
import com.soulware.tcompro.shop.domain.services.TrustedCustomerQueryService;
import com.soulware.tcompro.shop.interfaces.rest.assemblers.CreateTrustedCustomerCommandFromResourceAssembler;
import com.soulware.tcompro.shop.interfaces.rest.assemblers.TrustedCustomerResourceFromEntityAssembler;
import com.soulware.tcompro.shop.interfaces.rest.assemblers.UpdateCreditLimitCommandFromResourceAssembler;
import com.soulware.tcompro.shop.interfaces.rest.resources.CreateTrustedCustomerResource;
import com.soulware.tcompro.shop.interfaces.rest.resources.TrustedCustomerResource;
import com.soulware.tcompro.shop.interfaces.rest.resources.UpdateCreditLimitResource;
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
@RequestMapping(value = "/trusted-customers/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Trusted Customers", description = "Endpoints for Trusted Customers")
public class TrustedCustomerController {
    private final TrustedCustomerCommandService trustedCustomerCommandService;
    private final TrustedCustomerQueryService trustedCustomerQueryService;

    TrustedCustomerController(
            TrustedCustomerCommandService trustedCustomerCommandService,
            TrustedCustomerQueryService trustedCustomerQueryService
    ){
        this.trustedCustomerCommandService = trustedCustomerCommandService;
        this.trustedCustomerQueryService = trustedCustomerQueryService;
    }

    @PostMapping
    @Operation(summary = "Create Trusted customer", description = "Links a customer with a shop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create Trusted customer successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<TrustedCustomerResource> createTrustedCustomer(CreateTrustedCustomerResource resource){
        Optional<TrustedCustomer> trustedCustomer = trustedCustomerCommandService
                .handle(CreateTrustedCustomerCommandFromResourceAssembler.toCommandFromResource(resource));
        return trustedCustomer
                .map(source -> new ResponseEntity<>(TrustedCustomerResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PatchMapping
    @Operation(summary = "Update credit limit of a trusted customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update credit limit successfully")
    })
    public ResponseEntity<TrustedCustomerResource> updateCreditLimit(UpdateCreditLimitResource resource){
        Optional<TrustedCustomer> trustedCustomer = trustedCustomerCommandService
                .handle(UpdateCreditLimitCommandFromResourceAssembler.toCommandFromResource(resource));
        return trustedCustomer
                .map(source -> new ResponseEntity<>(TrustedCustomerResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/by-customer/{id}")
    @Operation(summary = "Get trusted customers by customer id", description = "Get a list of trusted customers linked to a customer id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trusted customers found")
    })
    public ResponseEntity<List<TrustedCustomerResource>> getTrustedCustomersByCustomerId(@PathVariable Long id){
        var query = new GetTrustedCustomerByCustomerIdQuery(id);
        List<TrustedCustomer> trustedCustomer = trustedCustomerQueryService
                .handle(query);
        List<TrustedCustomerResource> resources = trustedCustomer.stream()
                .map(TrustedCustomerResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/by-shop/{id}")
    @Operation(summary = "Get trusted customers by shop id", description = "Get a list of trusted customers linked to a shop id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trusted customers found")
    })
    public ResponseEntity<List<TrustedCustomerResource>> getTrustedCustomersByShopId(@PathVariable Long id){
        var query =  new GetAllTrustedCustomersByShopIdQuery( id);
        List<TrustedCustomer> trustedCustomer = trustedCustomerQueryService
                .handle(query);
        List<TrustedCustomerResource> resources = trustedCustomer.stream()
                .map(TrustedCustomerResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
