package com.soulware.tcompro.shop.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Coordinates {

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    protected Coordinates() {}

    public Coordinates(double latitude, double longitude) {
        if(latitude < -90 || latitude > 90)
            throw new IllegalArgumentException("Latitude out of range");

        if(longitude < -180 || longitude > 180)
            throw new IllegalArgumentException("Longitude out of range");

        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double latitude() { return latitude; }
    public double longitude() { return longitude; }
}
