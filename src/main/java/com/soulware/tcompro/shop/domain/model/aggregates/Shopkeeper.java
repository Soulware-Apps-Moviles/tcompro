package com.soulware.tcompro.shop.domain.model.aggregates;

import com.soulware.tcompro.iam.domain.model.aggregates.Profile;
import com.soulware.tcompro.iam.domain.model.valueobjects.AuthId;
import com.soulware.tcompro.iam.domain.model.valueobjects.ProfileId;
import com.soulware.tcompro.shared.domain.model.valueobjects.EmailAddress;
import com.soulware.tcompro.shared.domain.model.valueobjects.PersonName;
import com.soulware.tcompro.shared.domain.model.valueobjects.PhoneNumber;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.shop.domain.model.valueobjects.ShopkeeperId;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shopkeepers")
@Setter
@Getter
public class Shopkeeper extends Profile {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private ShopkeeperId id;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "shop_id", nullable = false)
    )
    private ShopId  shopId;

    private boolean isHired;

    protected Shopkeeper() {
        super();
    }

    public Shopkeeper(ShopkeeperId id, AuthId authId, PersonName personName, EmailAddress email, @Nullable PhoneNumber phone, ShopId shopId) {
        super(authId, personName, email, phone);
        this.shopId = shopId;
        this.isHired = true;
        this.id = id;
    }

    public void fire(){
        this.isHired = false;
    }

    public void hire(){
        this.isHired = true;
    }
}
