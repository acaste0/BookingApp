package me.inc.bookingapp.model.binding;

import javax.persistence.Column;

public class StayPropertiesBinding {

    private boolean hasKitchen = false;
    private boolean hasBedroom = false;
    private boolean hasBathroom = false;
    private boolean hasTV = false;
    private boolean hasWifi = false;
    private boolean hasParking = false;
    private boolean hasElevator = false;
    private boolean hasCityView = false;
    private boolean hasAirConditioning = false;
    private boolean hasRestaurant = false;
    private boolean hasFreeBreakfast = false;
    private boolean isAllInclusive = false;


    public boolean isHasKitchen() {
        return hasKitchen;
    }

    public StayPropertiesBinding setHasKitchen(boolean hasKitchen) {
        this.hasKitchen = hasKitchen;
        return this;
    }

    public boolean isHasBedroom() {
        return hasBedroom;
    }

    public StayPropertiesBinding setHasBedroom(boolean hasBedroom) {
        this.hasBedroom = hasBedroom;
        return this;
    }

    public boolean isHasBathroom() {
        return hasBathroom;
    }

    public StayPropertiesBinding setHasBathroom(boolean hasBathroom) {
        this.hasBathroom = hasBathroom;
        return this;
    }

    public boolean isHasTV() {
        return hasTV;
    }

    public StayPropertiesBinding setHasTV(boolean hasTV) {
        this.hasTV = hasTV;
        return this;
    }

    public boolean isHasWifi() {
        return hasWifi;
    }

    public StayPropertiesBinding setHasWifi(boolean hasWifi) {
        this.hasWifi = hasWifi;
        return this;
    }

    public boolean isHasParking() {
        return hasParking;
    }

    public StayPropertiesBinding setHasParking(boolean hasParking) {
        this.hasParking = hasParking;
        return this;
    }

    public boolean isHasElevator() {
        return hasElevator;
    }

    public StayPropertiesBinding setHasElevator(boolean hasElevator) {
        this.hasElevator = hasElevator;
        return this;
    }

    public boolean isHasCityView() {
        return hasCityView;
    }

    public StayPropertiesBinding setHasCityView(boolean hasCityView) {
        this.hasCityView = hasCityView;
        return this;
    }

    public boolean isHasAirConditioning() {
        return hasAirConditioning;
    }

    public StayPropertiesBinding setHasAirConditioning(boolean hasAirConditioning) {
        this.hasAirConditioning = hasAirConditioning;
        return this;
    }

    public boolean isHasRestaurant() {
        return hasRestaurant;
    }

    public StayPropertiesBinding setHasRestaurant(boolean hasRestaurant) {
        this.hasRestaurant = hasRestaurant;
        return this;
    }

    public boolean isHasFreeBreakfast() {
        return hasFreeBreakfast;
    }

    public StayPropertiesBinding setHasFreeBreakfast(boolean hasFreeBreakfast) {
        this.hasFreeBreakfast = hasFreeBreakfast;
        return this;
    }

    public boolean isAllInclusive() {
        return isAllInclusive;
    }

    public StayPropertiesBinding setAllInclusive(boolean allInclusive) {
        isAllInclusive = allInclusive;
        return this;
    }
}
