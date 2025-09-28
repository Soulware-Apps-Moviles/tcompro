package com.soulware.tcompro.iam.domain.model.entities;

import com.soulware.tcompro.iam.domain.model.valueobjects.RoleId;
import com.soulware.tcompro.iam.domain.model.valueobjects.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @EmbeddedId
    private RoleId id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Roles name;

    public Role(Roles name){
        this.name = name;
    }
}
