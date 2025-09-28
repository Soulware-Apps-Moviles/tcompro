package com.soulware.tcompro.iam.interfaces.rest.controllers;

import com.soulware.tcompro.iam.domain.services.ProfileCommandService;
import com.soulware.tcompro.iam.interfaces.rest.assemblers.ProfileResourceFromEntityAssembler;
import com.soulware.tcompro.iam.interfaces.rest.assemblers.SignUpCommandFromResourceAssembler;
import com.soulware.tcompro.iam.interfaces.rest.resources.ProfileResource;
import com.soulware.tcompro.iam.interfaces.rest.resources.SignUpResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/profile/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profile", description = "Profile endpoints")
public class ProfileController {
    private final ProfileCommandService profileCommandService;

    public ProfileController(ProfileCommandService profileCommandService) {
        this.profileCommandService = profileCommandService;
    }

    @PostMapping
    public ResponseEntity<ProfileResource> signUp(@RequestBody SignUpResource signUpResource){
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
        var profile = profileCommandService.handle(signUpCommand);
        if (profile.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var resource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }
}
