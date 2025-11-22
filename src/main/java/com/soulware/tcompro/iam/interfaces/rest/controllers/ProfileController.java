package com.soulware.tcompro.iam.interfaces.rest.controllers;

import com.soulware.tcompro.iam.domain.model.entities.Role;
import com.soulware.tcompro.iam.domain.model.queries.GetByAuthIdAndRoleQuery;
import com.soulware.tcompro.iam.domain.model.valueobjects.Roles;
import com.soulware.tcompro.iam.domain.services.ProfileCommandService;
import com.soulware.tcompro.iam.domain.services.ProfileQueryService;
import com.soulware.tcompro.iam.interfaces.rest.assemblers.ProfileResourceFromEntityAssembler;
import com.soulware.tcompro.iam.interfaces.rest.assemblers.SignUpCommandFromResourceAssembler;
import com.soulware.tcompro.iam.interfaces.rest.resources.ProfileResource;
import com.soulware.tcompro.iam.interfaces.rest.resources.SignUpResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/profile/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profile", description = "Profile endpoints")
public class ProfileController {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfileController(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    @PostMapping
    @Operation(summary = "Sign up", description = "Sign user information, It has to be called after the call to Supabase auth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sign up successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<ProfileResource> signUp(@RequestBody SignUpResource signUpResource){
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
        var profile = profileCommandService.handle(signUpCommand);
        if (profile.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var resource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @GetMapping("by-auth-id/{authId}")
    @Operation(summary = "Get Profile", description = "Get a user profile by Auth ID and Role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile found"),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    public ResponseEntity<ProfileResource> getProfile(@PathVariable UUID authId, @RequestParam Roles role) {
        var query = new GetByAuthIdAndRoleQuery(authId, role);
        var profile = profileQueryService.handle(query);

        return profile
                .map(entity -> ResponseEntity.ok(
                        ProfileResourceFromEntityAssembler.toResourceFromEntity(entity)
                ))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
