package com.soulware.tcompro.shared.domain.model.entities;

import com.soulware.tcompro.shared.domain.model.valueobjects.Categories;
import com.soulware.tcompro.shared.domain.model.valueobjects.CategoryId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @EmbeddedId
    private CategoryId id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20,  nullable = false)
    private Categories name;
}
