package me.inc.bookingapp.model.entity.properties;


import me.inc.bookingapp.model.entity.StayListing;
import me.inc.bookingapp.model.entity.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "stay_properties")
public class StayProperties extends BaseEntity {

    @Column(name = "has_kitchen")
    private boolean hasKitchen = false;
    @Column(name = "has_bedroom")
    private boolean hasBedroom = false;
    @Column(name = "has_bathroom")
    private boolean hasBathroom = false;
    @Column(name = "has_tv")
    private boolean hasTV = false;
    @Column(name = "has_wifi")
    private boolean hasWifi = false;
    @Column(name = "has_parking")
    private boolean hasParking = false;
    @Column(name = "has_elevator")
    private boolean hasElevator = false;
    @Column(name = "has_city_view")
    private boolean hasCityView = false;
    @Column(name = "has_air_conditioning")
    private boolean hasAirConditioning = false;
    @Column(name = "has_restaurant")
    private boolean hasRestaurant = false;
    @Column(name = "has_free_breakfast")
    private boolean hasFreeBreakfast = false;
    @Column(name = "is_all_inclusive")
    private boolean isAllInclusive = false;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "listing_id")
    private StayListing listing;

    public StayProperties() {
    }

    public boolean isHasKitchen() {
        return hasKitchen;
    }

    public StayProperties setHasKitchen(boolean hasKitchen) {
        this.hasKitchen = hasKitchen;
        return this;
    }

    public boolean isHasBedroom() {
        return hasBedroom;
    }

    public StayProperties setHasBedroom(boolean hasBedroom) {
        this.hasBedroom = hasBedroom;
        return this;
    }

    public boolean isHasBathroom() {
        return hasBathroom;
    }

    public StayProperties setHasBathroom(boolean hasBathroom) {
        this.hasBathroom = hasBathroom;
        return this;
    }

    public boolean isHasTV() {
        return hasTV;
    }

    public StayProperties setHasTV(boolean hasTV) {
        this.hasTV = hasTV;
        return this;
    }

    public boolean isHasWifi() {
        return hasWifi;
    }

    public StayProperties setHasWifi(boolean hasWifi) {
        this.hasWifi = hasWifi;
        return this;
    }

    public boolean isHasParking() {
        return hasParking;
    }

    public StayProperties setHasParking(boolean hasParking) {
        this.hasParking = hasParking;
        return this;
    }

    public boolean isHasElevator() {
        return hasElevator;
    }

    public StayProperties setHasElevator(boolean hasElevator) {
        this.hasElevator = hasElevator;
        return this;
    }

    public boolean isHasCityView() {
        return hasCityView;
    }

    public StayProperties setHasCityView(boolean hasCityView) {
        this.hasCityView = hasCityView;
        return this;
    }

    public boolean isHasAirConditioning() {
        return hasAirConditioning;
    }

    public StayProperties setHasAirConditioning(boolean hasAirConditioning) {
        this.hasAirConditioning = hasAirConditioning;
        return this;
    }

    public boolean isHasRestaurant() {
        return hasRestaurant;
    }

    public StayProperties setHasRestaurant(boolean hasRestaurant) {
        this.hasRestaurant = hasRestaurant;
        return this;
    }

    public boolean isHasFreeBreakfast() {
        return hasFreeBreakfast;
    }

    public StayProperties setHasFreeBreakfast(boolean hasFreeBreakfast) {
        this.hasFreeBreakfast = hasFreeBreakfast;
        return this;
    }

    public boolean isAllInclusive() {
        return isAllInclusive;
    }

    public StayProperties setAllInclusive(boolean allInclusive) {
        isAllInclusive = allInclusive;
        return this;
    }

    public StayListing getListing() {
        return listing;
    }

    public StayProperties setListing(StayListing listing) {
        this.listing = listing;
        return this;
    }
}
