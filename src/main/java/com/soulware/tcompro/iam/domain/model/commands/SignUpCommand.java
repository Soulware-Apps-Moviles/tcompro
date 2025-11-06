package com.soulware.tcompro.iam.domain.model.commands;

import com.soulware.tcompro.iam.domain.model.entities.Role;
import com.soulware.tcompro.iam.domain.model.valueobjects.AuthId;
import com.soulware.tcompro.shared.domain.model.valueobjects.EmailAddress;
import com.soulware.tcompro.shared.domain.model.valueobjects.PersonName;
import com.soulware.tcompro.shared.domain.model.valueobjects.PhoneNumber;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import jakarta.annotation.Nullable;

public record SignUpCommand(AuthId authId,
                            PersonName personName,
                            EmailAddress email,
                            @Nullable PhoneNumber phoneNumber,
                            Role role) {
}
