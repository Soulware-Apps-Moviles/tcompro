package com.soulware.tcompro.iam.interfaces.rest.assemblers;

import com.soulware.tcompro.iam.domain.model.commands.SignUpCommand;
import com.soulware.tcompro.iam.domain.model.entities.Role;
import com.soulware.tcompro.iam.domain.model.valueobjects.AuthId;
import com.soulware.tcompro.iam.domain.model.valueobjects.Roles;
import com.soulware.tcompro.iam.interfaces.rest.resources.SignUpResource;
import com.soulware.tcompro.shared.domain.model.valueobjects.EmailAddress;
import com.soulware.tcompro.shared.domain.model.valueobjects.PersonName;
import com.soulware.tcompro.shared.domain.model.valueobjects.PhoneNumber;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource){
        new PhoneNumber(resource.phone());
        return new SignUpCommand(
                new AuthId(resource.authId()),
                new PersonName(resource.firstName(), resource.lastName()),
                new EmailAddress(resource.email()),
                new PhoneNumber(resource.phone()),
                new Role(Roles.valueOf(resource.role()))
        );
    }
}
