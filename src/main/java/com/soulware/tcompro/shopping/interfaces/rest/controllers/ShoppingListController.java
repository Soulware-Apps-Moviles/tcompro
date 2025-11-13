package com.soulware.tcompro.shopping.interfaces.rest.controllers;

import com.soulware.tcompro.shopping.domain.model.aggregates.ShoppingList;
import com.soulware.tcompro.shopping.domain.model.queries.GetAllShoppingListsQuery;
import com.soulware.tcompro.shopping.domain.services.ShoppingListCommandService;
import com.soulware.tcompro.shopping.domain.services.ShoppingListQueryService;
import com.soulware.tcompro.shopping.interfaces.rest.assemblers.CreateShoppingListCommandFromResourceAssembler;
import com.soulware.tcompro.shopping.interfaces.rest.assemblers.DeleteShoppingListCommandFromResourceAssembler;
import com.soulware.tcompro.shopping.interfaces.rest.assemblers.ShoppingListResourceFromEntityAssembler;
import com.soulware.tcompro.shopping.interfaces.rest.assemblers.UpdateShoppingListCommandFromResourceAssembler;
import com.soulware.tcompro.shopping.interfaces.rest.resources.CreateShoppingListResource;
import com.soulware.tcompro.shopping.interfaces.rest.resources.ShoppingListResource;
import com.soulware.tcompro.shopping.interfaces.rest.resources.UpdateShoppingListResource;
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
@RequestMapping(value = "/shopping-list/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Shopping List" , description = "Endpoints for Shopping list")
public class ShoppingListController {
    private final ShoppingListCommandService  shoppingListCommandService;
    private final ShoppingListQueryService shoppingListQueryService;

    ShoppingListController(
            ShoppingListCommandService shoppingListCommandService,
            ShoppingListQueryService shoppingListQueryService
    ){
        this.shoppingListCommandService = shoppingListCommandService;
        this.shoppingListQueryService = shoppingListQueryService;
    }

    @PostMapping
    @Operation(summary = "Create Shopping list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create Shopping list successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<ShoppingListResource> createShoppingList(@RequestBody CreateShoppingListResource resource){
        Optional<ShoppingList> shoppingList = shoppingListCommandService
                .handle(CreateShoppingListCommandFromResourceAssembler.toCommandFromResource(resource));
        return shoppingList
                .map(source -> new ResponseEntity<>(ShoppingListResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Shopping list by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete Shopping list successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Shopping list not found")
    })
    public ResponseEntity<Long> deleteShoppingList(@PathVariable Long id){
        Optional<Long> shoppingListId = shoppingListCommandService
                .handle(DeleteShoppingListCommandFromResourceAssembler.toCommandFromResource(id));
        return shoppingListId
                .map(source -> new ResponseEntity<>(source, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update Shopping list", description = "Update name or items of a shopping list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Update Shopping list successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Shopping list not found")
    })
    public ResponseEntity<ShoppingListResource> updateShoppingList(@PathVariable Long id, @RequestBody UpdateShoppingListResource resource){
        Optional<ShoppingList> shoppingList = shoppingListCommandService
                .handle(UpdateShoppingListCommandFromResourceAssembler.toCommandFromResource(id, resource));
        return shoppingList
                .map(source -> new ResponseEntity<>(ShoppingListResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("")
    @Operation(summary = "Get shopping lists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shopping Lists found")
    })
    public ResponseEntity<List<ShoppingListResource>> getAllShoppingListsByCustomer(
            @RequestParam Long customerId,
            @RequestParam(required = false) String name
    ){
        var query = new GetAllShoppingListsQuery(customerId, name);
        List<ShoppingList> shoppingLists = shoppingListQueryService
                .handle(query);
        List<ShoppingListResource> resources = shoppingLists.stream()
                .map(ShoppingListResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }
}
